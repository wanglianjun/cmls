package com.dy.cmls.mine.bean;

/**
 * Created by lcjing on 2019/1/4.
 */

public class CrashBean {

    /**
     * status : 9999
     * message : 请求成功
     * info : {"account_money":"0.00","bonus_money":null,"can_money":"0.00","member_cash_fee":"2","member_cash_min":"15","is_bind":0,"bank_name":"","bank_num":"","bank_number":"","phone":"","bank_logo":""}
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
         * account_money : 0.00
         * bonus_money : null
         * can_money : 0.00
         * member_cash_fee : 2
         * member_cash_min : 15
         * is_bind : 0
         * bank_name :
         * bank_num :
         * bank_number :
         * phone :
         * bank_logo :
         */

        private String account_money;
        private String bonus_money;
        private String can_money;
        private String member_cash_fee;
        private String member_cash_min;
        private int is_bind;
        private String bank_name;
        private String bank_num;
        private String bank_number;
        private String phone;
        private String bank_logo;

        public String getAccount_money() {
            return account_money;
        }

        public void setAccount_money(String account_money) {
            this.account_money = account_money;
        }

        public String getBonus_money() {
            return bonus_money;
        }

        public void setBonus_money(String bonus_money) {
            this.bonus_money = bonus_money;
        }

        public String getCan_money() {
            return can_money;
        }

        public void setCan_money(String can_money) {
            this.can_money = can_money;
        }

        public String getMember_cash_fee() {
            return member_cash_fee;
        }

        public void setMember_cash_fee(String member_cash_fee) {
            this.member_cash_fee = member_cash_fee;
        }

        public String getMember_cash_min() {
            return member_cash_min;
        }

        public void setMember_cash_min(String member_cash_min) {
            this.member_cash_min = member_cash_min;
        }

        public int getIs_bind() {
            return is_bind;
        }

        public void setIs_bind(int is_bind) {
            this.is_bind = is_bind;
        }

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

        public String getBank_number() {
            return bank_number;
        }

        public void setBank_number(String bank_number) {
            this.bank_number = bank_number;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBank_logo() {
            return bank_logo;
        }

        public void setBank_logo(String bank_logo) {
            this.bank_logo = bank_logo;
        }
    }
}
