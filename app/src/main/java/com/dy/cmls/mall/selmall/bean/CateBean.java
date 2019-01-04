package com.dy.cmls.mall.selmall.bean;

/**
 * Created by lcjing on 2018/12/24.
 */

public class CateBean  {
    private String id;
    private String type;
    private String name;
    private boolean select;

    public CateBean() {
    }

    public CateBean(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
