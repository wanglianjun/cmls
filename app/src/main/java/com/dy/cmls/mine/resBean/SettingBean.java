package com.dy.cmls.mine.resBean;

/**
 * Created by lcjing on 2019/1/3.
 */

public class SettingBean {
    private String status;
    private String message;

    private SettingInfo info;

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

    public SettingInfo getInfo() {
        return info;
    }

    public void setInfo(SettingInfo info) {
        this.info = info;
    }

    public static class SettingInfo{
        private String title;//网站名称
        private String comp_name;//公司名称
        private String phone;
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getComp_name() {
            return comp_name;
        }

        public void setComp_name(String comp_name) {
            this.comp_name = comp_name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
