package com.dy.cmls.mine.bean;

/**
 * Created by tangji on 2017/12/5.
 */

public class InviteImageBean {
    private String name;
    private int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public InviteImageBean(String name, int image) {
        this.name = name;
        this.image = image;
    }
}
