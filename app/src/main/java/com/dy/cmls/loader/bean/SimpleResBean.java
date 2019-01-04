package com.dy.cmls.loader.bean;

/**
 * Created by lcjing on 2019/1/3.
 */

public class SimpleResBean {
    private String status;
    private String message;
    private InfoBean info;




    public static class InfoBean{
//         "use_price": "33.00",
//                 "use_info": "购买财盟联商使用权，终身免费使用！"
        private String idcard_image1;
        private String idcard_image2;

        private String use_price;
        private String use_info;

        public String getUse_price() {
            return use_price;
        }

        public void setUse_price(String use_price) {
            this.use_price = use_price;
        }

        public String getUse_info() {
            return use_info;
        }

        public void setUse_info(String use_info) {
            this.use_info = use_info;
        }

        public String getIdcard_image1() {
            return idcard_image1;
        }

        public void setIdcard_image1(String idcard_image1) {
            this.idcard_image1 = idcard_image1;
        }

        public String getIdcard_image2() {
            return idcard_image2;
        }

        public void setIdcard_image2(String idcard_image2) {
            this.idcard_image2 = idcard_image2;
        }
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

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


}
