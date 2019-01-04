package com.dy.cmls.mkabao.bean;

/**
 * Created by lcjing on 2018/12/29.
 */

public class ShouKuanLogBean {
    private String id;
    private String status;
    private String time;
    private String money;

    public ShouKuanLogBean() {
    }

    public ShouKuanLogBean(String status, String time, String money) {
        this.status = status;
        this.time = time;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
