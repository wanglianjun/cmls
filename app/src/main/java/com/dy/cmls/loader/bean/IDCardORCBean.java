package com.dy.cmls.loader.bean;

/**
 * @author tangji
 * @date 2018/5/25 15:52
 */

public class IDCardORCBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"msg":"识别成功!","birthday":"198X-0X-XX","flag":true,"nationality":"汉","sex":"男","name":"王XX","headImgBase64":"身份证的头像的base64信息","addr":"湖北省武汉市.....","ret_code":0,"idNo":"*********01211122"}
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
         * msg : 识别成功!
         * birthday : 198X-0X-XX
         * flag : true
         * nationality : 汉
         * sex : 男
         * name : 王XX
         * headImgBase64 : 身份证的头像的base64信息
         * addr : 湖北省武汉市.....
         * ret_code : 0
         * idNo : *********01211122
         */

        private String msg;
        private String birthday;
        private boolean flag;
        private String nationality;
        private String sex;
        private String name;
        private String headImgBase64;
        private String addr;
        private String ret_code;
        private String idNo;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeadImgBase64() {
            return headImgBase64;
        }

        public void setHeadImgBase64(String headImgBase64) {
            this.headImgBase64 = headImgBase64;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }
    }
}
