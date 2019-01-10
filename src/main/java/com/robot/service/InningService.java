package com.robot.service;

import com.robot.pojo.Inning;

public interface InningService {
    Inning getInning();

    boolean createInning(Object cfrobotqqnum, Object guessnum, Object toplimit);
}
