package com.dy.cmls.loader.bean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/3.
 */

public class SimpleResListBean {
    private String status;
    private String message;
    private List<Object> info;

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

    public List<Object> getInfo() {
        return info;
    }

    public void setInfo(List<Object> info) {
        this.info = info;
    }
}
