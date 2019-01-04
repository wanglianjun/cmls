package com.dy.cmls.mine.bean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/4.
 */

public class MoneyLogBean {
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

//        "type": "1",
//                "title": "推广收益"
//         "money": "0.60",
//                 "order_no": "201811261253172615",
//                 "time": "1543207997",
//                 "type": "2",
//                 "info": "您的好友【17615107370】进行快捷收款，您获得0.6元分润。"
        private String type;
        private String title;
        private String money;
        private String order_no;
        private String time;
        private String info;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

}
