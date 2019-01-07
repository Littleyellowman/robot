package com.robot.pojo;

import java.io.Serializable;

public class Participant implements Serializable {
    private String tableId;

    private Integer participantQq;

    private Integer money;

    private Integer guessNum;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

    public Integer getParticipantQq() {
        return participantQq;
    }

    public void setParticipantQq(Integer participantQq) {
        this.participantQq = participantQq;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getGuessNum() {
        return guessNum;
    }

    public void setGuessNum(Integer guessNum) {
        this.guessNum = guessNum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}