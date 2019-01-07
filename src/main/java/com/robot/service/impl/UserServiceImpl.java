package com.robot.service.impl;

import com.robot.mapper.UserMapper;
import com.robot.pojo.User;
import com.robot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public User selectByPrimaryKey(int qq){
        return userMapper.selectByPrimaryKey(qq);
    }
}
