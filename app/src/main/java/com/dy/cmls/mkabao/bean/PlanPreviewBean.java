package com.dy.cmls.mkabao.bean;

/**
 * Created by lcjing on 2018/12/7.
 */

public class PlanPreviewBean {
    private String time;
    private String planMoney;
    private String hkMoney;
    private String firstMoney;
    private String secondMoney;
    private String thirdMoney;
    private String feeMoney;

    public PlanPreviewBean() {
    }

    public PlanPreviewBean(String time, String planMoney) {
        this.time = time;
        this.planMoney = planMoney;
    }

    public PlanPreviewBean(String time, String planMoney, String hkMoney, String firstMoney, String secondMoney, String thirdMoney, String feeMoney) {
        this.time = time;
        this.planMoney = planMoney;
        this.hkMoney = hkMoney;
        this.firstMoney = firstMoney;
        this.secondMoney = secondMoney;
        this.thirdMoney = thirdMoney;
        this.feeMoney = feeMoney;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(String planMoney) {
        this.planMoney = planMoney;
    }

    public String getHkMoney() {
        return hkMoney;
    }

    public void setHkMoney(String hkMoney) {
        this.hkMoney = hkMoney;
    }

    public String getFirstMoney() {
        return firstMoney;
    }

    public void setFirstMoney(String firstMoney) {
        this.firstMoney = firstMoney;
    }

    public String getSecondMoney() {
        return secondMoney;
    }

    public void setSecondMoney(String secondMoney) {
        this.secondMoney = secondMoney;
    }

    public String getThirdMoney() {
        return thirdMoney;
    }

    public void setThirdMoney(String thirdMoney) {
        this.thirdMoney = thirdMoney;
    }

    public String getFeeMoney() {
        return feeMoney;
    }

    public void setFeeMoney(String feeMoney) {
        this.feeMoney = feeMoney;
    }
}
