package com.robot.mapper;

import com.robot.pojo.Lottery;

public interface LotteryMapper {
    int deleteByPrimaryKey(Integer periodNum);

    int insert(Lottery record);

    int insertSelective(Lottery record);

    Lottery selectByPrimaryKey(Integer periodNum);

    int updateByPrimaryKeySelective(Lottery record);

    int updateByPrimaryKey(Lottery record);
}