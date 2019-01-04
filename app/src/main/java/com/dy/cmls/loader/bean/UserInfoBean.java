package com.dy.cmls.loader.bean;

/**
 * Created by lcjing on 2019/1/3.
 */

public class UserInfoBean {

    /**
     * status : 9999
     * message : 请求成功
     * info : {"phone":"18363850579","paypass":"0","nick_name":"183ehmf0579","account_money":"0.00","bonus_money":null,"is_confirm":"0","is_pay":"0","avatar":"","msg":"1","area":"中国 山东 济南","realname":null,"idcard":null,"cash_bank":"0","bank_num":null,"perfect_repayment_rate":null,"quick_repayment_rate":null,"collect_rate":null,"cash_fee":"1.00","collect_fee":"2","level":null,"points":"0"}
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
         * phone : 18363850579
         * paypass : 0
         * nick_name : 183ehmf0579
         * account_money : 0.00
         * bonus_money : null
         * is_confirm : 0
         * is_pay : 0
         * avatar :
         * msg : 1
         * area : 中国 山东 济南
         * realname : null
         * idcard : null
         * cash_bank : 0
         * bank_num : null
         * perfect_repayment_rate : null
         * quick_repayment_rate : null
         * collect_rate : null
         * cash_fee : 1.00
         * collect_fee : 2
         * level : null
         * points : 0
         */

        private String phone;
        private String paypass;
        private String nick_name;
        private String account_money;
        private String bonus_money;
        private String is_confirm;
        private String is_pay;
        private String avatar;
        private String msg;
        private String area;
        private String realname;
        private String idcard;
        private String cash_bank;
        private String bank_num;
        private String perfect_repayment_rate;
        private String quick_repayment_rate;
        private String collect_rate;
        private String cash_fee;
        private String collect_fee;
        private String level;
        private String points;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPaypass() {
            return paypass;
        }

        public void setPaypass(String paypass) {
            this.paypass = paypass;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

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

        public String getIs_confirm() {
            return is_confirm;
        }

        public void setIs_confirm(String is_confirm) {
            this.is_confirm = is_confirm;
        }

        public String getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(String is_pay) {
            this.is_pay = is_pay;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getCash_bank() {
            return cash_bank;
        }

        public void setCash_bank(String cash_bank) {
            this.cash_bank = cash_bank;
        }

        public String getBank_num() {
            return bank_num;
        }

        public void setBank_num(String bank_num) {
            this.bank_num = bank_num;
        }

        public String getPerfect_repayment_rate() {
            return perfect_repayment_rate;
        }

        public void setPerfect_repayment_rate(String perfect_repayment_rate) {
            this.perfect_repayment_rate = perfect_repayment_rate;
        }

        public String getQuick_repayment_rate() {
            return quick_repayment_rate;
        }

        public void setQuick_repayment_rate(String quick_repayment_rate) {
            this.quick_repayment_rate = quick_repayment_rate;
        }

        public String getCollect_rate() {
            return collect_rate;
        }

        public void setCollect_rate(String collect_rate) {
            this.collect_rate = collect_rate;
        }

        public String getCash_fee() {
            return cash_fee;
        }

        public void setCash_fee(String cash_fee) {
            this.cash_fee = cash_fee;
        }

        public String getCollect_fee() {
            return collect_fee;
        }

        public void setCollect_fee(String collect_fee) {
            this.collect_fee = collect_fee;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }
    }
}
