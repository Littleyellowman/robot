package com.robot.mapper;

import com.robot.pojo.Inning;

public interface InningMapper {
    int deleteByPrimaryKey(String tableId);

    int insert(Inning record);

    int insertSelective(Inning record);

    Inning selectByPrimaryKey(String tableId);

    int updateByPrimaryKeySelective(Inning record);

    int updateByPrimaryKey(Inning record);
}