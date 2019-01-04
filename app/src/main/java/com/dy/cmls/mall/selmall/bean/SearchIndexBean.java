package com.dy.cmls.mall.selmall.bean;

/**
 * Created by lcjing on 2018/12/24.
 */

public class SearchIndexBean {
    private String id;
    private String name;

    public SearchIndexBean() {
    }

    public SearchIndexBean(String id, String name) {
        this.id = id;
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
}
