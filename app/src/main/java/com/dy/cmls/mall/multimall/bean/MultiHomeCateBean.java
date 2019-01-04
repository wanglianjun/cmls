package com.dy.cmls.mall.multimall.bean;

/**
 * Created by lcjing on 2019/1/2.
 */

public class MultiHomeCateBean {
    private String name;
    private String id;
    private int imgResource;

    public MultiHomeCateBean() {
    }

    public MultiHomeCateBean(String name, int imgResource) {
        this.name = name;
        this.imgResource = imgResource;
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

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
