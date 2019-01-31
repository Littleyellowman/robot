package com.robot.controller;

import cn.hutool.db.nosql.redis.RedisDS;
import com.robot.util.Url;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 类功能描述：加入一桌,判断资格，进行处理
 *
 * <p>
 * 版权所有：小黄人
 * <p>
 * 未经小黄人许可，不得以任何方式复制或使用本程序任何部分
 * <p>
 *
 * @author LittleYellowMan
 * @version v1.0
 *
 */


@RequestMapping("/LittleYellowMan/JoinController")
@Controller
public class JoinController {

    private static final String LittleYellowMan_Join_API = "/join";
    public  static final int periodNum=0;
    private Jedis jedis = RedisDS.create().getJedis();

    @RequestMapping(LittleYellowMan_Join_API)
    @ResponseBody
    public void join(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        String queryArg = request.getQueryString();
        Map queryParam = null;
        try {
            queryParam = Url.paramToMap(queryArg);//参数转转码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String qqNum= (String) queryParam.get("qqNum");//qq
        String tableId= (String) queryParam.get("tableId");//加入的桌
        String betting=request.getParameter("betting");//投注
        String guessnum=request.getParameter("guessnum");//猜测的数字

        List list = jedis.hmget(qqNum,"money");
        int moneys = (int) list.get(0); //加入者资金
        List tableIdList = jedis.hmget(tableId, "tableId");
        int topLimit = (int) list.get(2); //所加桌的投注上限

        if (!jedis.exists("tableId")){
            try {
                response.getWriter().write("你要加入的桌不存在");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        //存在时为老用户，直接操作
        if(jedis.exists(qqNum)){

            if(Integer.parseInt(betting)<0){ //投注不能为负数
                try {
                    response.getWriter().write("投注不能为负数哦");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            }

            if(Integer.parseInt(betting)>topLimit){ //投注不能高于所加桌上限
                try {
                    response.getWriter().write("投注不能高于所加桌上限哦");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            }

            if(Integer.parseInt(betting)>moneys){
                try {
                    response.getWriter().write("您的金币不够哦");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            }
            doJoin(qqNum,tableId,betting,guessnum,moneys,response);

        }else {//不存在时为新用户，需初始化信息后再操作
            //加入新的用户信息
            Map user= new HashMap();
            user.put("qqNum",qqNum);
            user.put("money",1000);
            user.put("freeze",0);
            jedis.hmset(qqNum,user);
            doJoin(qqNum,tableId,betting,guessnum,moneys,response);
        }
    }

    public void doJoin(String qqNum,String tableId,String betting,String guessnum,int moneys,HttpServletResponse response){
        Map map=new HashMap();
        map.put("qqNum",qqNum);
        map.put("tableId",tableId);
        map.put("betting",betting);
        map.put("guessnum",guessnum);
        jedis.hmset(tableId + "_" + qqNum,map);//新建加入

        //修改加入者资金，冻结资金项
        jedis.hset(qqNum,"money", String.valueOf(moneys-Integer.parseInt(betting)));
        jedis.hset(qqNum,"freeze", betting);
        try {
            response.getWriter().write("加入第" + tableId + "桌成功");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return;
    }
}
