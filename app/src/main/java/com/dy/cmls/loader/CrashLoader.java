package com.dy.cmls.loader;

import com.dy.cmls.base.http.ObjectLoader;
import com.dy.cmls.base.http.RetrofitServiceManager;
import com.dy.cmls.loader.bean.NewsListBean;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.SmsResBean;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.mine.bean.CashLogBean;
import com.dy.cmls.mine.bean.CrashBean;
import com.dy.cmls.mine.bean.CrashCardBean;
import com.dy.cmls.mine.bean.MoneyLogBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lcjing on 2019/1/4.
 */

public class CrashLoader extends ObjectLoader {
    private static final CrashLoader INSTANCE = new CrashLoader();
    private static final ThisService thisService = RetrofitServiceManager.getInstance().create(ThisService.class);

    public static CrashLoader getInstance() {
        return INSTANCE;
    }

    //我的钱包
    public Observable<CrashBean> getCashInfo(String urlName, String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getCashInfo(id));
    }

    //我的提现卡
    public Observable<CrashCardBean> getCashCard(String urlName, String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getCashCard(id));
    }

    //绑定银行卡
    public Observable<SimpleResBean> cashBind(String urlName, String id, String bank_name
            , String bank_num, String bank_number, String phone) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.cashBind(id, bank_name, bank_num, bank_number, phone));
    }

    //绑卡短信
    public Observable<SmsResBean> cashSms(String urlName, String phone) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.cashSms(phone));

    }

    //提现卡 解绑
    public Observable<SimpleResBean> cashUnbind(String urlName, String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.cashUnbind(id));
    }

    //提现申请
    public Observable<SimpleResBean> cashApply(String urlName, String id, String money, String pass) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.cashApply(id, money, pass));
    }

    //提现记录  status留空全部0-待审核，1-成功，2-驳回
    public Observable<CashLogBean> cashLog(String urlName, String id, String page, String status) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.cashLog(id, page, status));
    }

    public Observable<MoneyLogBean> moneyIndex(String urlName) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.moneyIndex());
    }

    public Observable<MoneyLogBean> moneyLog(String urlName, String id, String page, String type) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.moneyLog(id, page, type));
    }

    public interface ThisService {

        //我的钱包
        @FormUrlEncoded
        @POST("Cash/money")
        Observable<CrashBean> getCashInfo(@Field("userid") String id);

        //我的提现卡
        @FormUrlEncoded
        @POST("Cash/card")
        Observable<CrashCardBean> getCashCard(@Field("userid") String id);


        //        userid
//        bank_name		银行名称
//        bank_num		银行卡号
//        bank_number		银行银联号
//        phone
        //提现卡绑卡+修改
        @FormUrlEncoded
        @POST("Cash/bind")
        Observable<SimpleResBean> cashBind(@Field("userid") String id, @Field("bank_name") String bank_name
                , @Field("bank_num") String bank_num
                , @Field("bank_number") String bank_number, @Field("phone") String phone);


        //提现卡解绑
        @FormUrlEncoded
        @POST("Cash/bind_del")
        Observable<SimpleResBean> cashUnbind(@Field("userid") String id);


        //绑卡短信
        @FormUrlEncoded
        @POST("Cash/sms")
        Observable<SmsResBean> cashSms(@Field("phone") String phone);

        //提现申请
        @FormUrlEncoded
        @POST("Cash/apply")
        Observable<SimpleResBean> cashApply(@Field("userid") String id, @Field("money") String money, @Field("pass") String pass);

        //提现记录
        @FormUrlEncoded
        @POST("Cash/log")
        Observable<CashLogBean> cashLog(@Field("userid") String id, @Field("page") String page, @Field("status") String status);

        //交易明细--筛选条件
        @POST("Money/index")
        Observable<MoneyLogBean> moneyIndex();

        //资金变动记录-余额明细+收益记录
        @FormUrlEncoded
        @POST("Money/log")
        Observable<MoneyLogBean> moneyLog(@Field("userid") String id, @Field("page") String page, @Field("type") String type);




    }


}
