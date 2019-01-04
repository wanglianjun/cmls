package com.dy.cmls.view.pop.bean;

/**
 * Created by lcjing on 2018/12/26.
 */

public class AttrBean {
    private String name;
    private String value;

    public AttrBean() {
    }

    public AttrBean(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
