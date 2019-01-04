package com.dy.cmls.loader;

import com.dy.cmls.base.http.ObjectLoader;
import com.dy.cmls.base.http.RetrofitServiceManager;
import com.dy.cmls.loader.bean.AreaResBean;
import com.dy.cmls.loader.bean.MsgInfoBean;
import com.dy.cmls.loader.bean.MsgListBean;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.SmsResBean;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.mine.bean.MyInviteListBean;
import com.dy.cmls.mine.bean.MyInviteResBean;
import com.dy.cmls.mine.bean.UserPointBean;
import com.dy.cmls.mine.resBean.PersonInfo;
import com.dy.cmls.mine.resBean.SettingBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by lcjing on 2019/1/3.
 */

public class PersonLoader extends ObjectLoader {
    private static final PersonLoader INSTANCE = new PersonLoader();
    private static final ThisService thisService = RetrofitServiceManager.getInstance().create(ThisService.class);

    public static PersonLoader getInstance() {
        return INSTANCE;
    }

    public Observable<UserInfoBean> getUserIndex(String urlName, String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getUserIndex(id));
    }


    public Observable<MsgListBean> getMsgList(String urlName, String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getMsgList(id));
    }

    public Observable<MsgInfoBean> getMsgInfo(String urlName, String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getMsgInfo(id));
    }

    public Observable<SettingBean> getSetting(String urlName) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getSetting());
    }

    public Observable<SettingBean> getAbout(String urlName) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getAbout());
    }

    public Observable<PersonInfo> getUserInfo(String urlName, String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getUserInfo(id));
    }


    //修改头像
    public Observable<SimpleResBean> updateHeadImage(String urlName, RequestBody file) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.updateHeadImage(file));
    }

    //修改昵称
    public Observable<SimpleResBean> modifyNick(String urlName, String id, String nick) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.modifyNick(id, nick));
    }


    //修改手机号 获取验证码
    public Observable<SmsResBean> modifyPhoneSms(String urlName, String phone) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.modifyPhoneSms(phone));
    }

    //修改手机号 获取验证码-新手机
    public Observable<SmsResBean> modifyNewPhoneSms(String urlName, String phone) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.modifyNewPhoneSms(phone));
    }

    //修改手机号 -提交
    public Observable<SimpleResBean> modifyPhone(String urlName, String phone,String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.modifyPhone(phone,id));
    }
    //忘记支付密码 获取验证码-新手机
    public Observable<SmsResBean> payPassSms(String urlName, String phone) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.payPassSms(phone));
    }

    //重置交易密码
    public Observable<SimpleResBean> setNewPayPass(String urlName, String pass,String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.setNewPayPass(pass,id));
    }

    //修改交易密码
    public Observable<SimpleResBean> modPayPass(String urlName, String odlPass,String newPass,String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.modPayPass(odlPass,newPass,id));
    }

    //设置交易密码
    public Observable<SimpleResBean> setPayPass(String urlName, String pass,String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.setPayPass(pass,id));
    }

    //修改登录密码
    public Observable<SimpleResBean> modPass(String urlName, String odlPass,String newPass,String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.modPass(odlPass,newPass,id));
    }
    //个人中心—我的邀请码
    public Observable<MyInviteResBean> getSpreadInfo(String urlName,String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getSpreadInfo(id));
    }
    //邀请列表
    public Observable<MyInviteListBean> getSpreadList(String urlName,String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getSpreadList(id));
    }

    //我的会员列表界面
    public Observable<MyInviteListBean> getSpreadMemList(String urlName,String id,String type,String page) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getSpreadMemList(id,type,page));
    }

    //实名认证获取图片
    public Observable<SimpleResBean> getRealImage(String urlName, String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getRealImage(id));
    }


    //实名认证上传图片
    public Observable<SimpleResBean> updateImage(String urlName, RequestBody file) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.updateImage(file));
    }
    //实名认证
    public Observable<SimpleResBean> realNameSubmit(String urlName, String id,String name,String idcard) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.realNameSubmit(id,name,idcard));
    }


    //我的积分
    public Observable<UserPointBean> getPoints(String urlName, String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getPoints(id));
    }


    //使用权 界面信息
    public Observable<SimpleResBean> getUse(String urlName, String id) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getUse(id));
    }



    public interface ThisService {

        //个人中心—我的
        @FormUrlEncoded
        @POST("User/index")
        Observable<UserInfoBean> getUserIndex(@Field("userid") String id);


        //消息列表
        @FormUrlEncoded
        @POST("Msg/index")
        Observable<MsgListBean> getMsgList(@Field("userid") String id);


        //消息详情  id-消息id
        @FormUrlEncoded
        @POST("Msg/info")
        Observable<MsgInfoBean> getMsgInfo(@Field("id") String id);


        //设置
        @POST("Help/web")
        Observable<SettingBean> getSetting();

        //关于我们
        @POST("Setting/about")
        Observable<SettingBean> getAbout();

        //消息列表
        @FormUrlEncoded
        @POST("User/info")
        Observable<PersonInfo> getUserInfo(@Field("userid") String id);

        //修改头像
        @POST("User/avatar")
        Observable<SimpleResBean> updateHeadImage(@Body RequestBody file);

        //修改昵称
        @FormUrlEncoded
        @POST("User/nickname")
        Observable<SimpleResBean> modifyNick(@Field("userid") String id, @Field("nickname") String nickname);

        //修改手机号 获取验证码
        @FormUrlEncoded
        @POST("Setting/yz_sms")
        Observable<SmsResBean> modifyPhoneSms(@Field("phone") String phone);


        //修改手机号 获取验证码 -新手机
        @FormUrlEncoded
        @POST("Setting/sms")
        Observable<SmsResBean> modifyNewPhoneSms(@Field("phone") String phone);

        //修改手机号 提交
        @FormUrlEncoded
        @POST("Setting/mod_phone")
        Observable<SimpleResBean> modifyPhone(@Field("phone") String phone,@Field("userid") String userid);


        //忘记交易密码-短信验证
        @FormUrlEncoded
        @POST("Setting/pass_sms")
        Observable<SmsResBean> payPassSms(@Field("phone") String phone);

        //忘记交易密码-设置密码
        @FormUrlEncoded
        @POST("Setting/get_pass")
        Observable<SimpleResBean> setNewPayPass(@Field("pass") String pass,@Field("userid") String userid);


        //交易密码-修改密码
        @FormUrlEncoded
        @POST("Setting/mod_pass")
        Observable<SimpleResBean> modPayPass(@Field("old_pass") String oldPass,@Field("new_pass") String newPass,@Field("userid") String userid);



        //交易密码-设置密码
        @FormUrlEncoded
        @POST("Setting/set_pass")
        Observable<SimpleResBean> setPayPass(@Field("pass") String pass,@Field("userid") String userid);

        //修改登录密码
        @FormUrlEncoded
        @POST("Password/modify")
        Observable<SimpleResBean> modPass(@Field("oldpass") String oldPass,@Field("newpass") String newPass,@Field("userid") String userid);




        //个人中心—我的邀请码
        @FormUrlEncoded
        @POST("Spread/info")
        Observable<MyInviteResBean> getSpreadInfo(@Field("userid") String userid);


        //邀请列表
        @FormUrlEncoded
        @POST("Spread/myfriends")
        Observable<MyInviteListBean> getSpreadList(@Field("userid") String userid);

        //获取实名认证图片
        @FormUrlEncoded
        @POST("Realname/member_image")
        Observable<SimpleResBean> getRealImage(@Field("userid") String userid);

        //实名认证图片上传
        @POST("Realname/image")
        Observable<SimpleResBean> updateImage(@Body RequestBody file);

        //实名认证
        @FormUrlEncoded
        @POST("Realname/submit")
        Observable<SimpleResBean> realNameSubmit(@Field("userid") String userid,@Field("realname") String realname
                ,@Field("idcard") String idcard);


        @FormUrlEncoded
        @POST("User/points")
        Observable<UserPointBean> getPoints(@Field("userid") String userid);

        //我的会员列表界面
        @FormUrlEncoded
        @POST("Spread/mem_list")
        Observable<MyInviteListBean> getSpreadMemList(@Field("userid") String userid,@Field("direct") String direct,
                                                      @Field("page") String page);


        //使用权界面信息
        @FormUrlEncoded
        @POST("Use/info")
        Observable<SimpleResBean> getUse(@Field("userid") String userid);

        @FormUrlEncoded
        @POST("MemberAddress/index")
        Observable<SimpleResBean> getAddressList(@Field("userid") String userid);




    }
}
