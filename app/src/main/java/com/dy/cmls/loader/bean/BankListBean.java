package com.dy.cmls.loader.bean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/4.
 */

public class BankListBean {
    private String status;
    private String message;
    private List<BankBean> info;

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

    public List<BankBean> getInfo() {
        return info;
    }

    public void setInfo(List<BankBean> info) {
        this.info = info;
    }

    public static class BankBean {
        //        name		银行名称
//                number
        private String name;
        private String number;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
