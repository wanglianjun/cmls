package com.dy.cmls.mine.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lcjing on 2019/1/4.
 */

public class MyInviteResBean {

    /**
     * status : 9999
     * message : 请求成功
     * info : {"qrcode":"http://cmls.diyunkeji.com/Uploads/rcode/member-2362.png","url":"http://cmls.diyunkeji.com/index.php/Home/Common/register/type/member/code/2362","code":"M886271376","level":{"1":"邀请且激活人数达到人次即可升级","2":"邀请且激活人数达到人次即可升级","3":"在线购买使用权或使用激活码激活即可升级"}}
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
         * qrcode : http://cmls.diyunkeji.com/Uploads/rcode/member-2362.png
         * url : http://cmls.diyunkeji.com/index.php/Home/Common/register/type/member/code/2362
         * code : M886271376
         * level : {"1":"邀请且激活人数达到人次即可升级","2":"邀请且激活人数达到人次即可升级","3":"在线购买使用权或使用激活码激活即可升级"}
         */

        private String qrcode;
        private String url;
        private String code;
        private LevelBean level;

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public LevelBean getLevel() {
            return level;
        }

        public void setLevel(LevelBean level) {
            this.level = level;
        }

        public static class LevelBean {
            /**
             * 1 : 邀请且激活人数达到人次即可升级
             * 2 : 邀请且激活人数达到人次即可升级
             * 3 : 在线购买使用权或使用激活码激活即可升级
             */

            @SerializedName("1")
            private String _$1;
            @SerializedName("2")
            private String _$2;
            @SerializedName("3")
            private String _$3;

            public String get_$1() {
                return _$1;
            }

            public void set_$1(String _$1) {
                this._$1 = _$1;
            }

            public String get_$2() {
                return _$2;
            }

            public void set_$2(String _$2) {
                this._$2 = _$2;
            }

            public String get_$3() {
                return _$3;
            }

            public void set_$3(String _$3) {
                this._$3 = _$3;
            }
        }
    }
}
