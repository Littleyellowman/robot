package com.robot.service;

import com.robot.pojo.User;

public interface UserService {

    int deleteByPrimaryKey(Integer qqNum);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer qqNum);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
