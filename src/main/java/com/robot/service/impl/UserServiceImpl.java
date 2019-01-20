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

    @Override
    public int deleteByPrimaryKey(Integer qqNum) {
        return 0;
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer qqNum) {
        return userMapper.selectByPrimaryKey(qqNum);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }
}
