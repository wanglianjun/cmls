package com.dy.cmls.mine.bean;

import com.dy.cmls.loader.bean.MsgListBean;
import com.dy.cmls.utils.date.DateFormatUtil;

import java.text.ParseException;

/**
 * Created by lcjing on 2018/12/29.
 */

public class MessageBean {
    private String id;
    private String title;
    private String time;
    private String content;
    private boolean unRead;

    public MessageBean(String title, String time, String content, boolean unRead) {
        this.title = title;
        this.time = time;
        this.content = content;
        this.unRead = unRead;
    }

    public MessageBean(String title, String time, String content) {
        this.title = title;
        this.time = time;
        this.content = content;
    }


    public MessageBean(MsgListBean.MessageBean messageBean) {
        this.id = messageBean.getId();
        this.title = messageBean.getTitle();
        this.time = DateFormatUtil.getTime(messageBean.getTime());
        this.content = messageBean.getInfo();
        this.unRead = "0".contains(messageBean.getStatus());
    }

    public MessageBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isUnRead() {
        return unRead;
    }

    public void setUnRead(boolean unRead) {
        this.unRead = unRead;
    }
}
