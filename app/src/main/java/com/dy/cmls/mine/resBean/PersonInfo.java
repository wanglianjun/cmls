package com.dy.cmls.mine.resBean;

/**
 * Created by lcjing on 2019/1/3.
 */

public class PersonInfo {
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

    public static class InfoBean{
        private String nick_name;
        private String avatar;
        private String invite_info;
        private String agent_info;
//        "nick_name": "159rwby2297",
//                "avatar": "",
//                "invite_info": "",
//                "agent_info": ""

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getInvite_info() {
            return invite_info;
        }

        public void setInvite_info(String invite_info) {
            this.invite_info = invite_info;
        }

        public String getAgent_info() {
            return agent_info;
        }

        public void setAgent_info(String agent_info) {
            this.agent_info = agent_info;
        }
    }
}
