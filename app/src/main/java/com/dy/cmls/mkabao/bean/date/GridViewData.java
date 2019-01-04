package com.dy.cmls.mkabao.bean.date;

public class GridViewData {

    public final static int CHECK_NORAML = 0;
    public final static int CHECK_IN = 1;

    private boolean isToday = false;
    private int day;
    private int checkType;
    private VDate vDate;

    public VDate getvDate() {
        return vDate;
    }

    public void setvDate(VDate vDate) {
        this.vDate = vDate;
    }

    public int getCheckType() {
        return checkType;
    }

    public void setCheckType(int checkType) {
        this.checkType = checkType;
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean isToday) {
        this.isToday = isToday;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
