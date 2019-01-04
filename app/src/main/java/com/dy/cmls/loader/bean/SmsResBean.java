package com.dy.cmls.loader.bean;

/**
 * Created by lcjing on 2019/1/3.
 */

public class SmsResBean {
    private String status;
    private String message;
    private SmsInfo info;

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

    public SmsInfo getInfo() {
        return info;
    }

    public void setInfo(SmsInfo info) {
        this.info = info;
    }

    public class SmsInfo{
        private String phone;
        private String code;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
