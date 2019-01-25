package com.robot.controller;

import cn.hutool.db.nosql.redis.RedisDS;
import com.robot.service.UserService;
import com.robot.util.Url;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 类功能描述：接受发起请求,判断资格，进行处理
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

@RequestMapping("/LittleYellowMan/Initiate")
@Controller
public class InitiateController {

    private static final String LittleYellowMan_initiate_API = "/resetDeviceStatusApi";
    public  static final int periodNum=0;

    private Jedis jedis = RedisDS.create().getJedis();

    @Autowired
    UserService userService;

    @RequestMapping(LittleYellowMan_initiate_API)
    @ResponseBody
    public void initiate(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setCharacterEncoding("UTF-8");
            String queryArg = request.getQueryString();
            Map queryParam = null;
            try {
                queryParam = Url.paramToMap(queryArg);//参数转转码
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String qqNum= (String) queryParam.get("qqNum");//qq
            String topLimit=request.getParameter("topLimit");//限注
            String numberMan=request.getParameter("numberMan");//人数
            String guessnum=request.getParameter("guessnum");//猜测的数字
            int total=Integer.parseInt(topLimit)*Integer.parseInt(numberMan);//此局总额
            List list=jedis.hmget(qqNum,"money");
            int moneys= (int) list.get(0); //发起者资金
            //存在时为老用户，直接操作
            if(jedis.exists(qqNum)){

                if(Integer.parseInt(topLimit)<50){ //上限不能低于50
                    response.getWriter().write("上限不能太低哦");
                    return;
                }

                if(moneys<total){
                    response.getWriter().write("您的金币不够哦");
                    return;
                }
                String result= creat(qqNum,topLimit,guessnum,moneys,total);
                response.getWriter().write(result);

            }else {//不存在时为新用户，需初始化信息后再操作
                //加入新的用户信息
                Map user= new HashMap();
                user.put("qqNum",qqNum);
                user.put("money",1000);
                user.put("freeze",0);
                jedis.hmset(qqNum,user);

                if(Integer.parseInt(topLimit)<50){ //上限不能低于50
                    response.getWriter().write("上限不能太低哦");
                    return;
                }
                if(moneys<total){
                    response.getWriter().write("您的金币不够哦");
                    return;
                }
                String result= creat(qqNum,topLimit,guessnum,moneys,total);
                response.getWriter().write(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String creat(String qqNum,String topLimit,String guessnum,Integer moneys,Integer total){
        if(periodNum==0){ //前一期数不能为空，为空时无法进行
            return null;
        }
        int period=periodNum + 1; //根据前一期数预算出当前期数
        List periodlist=jedis.lrange("periodlist",0, -1);
        //根据期桌链表算出当前桌数id: 期数_桌数
        int tableId=periodlist.size();
        Map map=new HashMap();
        map.put("tableId",tableId);
        map.put("bankerQq",qqNum);
        map.put("topLimit",topLimit);
        map.put("guessnum",guessnum);
        map.put("profit",0);
        map.put("tableIdList","list_"+tableId);//写入桌链表，结算时从高到底删除使用
        jedis.hmset(String.valueOf(tableId),map);//新建桌局
        jedis.lpush("list_"+tableId,"");//新建空桌链表，后续加入时使用

        //修改发起人资金，冻结资金项
        jedis.hset(qqNum,"money", String.valueOf((moneys-total)));
        jedis.hset(qqNum,"freeze", String.valueOf(total));
        return "第"+tableId+"桌创建成功";
    }

}
