package com.dy.cmls.mstart;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.LoginLoader;
import com.dy.cmls.loader.bean.AreaResBean;
import com.dy.cmls.loader.bean.RegisterResBean;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.SmsResBean;
import com.dy.cmls.mall.multimall.bean.AreaBean;
import com.dy.cmls.utils.LocationBack;
import com.dy.cmls.utils.MyBDLocationManager;
import com.dy.cmls.utils.MyLocationManager;
import com.dy.cmls.utils.PermissionUtils;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.utils.SoftHideKeyBoardUtil;
import com.dy.cmls.utils.TextUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class RegActivity extends BaseActivity implements SoftHideKeyBoardUtil.ChangedListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.v_top)
    View vTop;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.et_pass_2)
    EditText etPass2;
    @BindView(R.id.et_invite)
    EditText etInvite;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.v_bottom)
    View vBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        SoftHideKeyBoardUtil softHideKeyBoardUtil = SoftHideKeyBoardUtil.assistActivity(this);
        initView();
        softHideKeyBoardUtil.setChangedListener(this);
    }

    private List<String> areaList=new ArrayList<>();
    @Override
    protected void initView() {
 initLocation();

    }

    private void initLocation(){
        MyBDLocationManager.getInstance().setLocationBack(new LocationBack() {
            @Override
            public void success(List<String> list) {
                areaList.addAll(list);
                getArea("");
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtils.selfPermissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    && PermissionUtils.selfPermissionGranted(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                MyBDLocationManager.getInstance().start();
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                }, PermissionUtils.TAG_GPS);
                return;
            }

        }
    }

    private int position=0;

    private String areaCode="";

    private void getArea(String id) {
        showProgressDialog("正在获取位置您的信息");
        LoginLoader.getInstance().getArea("注册获取地址信息", id).subscribe(new Action1<AreaResBean>() {
            @Override
            public void call(AreaResBean areaBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(areaBean.getStatus())&&
                        position<areaList.size()) {
                    List<AreaBean> areaBeans=areaBean.getInfo();
                    for (int i = 0; i < areaBeans.size(); i++) {
                        if (areaBeans.get(i).getName().equals(areaList.get(position))
                                ||areaBeans.get(i).getName().contains(areaList.get(position))
                                ||areaList.get(position).contains(areaBeans.get(i).getName()) ){
                            position++;
                            if (position==2) {
                                areaCode=areaBeans.get(i).getId();
                            }else
                            getArea(areaBeans.get(i).getId());
                        }
                    }
                }else {
                    ToastUtils.showShort(areaBean.getMessage());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("注册获取地址信息:报异常2:", throwable.getMessage());
            }
        });
    }


    @OnClick({R.id.iv_close, R.id.tv_reg, R.id.tv_login,R.id.tv_get_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_get_code:

                if (tvGetCode.getText().toString().contains("获取")) {
//                    mTimer.start();
                    getCode();
                }
                break;
            case R.id.tv_reg:
                String checkCode=TextUtils.checkCode(smsCode,etCode.getText().toString());
                if (!StringUtils.isEmpty(checkCode)) {
                    ToastUtils.showShort(checkCode);
                    return;
                }
                String checkPass=TextUtils.checkPass(etPass,etPass2);
                if (!StringUtils.isEmpty(checkPass)) {
                    ToastUtils.showShort(checkPass);
                    return;
                }
                String inviteCode=etInvite.getText().toString();
//                if (StringUtils.isEmpty(inviteCode)) {
//                    ToastUtils.showShort("请输入邀请码");
//                    return;
//                }
                register(phone,etPass.getText().toString(),inviteCode);
                break;
            case R.id.tv_login:
                jumpToPage(LoginActivity.class);
                finish();
                break;
        }
    }

    private String smsCode=CMLSConstant.DEFAULT_SMS_CODE;

    private String phone;

    private void getCode() {
        phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return;
        }
        showProgressDialog();
        LoginLoader.getInstance().getRegisterCode("获取验证码", phone).subscribe(new Action1<SmsResBean>() {
            @Override
            public void call(SmsResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    if (bean.getInfo().getCode() != null) {
                        smsCode = bean.getInfo().getCode();
                        showToast("发送成功");
                        mTimer.start();
                    }
                } else if (bean.getMessage() != null) {
                    ToastUtils.showShort(bean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("获取验证码:报异常2:", throwable.getMessage());
            }
        });
    }


    private void register(final String phone, final String pass,String inviteCode) {
        showProgressDialog();
        LoginLoader.getInstance().register("注册", phone, inviteCode,areaCode, pass).subscribe(new Action1<RegisterResBean>() {
            @Override
            public void call(RegisterResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    showToast(bean.getMessage());
                    SPUtils.setUserInfo(bean.getInfo().getId(),bean.getInfo().getPhone(),bean.getInfo().getNick_name());
                    finish();
                } else if (bean.getMessage() != null) {
                    ToastUtils.showShort(bean.getMessage());
                    if(bean.getMessage().contains("城市ID")){
                        initLocation();
                    }
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("注册:报异常2:", throwable.toString());
            }
        });
    }

    CountDownTimer mTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvGetCode.setText("|   "+millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            tvGetCode.setEnabled(true);
            tvGetCode.setText("|   获取验证码");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyBDLocationManager.getInstance().stop();
        mTimer.cancel();
    }

    @Override
    public void onKeyBoardChanged(boolean isShow) {
        if (isShow) {
            vTop.setVisibility(View.GONE);
            tvTitle.setVisibility(View.VISIBLE);
            llPhone.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            vBottom.setVisibility(View.VISIBLE);
        } else {
            vTop.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.GONE);
            llPhone.setBackgroundColor(Color.parseColor("#00000000"));
            vBottom.setVisibility(View.GONE);
        }
//        vTop.setVisibility(isShow ? View.GONE : View.VISIBLE);
//        tvTitle.setVisibility(!isShow ? View.GONE : View.VISIBLE);
    }


    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull final int[] grantResults) {
        MyBDLocationManager.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
