package com.dy.cmls.mine.bean;

import com.dy.cmls.utils.date.DateFormatUtil;

import java.util.List;

/**
 * Created by lcjing on 2018/12/20.
 */

public class ScoreBean {
    private String id;
    private String type;
    private String time;
    private String score;
    private String scoreType;

    public ScoreBean() {
    }
    public ScoreBean(UserPointBean.PointBean pointBean) {
        this.type = pointBean.getType_name();
        this.time= DateFormatUtil.getTime(pointBean.getAdd_time()+"000");
        this.score = pointBean.getAffect_points();
        this.scoreType = "";
    }
    public ScoreBean(String type, String time, String score, String scoreType) {
        this.type = type;
        this.time = time;
        this.score = score;
        this.scoreType = scoreType;
    }

    public ScoreBean(MoneyLogBean.CashBean cashBean,List<MoneyLogBean.CashBean> types ) {
        //要从服务器获取
        for (int i = 0; i < types.size(); i++) {
            if(cashBean.getType().equals(types.get(i).getType())){
                this.type=types.get(i).getTitle();
                switch (type){
                    case "1":
//                        this.type="推广收益";
                        this.scoreType = "+";
                        break;
                    case "2":
//                        this.type="分润收益";
                        this.scoreType = "+";
                        break;
                    case "3":
//                        this.type="申请提现";
                        this.scoreType = "-";
                        break;
                    case "4":
//                        this.type="提现失败";
                        this.scoreType = "";
                        break;
                }
                break;
            }
        }
        this.time = cashBean.getTime();
        this.score = cashBean.getMoney();

        this.id=cashBean.getOrder_no();
    }
    public ScoreBean(CashLogBean.CashBean cashBean,String scoreType ) {
        switch (cashBean.getStatus()){     //0-待审核，1-成功，2-驳回
            case "0":
                this.type = "待审核";
                break;
            case "1":
                this.type = "成功";
                break;
            case "2":
                this.type = "驳回";
                break;
        }

        this.time = cashBean.getTime();
        this.score = cashBean.getMoney();
        this.scoreType = scoreType;
        this.id=cashBean.getOrder_no();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }
}
