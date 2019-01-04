package com.dy.cmls.mall.selmall.bean;

/**
 * Created by lcjing on 2018/12/24.
 */

public class GoodsBean {
    private String id;
    private String name;
    private String imgUrl;
    private String count;
    private String price;
    private int mCount;
    private boolean select;
    private String attr;

    public GoodsBean() {
    }

    public GoodsBean(String name, String price, String count, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.count ="总销量"+ count;
        this.mCount=Integer.parseInt(count);
        this.price = price;
    }

    public GoodsBean(String name, String imgUrl, String count, String price, String attr) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.count = count;
        this.price = price;
        this.attr = attr;
        this.mCount=Integer.parseInt(count);
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
