package com.dy.cmls.loader.bean;

/**
 * @author tangji
 * @date 2018/5/25 15:52
 */

public class BankCardORCBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":"0","flag":"true","msg":"识别成功!","kh":"6222.....888","yhklx":"借记卡","yhkmc":"京卡借记卡","yhmc":"北京银行","yhbh":"0400000"}
     */

    private String showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public String getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(String showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ret_code : 0
         * flag : true
         * msg : 识别成功!
         * kh : 6222.....888
         * yhklx : 借记卡
         * yhkmc : 京卡借记卡
         * yhmc : 北京银行
         * yhbh : 0400000
         */

        private String ret_code;
        private String flag;
        private String msg;
        private String kh;
        private String yhklx;
        private String yhkmc;
        private String yhmc;
        private String yhbh;

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getKh() {
            return kh;
        }

        public void setKh(String kh) {
            this.kh = kh;
        }

        public String getYhklx() {
            return yhklx;
        }

        public void setYhklx(String yhklx) {
            this.yhklx = yhklx;
        }

        public String getYhkmc() {
            return yhkmc;
        }

        public void setYhkmc(String yhkmc) {
            this.yhkmc = yhkmc;
        }

        public String getYhmc() {
            return yhmc;
        }

        public void setYhmc(String yhmc) {
            this.yhmc = yhmc;
        }

        public String getYhbh() {
            return yhbh;
        }

        public void setYhbh(String yhbh) {
            this.yhbh = yhbh;
        }
    }
}
