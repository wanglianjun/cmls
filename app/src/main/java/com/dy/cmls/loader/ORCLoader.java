package com.dy.cmls.loader;

import com.dy.cmls.base.http.ObjectLoader;
import com.dy.cmls.base.http.RetrofitServiceManager;
import com.dy.cmls.base.http.RetrofitServiceManagerORC;
import com.dy.cmls.loader.bean.BankCardORCBean;
import com.dy.cmls.loader.bean.IDCardORCBean;
import com.dy.cmls.loader.bean.UserInfoBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by lcjing on 2019/1/4.
 */

public class ORCLoader   extends ObjectLoader {
    private static final ORCLoader INSTANCE = new ORCLoader();
    private static final ThisService thisService = RetrofitServiceManagerORC.getInstance().create(ThisService.class);

    public static ORCLoader getInstance() {
        return INSTANCE;
    }

    //ORC识别身份证
    public Observable<IDCardORCBean> postIDCardORC(String urlName, String enctype, String showapi_appid,
                                                   String showapi_sign, String imgData, String type) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.postIDCardORC(enctype, showapi_appid, showapi_sign, imgData, type));
    }

    //ORC识别银行卡
    public Observable<BankCardORCBean> BankCardORCBean(String urlName, String enctype, String showapi_appid,
                                                       String showapi_sign, String imgData) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.BankCardORCBean(enctype, showapi_appid, showapi_sign, imgData));
    }

    public interface ThisService {
        //ORC识别身份证
        @FormUrlEncoded @POST("1429-1/") Observable<IDCardORCBean> postIDCardORC(
                @Field("enctype") String enctype, @Field("showapi_appid") String showapi_appid,
                @Field("showapi_sign") String showapi_sign, @Field("imgData") String imgData,
                @Field("type") String type);

        //ORC识别银行卡
        @FormUrlEncoded @POST("1520-2/") Observable<BankCardORCBean> BankCardORCBean(
                @Field("enctype") String enctype, @Field("showapi_appid") String showapi_appid,
                @Field("showapi_sign") String showapi_sign, @Field("imgData") String imgData);
    }
}
