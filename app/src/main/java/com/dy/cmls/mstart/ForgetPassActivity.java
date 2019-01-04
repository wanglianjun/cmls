package com.dy.cmls.mstart;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.LoginLoader;
import com.dy.cmls.loader.bean.RegisterResBean;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.SmsResBean;
import com.dy.cmls.utils.CharCheckUtil;
import com.dy.cmls.utils.MyBDLocationManager;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.utils.TextUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class ForgetPassActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_new_pass)
    EditText etNewPass;
    @BindView(R.id.et_confirm_pass)
    EditText etConfirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("忘记密码");
        String phone = getIntent().getStringExtra("phone");
        if (!StringUtils.isEmpty(phone)) {
            etPhone.setText(phone);
        }
    }


    @OnClick({R.id.ivLeft, R.id.tv_get_code, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_get_code:
                if (!tvGetCode.getText().toString().contains("获取")) {
                    return;
                }
                getCode();
                break;
            case R.id.tv_confirm:
                String checkCode = TextUtils.checkCode(smsCode, etCode.getText().toString());
                if (!StringUtils.isEmpty(checkCode)) {
                    ToastUtils.showShort(checkCode);
                    return;
                }
                String checkPass = TextUtils.checkPass(etNewPass, etConfirmPass);
                if (!StringUtils.isEmpty(checkPass)) {
                    ToastUtils.showShort(checkPass);
                    return;
                }
                passModify(phone, etNewPass.getText().toString());
                break;
        }
    }

    private String smsCode = CMLSConstant.DEFAULT_SMS_CODE;
    private String phone;

    private void getCode() {
        phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort("请输入手机号");
            return;
        }
        showProgressDialog();
        LoginLoader.getInstance().getPassCode("忘记密码-获取验证码", phone).subscribe(new Action1<SmsResBean>() {
            @Override
            public void call(SmsResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    if (bean.getInfo().getCode() != null) {
                        smsCode = bean.getInfo().getCode();
                        ToastUtils.showShort("发送成功");
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
                showLog("忘记密码-获取验证码:报异常2:", throwable.getMessage());
            }
        });
    }


    private void passModify(final String phone, final String pass) {
        showProgressDialog();
        LoginLoader.getInstance().passModify("忘记密码-修改密码", phone, pass).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    showToast(bean.getMessage());
                    finish();
                } else if (bean.getMessage() != null) {
                    ToastUtils.showShort(bean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("忘记密码-修改密码:报异常2:", throwable.toString());
            }
        });
    }

    CountDownTimer mTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvGetCode.setText("|   " + millisUntilFinished / 1000 + "秒");
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
}
