package com.dy.cmls.mine.bean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/4.
 * 我的积分
 */
public class UserPointBean {

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
        private String member_points;
        private List<PointBean> list;

        public String getMember_points() {
            return member_points;
        }

        public void setMember_points(String member_points) {
            this.member_points = member_points;
        }

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

        public List<PointBean> getList() {
            return list;
        }

        public void setList(List<PointBean> list) {
            this.list = list;
        }
    }

    public static class PointBean {

//"id": "331",
//        "member_id": "2357",
//        "affect_points": "1",
//        "account_points": "1.00",
//        "type_name": "签到得积分",
//        "type": "qd",
//        "info": "推荐获得积分",
//        "add_time": "1545274817",
//        "add_ip": "127.0.0.1"
        private String id;
        private String member_id;
        private String affect_points;
        private String account_points;
        private String type_name;
        private String type;
        private String info;
        private String add_time;
        private String add_ip;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getAffect_points() {
            return affect_points;
        }

        public void setAffect_points(String affect_points) {
            this.affect_points = affect_points;
        }

        public String getAccount_points() {
            return account_points;
        }

        public void setAccount_points(String account_points) {
            this.account_points = account_points;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAdd_ip() {
            return add_ip;
        }

        public void setAdd_ip(String add_ip) {
            this.add_ip = add_ip;
        }
    }

}
