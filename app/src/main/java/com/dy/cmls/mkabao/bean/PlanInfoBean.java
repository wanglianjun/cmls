package com.dy.cmls.mkabao.bean;

/**
 * Created by lcjing on 2018/12/7.
 */

public class PlanInfoBean {
    private String type;
    private String money;
    private String dh;//单号
    private String ls;//流水号
    private String feeMoney;//手续费
    private String xfTime;//消费时间
    private String clTime;//处理时间
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public PlanInfoBean() {
    }

    public PlanInfoBean(String type, String money, String dh, String ls, String feeMoney, String xfTime, String clTime) {
        this.type = type;
        this.money = money;
        this.dh = dh;
        this.ls = ls;
        this.feeMoney = feeMoney;
        this.xfTime = xfTime;
        this.clTime = clTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getLs() {
        return ls;
    }

    public void setLs(String ls) {
        this.ls = ls;
    }

    public String getFeeMoney() {
        return feeMoney;
    }

    public void setFeeMoney(String feeMoney) {
        this.feeMoney = feeMoney;
    }

    public String getXfTime() {
        return xfTime;
    }

    public void setXfTime(String xfTime) {
        this.xfTime = xfTime;
    }

    public String getClTime() {
        return clTime;
    }

    public void setClTime(String clTime) {
        this.clTime = clTime;
    }
}
