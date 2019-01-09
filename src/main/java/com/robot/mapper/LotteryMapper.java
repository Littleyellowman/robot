package com.robot.mapper;

import com.robot.pojo.Lottery;

import java.util.List;

public interface LotteryMapper {
    int deleteByPrimaryKey(Integer periodNum);

    int insert(Lottery record);

    int insertSelective(Lottery record);

    Lottery selectByPrimaryKey(Integer periodNum);

    int updateByPrimaryKeySelective(Lottery record);

    int updateByPrimaryKey(Lottery record);

    List<Lottery> getTodayLotteryList(Integer ymd);
}