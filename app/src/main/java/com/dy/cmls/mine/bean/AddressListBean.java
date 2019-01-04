package com.dy.cmls.mine.bean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/4.
 */

public class AddressListBean {

    /**
     * status : 9999
     * message : 请求成功
     * info : [{"address_id":"65","member_id":"2362","true_name":"鲁成敬","province_id":"2","city_id":"52","area_id":"500","area_info":"可口可乐了","address":null,"tel_phone":"11111111","youbian":null,"is_default":"1","province":"东城区","city":"平山县","area":"新抚区"}]
     */

    private String status;
    private String message;
    private List<InfoBean> info;

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

    public List<InfoBean> getInfo() {
        return info;
    }

    public void setInfo(List<InfoBean> info) {
        this.info = info;
    }

    public static class InfoBean {
        /**
         * address_id : 65
         * member_id : 2362
         * true_name : 鲁成敬
         * province_id : 2
         * city_id : 52
         * area_id : 500
         * area_info : 可口可乐了
         * address : null
         * tel_phone : 11111111
         * youbian : null
         * is_default : 1
         * province : 东城区
         * city : 平山县
         * area : 新抚区
         */

        private String address_id;
        private String member_id;
        private String true_name;
        private String province_id;
        private String city_id;
        private String area_id;
        private String area_info;
        private Object address;
        private String tel_phone;
        private Object youbian;
        private String is_default;
        private String province;
        private String city;
        private String area;

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getProvince_id() {
            return province_id;
        }

        public void setProvince_id(String province_id) {
            this.province_id = province_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public String getArea_info() {
            return area_info;
        }

        public void setArea_info(String area_info) {
            this.area_info = area_info;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public String getTel_phone() {
            return tel_phone;
        }

        public void setTel_phone(String tel_phone) {
            this.tel_phone = tel_phone;
        }

        public Object getYoubian() {
            return youbian;
        }

        public void setYoubian(Object youbian) {
            this.youbian = youbian;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }
}

