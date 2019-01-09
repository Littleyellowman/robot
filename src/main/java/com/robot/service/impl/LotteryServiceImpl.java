package com.robot.service.impl;

import com.robot.mapper.LotteryMapper;
import com.robot.pojo.Lottery;
import com.robot.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LotteryServiceImpl implements LotteryService {

    @Autowired
    LotteryMapper lotteryMapper;

    @Override
    public List<Lottery> getTodayLotteryList(Integer ydm) {
        return lotteryMapper.getTodayLotteryList(ydm);
    }
}
