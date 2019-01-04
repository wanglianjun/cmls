package com.dy.cmls.mkabao.bean;

/**
 * Created by lcjing on 2018/12/6.
 */

public class KabaoBean {
    private String bankIcon;
    private String bankName;
    private String zdDate;
    private String hkDate;
    private String money;

    public KabaoBean() {
    }

    public KabaoBean(String bankIcon, String bankName, String zdDate, String hkDate, String money) {
        this.bankIcon = bankIcon;
        this.bankName = bankName;
        this.zdDate = zdDate;
        this.hkDate = hkDate;
        this.money = money;
    }

    public KabaoBean(String bankIcon, String bankName) {
        this.bankIcon = bankIcon;
        this.bankName = bankName;
    }

    public String getBankIcon() {
        return bankIcon;
    }

    public void setBankIcon(String bankIcon) {
        this.bankIcon = bankIcon;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getZdDate() {
        return zdDate;
    }

    public void setZdDate(String zdDate) {
        this.zdDate = zdDate;
    }

    public String getHkDate() {
        return hkDate;
    }

    public void setHkDate(String hkDate) {
        this.hkDate = hkDate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
