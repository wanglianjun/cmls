package com.dy.cmls.mine.bean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/4.
 */

public class CashLogBean {
    //    "status":"1054",
//            "message":
    private String status;
    private String message;
    private InfoBean info;

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

    public InfoBean getInfo() {
        return info;
    }

    public void setInfoBean(InfoBean infoBean) {
        this.info = infoBean;
    }

    public static class InfoBean {
        //"now_page": 1,
//        "all_page": 1,
        private String now_page;
        private String all_page;
        private List<CashBean> list;

        public String getNow_page() {
            return now_page;
        }

        public void setNow_page(String now_page) {
            this.now_page = now_page;
        }

        public String getAll_page() {
            return all_page;
        }

        public void setAll_page(String all_page) {
            this.all_page = all_page;
        }

        public List<CashBean> getList() {
            return list;
        }

        public void setList(List<CashBean> list) {
            this.list = list;
        }
    }

    public static class CashBean {
        private String money;
        private String order_no;
        private String time;
        private String status;
        private String deal_info;
        private String bank_num;
        private String bank_name;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDeal_info() {
            return deal_info;
        }

        public void setDeal_info(String deal_info) {
            this.deal_info = deal_info;
        }

        public String getBank_num() {
            return bank_num;
        }

        public void setBank_num(String bank_num) {
            this.bank_num = bank_num;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        //        "money": "200.00",
//                "order_no": "",
//                "time": "0",
//                "status": "0",
//                "deal_info": "",
//                "bank_num": "",
//                "bank_name": ""
    }

}
