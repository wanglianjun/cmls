package com.dy.cmls.loader;

import com.dy.cmls.base.http.ObjectLoader;
import com.dy.cmls.base.http.RetrofitServiceManager;
import com.dy.cmls.loader.bean.AreaResBean;
import com.dy.cmls.loader.bean.RegisterResBean;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.SmsResBean;
import com.dy.cmls.mall.multimall.bean.AreaBean;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by lcjing on 2019/1/3.
 */

public class LoginLoader  extends ObjectLoader {
    private static final LoginLoader INSTANCE = new LoginLoader();
    private static final ThisService thisService = RetrofitServiceManager.getInstance().create(ThisService.class);

    public static LoginLoader getInstance() {
        return INSTANCE;
    }

    public Observable<AreaResBean> getArea(String urlName, String pid) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getArea(pid));
    }

    public Observable<SmsResBean> getRegisterCode(String urlName, String phone) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getRegisterCode(phone));
    }
    public Observable<RegisterResBean> register(String urlName, String phone,String code,String area,String pass) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.register(phone,code,area,pass));
    }

    public Observable<RegisterResBean> login(String urlName, String phone,String pass) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.login(phone,pass));
    }

    public Observable<SmsResBean> getPassCode(String urlName, String phone) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getPassCode(phone));
    }


    public Observable<SimpleResBean> passModify(String urlName, String phone,String pass) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.passModify(phone,pass));
    }

    public interface ThisService {
        //定位-地区匹配ID获取
        @FormUrlEncoded
        @POST("Register/area")
        Observable<AreaResBean> getArea(@Field("pid") String pid);

        //用户注册短信
        @FormUrlEncoded
        @POST("Register/sms")
        Observable<SmsResBean> getRegisterCode(@Field("phone") String phone);


        /**
         * 注册
         * @param phone
         * @param code 邀请码
         * @param area 地区id
         * @param pass
         * @return
         */
        @FormUrlEncoded
        @POST("Register/doreg")
        Observable<RegisterResBean> register(@Field("phone") String phone, @Field("code") String code,
                                             @Field("area") String area, @Field("pass") String pass);
        //用户登录提交
        @FormUrlEncoded
        @POST("Login/dologin")
        Observable<RegisterResBean> login(@Field("phone") String phone, @Field("pass") String pass);


        //用户忘记密码短信
        @FormUrlEncoded
        @POST("Password/sms")
        Observable<SmsResBean> getPassCode(@Field("phone") String phone);

        //用户忘记密码短信
        @FormUrlEncoded
        @POST("Password/back")
        Observable<SimpleResBean> passModify(@Field("phone") String phone, @Field("pass") String pass);

    }
}
