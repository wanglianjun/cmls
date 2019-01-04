package com.dy.cmls.mall.multimall.bean;

/**
 * Created by lcjing on 2019/1/2.
 */

public class AreaBean {
    private String id;
    private String name;
    private boolean isPosition;
    private boolean select;

    public AreaBean() {
    }

    public AreaBean(String name) {
        this.name = name;
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

    public boolean isPosition() {
        return isPosition;
    }

    public void setPosition(boolean position) {
        isPosition = position;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
