package com.dy.cmls.mkabao.bean;

/**
 * Created by lcjing on 2018/12/7.
 */

public class ZhangdanBean {
    private String type;
    private String num;
    private String money;
    private String time;

    public ZhangdanBean() {
    }

    public ZhangdanBean(String type) {
        this.type = type;
        num="尾号（1208）";
        money="￥5000.00";
        time="2018-11-28 14:32:20";
    }

    public ZhangdanBean(String type, String num, String money, String time) {
        this.type = type;
        this.num = num;
        this.money = money;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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
