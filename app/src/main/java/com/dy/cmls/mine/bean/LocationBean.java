package com.dy.cmls.mine.bean;

/**
 * Created by lcjing on 2018/12/29.
 */

public class LocationBean {
    private String id;
    private String name;
    private String phone;
    private String address;
    private boolean idDefault;
    private String location;
    private String addressInfo;
    private String pId;
    private String cId;
    private String aId;

    public LocationBean(String name, String phone, String address, boolean idDefault) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.idDefault = idDefault;
    }

    public LocationBean(AddressListBean.InfoBean bean) {
        this.name = bean.getTrue_name();
        this.phone = bean.getTel_phone();
        this.address = bean.getProvince()+bean.getCity()+bean.getArea()+bean.getArea_info();
        this.idDefault = bean.getIs_default().equals("1");
        this.id=bean.getAddress_id();
        this.location=bean.getProvince()+bean.getCity()+bean.getArea();
        this.pId=bean.getProvince_id();
        this.cId=bean.getCity_id();
        this.aId=bean.getArea_id();
        this.addressInfo=bean.getArea_info();
    }

    public LocationBean(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }


    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIdDefault() {
        return idDefault;
    }

    public void setIdDefault(boolean idDefault) {
        this.idDefault = idDefault;
    }
}
