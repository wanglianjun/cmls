package com.dy.cmls.utils;

/**
 * Created by lj on 2018/5/2118:48.
 *
 * @author 963531200
 */

public interface BaiDuLocationListener {
    /**
     * @param province 省
     * @param city  百度定位，市
     * @param area 百度定位，区
     * @param adr 百度定位，地址
     * @param time 百度定位，市
     * @param longitude 百度定位，经度
     * @param latitude 百度定位，纬度
     */
    void result(String province, String city, String area, String adr, String time, double longitude, double latitude);
}
