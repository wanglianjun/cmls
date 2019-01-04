package com.dy.cmls.loader.bean;

/**
 * Created by lcjing on 2019/1/3.
 */

public class MsgInfoBean {


    /**
     * status : 9999
     * message : 请求成功
     * info : {"title":"注册成功","time":"1546485745","content":"尊敬的18363850579，恭喜您账户注册成功！"}
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
         * title : 注册成功
         * time : 1546485745
         * content : 尊敬的18363850579，恭喜您账户注册成功！
         */

        private String title;
        private String time;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
