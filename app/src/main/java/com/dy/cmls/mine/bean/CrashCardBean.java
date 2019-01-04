package com.dy.cmls.mine.bean;

/**
 * Created by lcjing on 2019/1/4.
 */

public class CrashCardBean {

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
        private String bank_name;
        private String bank_num;
        private String bank_color;
        private String bank_logo;


        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_num() {
            return bank_num;
        }

        public void setBank_num(String bank_num) {
            this.bank_num = bank_num;
        }

        public String getBank_color() {
            return bank_color;
        }

        public void setBank_color(String bank_color) {
            this.bank_color = bank_color;
        }


        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }
    }
}
