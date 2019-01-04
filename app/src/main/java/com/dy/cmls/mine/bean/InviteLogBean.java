package com.dy.cmls.mine.bean;

/**
 * Created by lcjing on 2018/12/19.
 */

public class InviteLogBean {
    private String phone;
    private String name;
    private String id;
    private String time;
    private boolean shiming;

    public InviteLogBean(String name, String time, boolean shiming) {
        this.time = time;
        this.shiming = shiming;
        if (name.length()>8) {
            String a=name.substring(0,3);
            String a2=name.substring(7);
            name=a+"****"+a2;
        }
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isShiming() {
        return shiming;
    }

    public void setShiming(boolean shiming) {
        this.shiming = shiming;
    }
}
