package com.dy.cmls.loader.bean;


import java.util.List;

/**
 * Created by lcjing on 2019/1/3.
 */

public class IndexBannerBean {
    private String status;
    private String message;
    private List<BannerInfo> info;


    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BannerInfo> getInfo() {
        return info;
    }

    public void setInfo(List<BannerInfo> info) {
        this.info = info;
    }

    public static class  BannerInfo{
//        "title": "1111",
//                "image": "http:\/\/cmls.test.com\/Uploads\/banner\/5c064a16acd3a.png"
        private String title;
        private String image;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
