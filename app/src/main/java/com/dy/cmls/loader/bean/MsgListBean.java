package com.dy.cmls.loader.bean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/3.
 */

public class MsgListBean {

    /**
     * status : 9999
     * message : 请求成功
     * info : {"now_page":1,"all_page":1,"message":[{"id":"70","title":"注册成功","info":"尊敬的18363850579，恭喜您账户注册成功！","status":"0","time":"1546485745"}]}
     */

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

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * now_page : 1
         * all_page : 1
         * message : [{"id":"70","title":"注册成功","info":"尊敬的18363850579，恭喜您账户注册成功！","status":"0","time":"1546485745"}]
         */

        private int now_page;
        private int all_page;
        private List<MessageBean> message;

        public int getNow_page() {
            return now_page;
        }

        public void setNow_page(int now_page) {
            this.now_page = now_page;
        }

        public int getAll_page() {
            return all_page;
        }

        public void setAll_page(int all_page) {
            this.all_page = all_page;
        }

        public List<MessageBean> getMessage() {
            return message;
        }

        public void setMessage(List<MessageBean> message) {
            this.message = message;
        }


    }
    public static class MessageBean {
        /**
         * id : 70
         * title : 注册成功
         * info : 尊敬的18363850579，恭喜您账户注册成功！
         * status : 0
         * time : 1546485745
         */

        private String id;
        private String title;
        private String info;
        private String status;
        private String time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
