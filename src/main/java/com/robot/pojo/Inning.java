package com.robot.pojo;

import java.io.Serializable;

public class Inning implements Serializable {
    private String tableId;

    private Integer bankerQq;

    private Integer topLimit;

    private Integer guessnum;

    private Integer state;

    private Integer profit;

    private static final long serialVersionUID = 1L;

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId == null ? null : tableId.trim();
    }

    public Integer getBankerQq() {
        return bankerQq;
    }

    public void setBankerQq(Integer bankerQq) {
        this.bankerQq = bankerQq;
    }

    public Integer getTopLimit() {
        return topLimit;
    }

    public void setTopLimit(Integer topLimit) {
        this.topLimit = topLimit;
    }

    public Integer getGuessnum() {
        return guessnum;
    }

    public void setGuessnum(Integer guessnum) {
        this.guessnum = guessnum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }
}