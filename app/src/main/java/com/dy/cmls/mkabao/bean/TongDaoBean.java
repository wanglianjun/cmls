package com.dy.cmls.mkabao.bean;

/**
 * Created by lcjing on 2018/12/29.
 */

public class TongDaoBean {
    private String id;
    private String name;
    private String type_name;
    private String rate;
    private String single;
    private String desc;
    private String jiesuan;
    private String time;
    private String money = "0";

    public TongDaoBean(String name, String type_name, String rate, String single, String desc, String jiesuan, String time, String money) {
        this.name = name;
        this.type_name = type_name;
        this.rate = rate;
        this.single = single;
        this.desc = desc;
        this.jiesuan = jiesuan;
        this.time = time;
        this.money = money;
    }

    public TongDaoBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJiesuan() {
        return jiesuan;
    }

    public void setJiesuan(String jiesuan) {
        this.jiesuan = jiesuan;
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
