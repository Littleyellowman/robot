package com.robot.service.impl;

import com.robot.mapper.InningMapper;
import com.robot.pojo.Inning;
import com.robot.service.InningService;
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

    @Override
    public boolean createInning(Object cfrobotqqnum, Object guessnum, Object toplimit) {
        Inning inning = new Inning();
        inning.setBankerQq((Integer) cfrobotqqnum);
        inning.setGuessnum((Integer) guessnum);
        inning.setGuessnum((Integer) toplimit);
        inningMapper.insert(inning);
        return false;
    }
}
