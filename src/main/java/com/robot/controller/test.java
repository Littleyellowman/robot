package com.robot.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.robot.util.DateUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 类功能描述：本地测试
 *
 * <p>
 * 版权所有：小黄人
 * <p>
 * 未经小黄人许可，不得以任何方式复制或使用本程序任何部分
 * <p>
 *
 * @author <a href="mailto:287627997@qq.com">287627997</a>
 * @version WEBMAIL2.0
 * @since 2019-1-6
 *
 */
public class test {

    private static Logger logger = Logger.getLogger(test.class);

    public static void main(String[] args) {
        //getURLInfo();
        //decode();
        //logger.info(DateUtil.getDate("yyyyMMdd"));
        Jedis jedis = RedisDS.create().getJedis();
        /*字符串*/
        //jedis.set("user", "wen");
        /*左边推入*/
        //long cc = jedis.lpush("list", "b");
        /*右边弹出*/
        //String cc = jedis.rpop("list");
        /*存Map数据*/
        /*Map userInfo = new HashMap<String, String>();
        userInfo.put("username", "wenbq");
        userInfo.put("age", "24");
        String cc = jedis.hmset("userinfo",userInfo);*/
        /*删除*/
        //long cc = jedis.del("userinfo");
        JSONObject cc = JSONUtil.parseObj("{aa:bb}");
        logger.info(cc.get("aa"));
    }

    public static void decode(){
        String cc = java.net.URLDecoder.decode("%E7%9A%84");
        System.out.println(cc);
    }

    public static void getURLInfo()  {
        StringBuffer document = new StringBuffer();
        try{
            URL url = new URL("http://kaijiang.500.com/bjk3.shtml");//远程url
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null)
                document.append(line + " ");
            reader.close();
        }catch(MalformedURLException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        String xml = document.toString();//返回值
        System.out.println(xml);
    }
}
