package com.robot.service.impl;

import com.robot.mapper.InningMapper;
import com.robot.pojo.Inning;
import com.robot.service.InningService;
import com.robot.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class InningServiceImpl implements InningService {

    @Autowired
    InningMapper inningMapper;

    @Override
    public Inning getInning() {
        /*DateUtil.getDate();
        inningMapper.selectByPrimaryKey();*/
        return null;
    }
}
