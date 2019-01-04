package com.dy.cmls.mall.selmall.bean;

/**
 * Created by lcjing on 2018/12/25.
 */

public class MyEvaBean {

    private String id;
    private String imgHead;
    private String name;
    private String date;
    private String goodsType;
    private String content;
    private String img1;
    private String img2;
    private String img3;
    private String imgGoods;
    private String goodsName;
    private String goodsPrice;
    private String shopContent;

    public MyEvaBean(String id) {
        this.id = id;
        initData();
    }

    public MyEvaBean(String id, String shopContent) {
        this.id = id;
        this.shopContent = "商家回复：感谢您购买本店商品，您的满意使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的使我们永恒的追求和动力，请多多光顾。";
        initData();
        img3 = "";
    }

    private void initData() {
        imgHead = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545742495088&di=dfc314f3d19940569f156a63f93f0720&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201802%2F01%2F20180201233733_qzdnw.png";
        name = "小红花";
        date = "2018-12-12";
        goodsType = "24K黑色";
        content = "超级好吃，一个也没坏，用泡沫箱装的还有冰袋，超贴心，打开盒子新鲜的蛋奶香，颜值高送人超有面子。";
        img1 = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3622275753,2691656719&fm=26&gp=0.jpg";
        img2 = img1;
        img3 = img1;
        imgGoods = img1;
        goodsName = "神奇的艾菲勒法式马卡龙甜点24枚西式糕点心小蛋糕甜品零食品";
        goodsPrice = "￥100.99";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgHead() {
        return imgHead;
    }

    public void setImgHead(String imgHead) {
        this.imgHead = imgHead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImgGoods() {
        return imgGoods;
    }

    public void setImgGoods(String imgGoods) {
        this.imgGoods = imgGoods;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getShopContent() {
        return shopContent;
    }

    public void setShopContent(String shopContent) {
        this.shopContent = shopContent;
    }
}
