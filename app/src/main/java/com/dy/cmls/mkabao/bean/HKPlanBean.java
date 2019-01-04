package com.dy.cmls.mkabao.bean;

/**
 * Created by lcjing on 2018/12/6.
 */

public class HKPlanBean {
    private String money;
    private String status;
    private String feeMoney;
    private String time;

    public HKPlanBean() {
    }

    public HKPlanBean(String money, String status, String feeMoney, String time) {
        this.money = money;
        this.status = status;
        this.feeMoney = feeMoney;
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeeMoney() {
        return feeMoney;
    }

    public void setFeeMoney(String feeMoney) {
        this.feeMoney = feeMoney;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
