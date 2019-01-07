package com.robot.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private Integer qqNum;

    private Integer money;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public Integer getQqNum() {
        return qqNum;
    }

    public void setQqNum(Integer qqNum) {
        this.qqNum = qqNum;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}