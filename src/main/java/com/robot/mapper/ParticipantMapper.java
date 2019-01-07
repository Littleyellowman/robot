package com.robot.mapper;

import com.robot.pojo.Participant;

public interface ParticipantMapper {
    int deleteByPrimaryKey(String tableId);

    int insert(Participant record);

    int insertSelective(Participant record);

    Participant selectByPrimaryKey(String tableId);

    int updateByPrimaryKeySelective(Participant record);

    int updateByPrimaryKey(Participant record);
}