package com.robot.service;

import com.robot.pojo.Lottery;

import java.util.List;

public interface LotteryService {
    List<Lottery> getTodayLotteryList(Integer ydm);
}
