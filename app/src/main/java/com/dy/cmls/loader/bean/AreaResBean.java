package com.dy.cmls.loader.bean;

import com.dy.cmls.mall.multimall.bean.AreaBean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/3.
 */

public class AreaResBean {
    private String status;
    private String message;
    private List<AreaBean> info;

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

    public List<AreaBean> getInfo() {
        return info;
    }

    public void setInfo(List<AreaBean> info) {
        this.info = info;
    }
}
