package com.dy.cmls.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dy.cmls.CMLSApp;
import com.dy.cmls.CMLSConstant;

/**
 * Created by lcjing on 2019/1/3.
 */

public class SPUtils {
    public static void setUserInfo(String id, String phone, String nickName) {
        SharedPreferences sp = CMLSApp.getInstance().getSharedPreferences(CMLSConstant.SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putString("id", id).putString("phone", phone).putString("nickname", nickName).apply();
    }

    public static void setUserInfo( String phone, String nickName) {
        SharedPreferences sp = CMLSApp.getInstance().getSharedPreferences(CMLSConstant.SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putString("phone", phone).putString("nickname", nickName).apply();
    }

    public static void setUserPhone( String phone) {
        SharedPreferences sp = CMLSApp.getInstance().getSharedPreferences(CMLSConstant.SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putString("phone", phone).apply();
    }

    public static String getUserId() {
        SharedPreferences sp = CMLSApp.getInstance().getSharedPreferences(CMLSConstant.SP_NAME, Context.MODE_PRIVATE);
        return sp.getString("id", "");
    }

    public static String getNickName() {
        SharedPreferences sp = CMLSApp.getInstance().getSharedPreferences(CMLSConstant.SP_NAME, Context.MODE_PRIVATE);
        return sp.getString("nickname", "");
    }

    public static String getUserPhone() {
        SharedPreferences sp = CMLSApp.getInstance().getSharedPreferences(CMLSConstant.SP_NAME, Context.MODE_PRIVATE);
        return sp.getString("phone", "");
    }

    public static void setRealNameAndId( String idCard, String realName) {
        SharedPreferences sp = CMLSApp.getInstance().getSharedPreferences(CMLSConstant.SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putString("id_card", idCard).putString("real_name", realName).apply();
    }

    public static String getIdCard(){
        SharedPreferences sp = CMLSApp.getInstance().getSharedPreferences(CMLSConstant.SP_NAME, Context.MODE_PRIVATE);
        return sp.getString("id_card", "");
    }

    public static String getRealName(){
        SharedPreferences sp = CMLSApp.getInstance().getSharedPreferences(CMLSConstant.SP_NAME, Context.MODE_PRIVATE);
        return sp.getString("real_name", "");
    }

}
