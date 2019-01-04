package com.dy.cmls.mall.selmall.bean;

/**
 * Created by lcjing on 2018/12/24.
 */

public class LikeBean {
    private String id;
    private String name;
    private String price;
    private String count;
    private String imgUrl;

    public LikeBean() {
    }

    public LikeBean(String name, String price, String count) {
        this.name = name;
        this.price = price;
        this.count ="总销量"+ count;
    }


    public LikeBean(String name, String price, String count, String imgUrl) {
        this.name = name;
        this.price = price;
        this.count ="总销量"+ count;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
