package com.dy.cmls.mall.multimall.bean;

import com.dy.cmls.mall.selmall.bean.GoodsBean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/2.
 */

public class SellerBean {
    private String name;
    private String url;
    private String id;
    private boolean hasRedPacket;
    private List<GoodsBean> goods;

    public SellerBean() {
    }

    public SellerBean(String name, String url, boolean hasRedPacket, List<GoodsBean> goods) {
        this.name = name;
        this.url = url;
        this.hasRedPacket = hasRedPacket;
        this.goods = goods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasRedPacket() {
        return hasRedPacket;
    }

    public void setHasRedPacket(boolean hasRedPacket) {
        this.hasRedPacket = hasRedPacket;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }
}
