package com.dy.cmls.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.mkabao.activity.AddCreditCardActivity;
import com.dy.cmls.utils.SPUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

//安全中心
public class SafetyCenterActivity extends BaseActivity {

    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_shiming)
    TextView tvShiming;
    @BindView(R.id.tv_pay_pass)
    TextView tvPayPass;
    @BindView(R.id.tv_login_pass)
    TextView tvLoginPass;
    @BindView(R.id.tv_card)
    TextView tvCard;
    private String color = "#FFFF9626";
    private String color9 = "#FF999999";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_safety_center);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserIndex();
    }


    private void getUserIndex() {
        showProgressDialog();
        PersonLoader.getInstance().getUserIndex("获取用户信息-安全中心", SPUtils.getUserId()).subscribe(new Action1<UserInfoBean>() {
            @Override
            public void call(UserInfoBean userInfoBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(userInfoBean.getStatus())) {
                    UserInfoBean.InfoBean info = userInfoBean.getInfo();
//                    if (StringUtils.isEmpty(info.getPhone())) {
//                        tvPhone.setText("");
//                    }
                    if ("0".equals(info.getPaypass())) {
                        tvPayPass.setText("去设置");
                        tvPayPass.setTextColor(Color.parseColor(color9));
                    } else {
                        tvPayPass.setText("已设置");
                        tvPayPass.setTextColor(Color.parseColor(color));
                    }
                    if ("0".equals(info.getIs_confirm())) {
                        tvShiming.setText("去认证");
                        tvShiming.setTextColor(Color.parseColor(color9));
                    } else {
                        tvShiming.setText("已认证");
                        tvShiming.setTextColor(Color.parseColor(color));
                    }
                    if ("0".equals(info.getCash_bank())) {
                        tvCard.setText("去绑定");
                        tvCard.setTextColor(Color.parseColor(color9));
                    } else {
                        String tvNum=info.getBank_num();
                        if (info.getBank_num().length()>12) {
                            tvNum=info.getBank_num().substring(4,12);
                            tvNum=info.getBank_num().replace(tvNum,"********");
                        }
                        tvCard.setText(tvNum);
                        tvCard.setTextColor(Color.parseColor(color));
                    }


                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("获取用户信息-安全中心:报异常2:", throwable.toString());
            }
        });
    }

    @OnClick({R.id.ivLeft, R.id.ll_phone, R.id.ll_shiming, R.id.ll_pay_pass, R.id.ll_login_pass, R.id.ll_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ll_phone:
                Bundle phone = new Bundle();
                phone.putString("type", "手机号");
                jumpToPage(SafetyEditActivity.class, phone);
                break;
            case R.id.ll_shiming:

                jumpToPage(SafetyShiMingActivity.class);
                break;
            case R.id.ll_pay_pass:
                Bundle payPass = new Bundle();
                if (tvPayPass.getText().equals("去设置")) {
                    payPass.putString("type", "支付密码");
                }else {
                    payPass.putString("type", "修改支付密码");
                }
                jumpToPage(SafetyEditActivity.class, payPass);
                break;
            case R.id.ll_login_pass:
                Bundle loginPass = new Bundle();
                loginPass.putString("type", "修改登录密码");
                jumpToPage(SafetyEditActivity.class, loginPass);
                break;
            case R.id.ll_card:
                if(tvCard.getText().toString().contains("去")){
                    jumpToPage(BindCardActivity.class);
                }else
                jumpToPage(SafetyCardListActivity.class);
                break;
        }
    }
}
