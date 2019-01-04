package com.dy.cmls.mine.bean;

/**
 * Created by lcjing on 2018/12/29.
 */

public class LocationBean {
    private String id;
    private String name;
    private String phone;
    private String address;
    private boolean idDefault;

    public LocationBean(String name, String phone, String address, boolean idDefault) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.idDefault = idDefault;
    }

    public LocationBean(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIdDefault() {
        return idDefault;
    }

    public void setIdDefault(boolean idDefault) {
        this.idDefault = idDefault;
    }
}
