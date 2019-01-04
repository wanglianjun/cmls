package com.dy.cmls.mkabao.bean;

/**
 * Created by lcjing on 2018/12/29.
 */

public class CardBean {
    private String id="1";
    private String bank_num;
    private String bank_name;
    private String color;
    private String logo;

    public CardBean() {
    }

    public CardBean(String bank_num, String bank_name) {
        this.bank_num = bank_num;
        this.bank_name = bank_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBank_num() {
        return bank_num;
    }

    public void setBank_num(String bank_num) {
        this.bank_num = bank_num;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
