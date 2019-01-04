package com.dy.cmls.mall.selmall.bean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/25.
 */

public class OrderBean {

    private String id;
    private String type;
    private String status;
    private String title;
    private String titleUrl;
    private String price;
    private String count;
    private List<GoodsBean> goodsBeans;

    public OrderBean(String type, String status, List<GoodsBean> goodsBeans) {
        this.type = type;
        this.status = status;
        this.goodsBeans = goodsBeans;
    }

    public OrderBean(String status, String price, String count, List<GoodsBean> goodsBeans) {
        this.status = status;
        this.price = price;
        this.count = count;
        this.goodsBeans = goodsBeans;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GoodsBean> getGoodsBeans() {
        return goodsBeans;
    }

    public void setGoodsBeans(List<GoodsBean> goodsBeans) {
        this.goodsBeans = goodsBeans;
    }
}
