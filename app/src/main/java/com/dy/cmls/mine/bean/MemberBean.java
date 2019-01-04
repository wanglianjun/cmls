package com.dy.cmls.mine.bean;

/**
 * Created by lcjing on 2018/12/20.
 */

public class MemberBean {
    private String id;
    private String name;
    private String type;
    private String money;
    private String time;


    public MemberBean() {
    }

    public MemberBean(String name, String type, String money, String time) {
        this.name = name;
        this.type = type;
        this.money = money;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
