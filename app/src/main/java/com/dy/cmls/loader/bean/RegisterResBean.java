package com.dy.cmls.loader.bean;

/**
 * Created by lcjing on 2019/1/3.
 */

public class RegisterResBean {
    private String status;
    private String message;
    private RegisterInfo info;

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

    public RegisterInfo getInfo() {
        return info;
    }

    public void setInfo(RegisterInfo info) {
        this.info = info;
    }

    public static class RegisterInfo{
        private String member_id;
        private String id;
        private String phone;
        private String nick_name;
//        "id": "2354",
//                "phone": "15910082297",
//                "nick_name": "159cqvo2297"


        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }
    }
}
