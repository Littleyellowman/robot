package com.robot.pojo;

import java.io.Serializable;

public class Lottery implements Serializable {
    private Integer periodNum;

    private String time;

    private String result;

    private static final long serialVersionUID = 1L;

    public Integer getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(Integer periodNum) {
        this.periodNum = periodNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}