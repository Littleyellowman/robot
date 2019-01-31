package com.robot.util;

import cn.hutool.cron.CronUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.robot.controller.UserController;
import com.robot.pojo.Lottery;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 类功能描述：缓存类
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
public class Cache {

    private static Logger logger = Logger.getLogger(Cache.class);

    public static int originalPeriod = -1;//上一期

    public static int currentPeriod = -1;//最新一期

    /*服务启动时进行初始化*/
    public void initData(){
        /*Integer ymd = Integer.valueOf(DateUtil.getDate("yyyyMMdd"));
        List<Lottery> todayLotteryList = lotteryServiceImpl.getTodayLotteryList(ymd);
        logger.info("1111111111    " + todayLotteryList);
        int maxLottery = 0;
        for (int i=0; i<todayLotteryList.size(); i++){
            Lottery lottery = todayLotteryList.get(i);
            *//*if (maxLottery<lottery.getPeriodNum().intValue()){
                maxLottery = lottery.getPeriodNum();
            }*//*
            logger.info("333333333    " + lottery.getPeriodNum());
            logger.info("333333333    " + lottery.getTime());
        }
        logger.info("2222222222    " + maxLottery);*/

        Jedis jedis = RedisDS.create().getJedis();
        Map latestIssue = LittleYellowMan.caijuzi();//获取最新一期数据
        //存入redis
        String insertSuccess = jedis.hmset("latestIssue",latestIssue);
        if (insertSuccess == "OK"){
            originalPeriod = (int) latestIssue.get("qh");//改变标志位
        }


        //启动定时任务
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }
}
