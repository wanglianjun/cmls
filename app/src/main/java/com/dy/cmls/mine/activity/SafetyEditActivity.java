package com.dy.cmls.mine.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.SmsResBean;
import com.dy.cmls.mine.fragment.EditPayPassFragment;
import com.dy.cmls.utils.CharCheckUtil;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.utils.TextUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class SafetyEditActivity extends BaseActivity {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tv_phone_name)
    TextView tvPhoneName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.ll_code)
    LinearLayout llCode;
    @BindView(R.id.et_old_pass)
    EditText etOldPass;
    @BindView(R.id.ll_old_pass)
    LinearLayout llOldPass;
    @BindView(R.id.tv_pass)
    TextView tvPass;
    @BindView(R.id.et_new_pass)
    EditText etNewPass;
    @BindView(R.id.ll_new_pass)
    LinearLayout llNewPass;
    @BindView(R.id.et_confirm_pass)
    EditText etConfirmPass;
    @BindView(R.id.ll_confirm_pass)
    LinearLayout llConfirmPass;
    @BindView(R.id.tv_next)
    TextView tvNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_edit);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        String type = getIntent().getStringExtra("type");
        tvTitleTitle.setText(type);
        switch (type) {
            case "手机号":
                llConfirmPass.setVisibility(View.GONE);
                llNewPass.setVisibility(View.GONE);
                llOldPass.setVisibility(View.GONE);
                etPhone.setText(SPUtils.getUserPhone());
                break;
            case "修改登录密码":
                llPhone.setVisibility(View.GONE);
                llCode.setVisibility(View.GONE);
                tvNext.setText("确定");
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, EditPayPassFragment.getInstance(type)).commit();
                break;
//            case "支付密码":
//                llPhone.setVisibility(View.GONE);
//                llCode.setVisibility(View.GONE);
//                llOldPass.setVisibility(View.GONE);
//                tvPass.setText("设置密码");
//                etNewPass.setHint("请设置6位数字的支付密码");
//                etConfirmPass.setHint("请再次输入支付密码");
//                tvNext.setText("确定");
//                break;
//            case "修改支付密码":
//
//                break;
//            case "忘记支付密码":
//                break;
        }

    }

    @OnClick({R.id.ivLeft, R.id.tv_get_code, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_get_code:
                if (tvGetCode.getText().toString().contains("获取")) {
                    if (tvPhoneName.getText().toString().equals("新手机号")) {
                        getNewCode();
                    } else
                        getCode();
                }

                break;
            case R.id.tv_next:
                if (tvNext.getText().toString().equals("确定")) {
                    String type = getIntent().getStringExtra("type");
                    switch (type) {
                        case "手机号":
                            if (checkCode()) {
                                ToastUtils.showShort(type);
                                modifyPhone();
                            }
                            break;
                        case "修改登录密码":
                            modifyPass();
                            break;
                    }
                } else {
                    if (checkCode()) {
                        stopCount();
                        tvPhoneName.setText("新手机号");
                        etPhone.clearComposingText();
                        etPhone.setText("");
                        etPhone.setHint("请输入新手机号");
                        etCode.clearComposingText();
                        etCode.setText("");
                        etPhone.setHint("请输入新手机号");
                        tvNext.setText("确定");
                    }

                }
                break;
        }
    }

    private boolean checkCode() {
        if (StringUtils.isEmpty(yanzhengma)) {
            ToastUtils.showShort("请先获取验证码");
            return false;
        }
        if (StringUtils.isEmpty(etCode.getText().toString())) {
            ToastUtils.showShort("输入验证码");
            return false;
        }
        if (!yanzhengma.equals(etCode.getText().toString())) {
            ToastUtils.showShort("验证码输入错误");
            return false;
        }
        return true;
    }

    private void getCode() {
        String phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return;
        }
        PersonLoader.getInstance().modifyPhoneSms("修改手机号获取验证码-安全中心", phone).subscribe(new Action1<SmsResBean>() {
            @Override
            public void call(SmsResBean smsResBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(smsResBean.getStatus())) {
                    yanzhengma = smsResBean.getInfo().getCode();
                    startCountBack();
                }
                ToastUtils.showShort(smsResBean.getMessage());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("修改手机号获取验证码:报异常2:", throwable.toString());
            }
        });
    }

    private void getNewCode() {
        String phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return;
        }
        PersonLoader.getInstance().modifyNewPhoneSms("修改手机号-获取新手机号验证码-安全中心", phone).subscribe(new Action1<SmsResBean>() {
            @Override
            public void call(SmsResBean smsResBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(smsResBean.getStatus())) {
                    yanzhengma = smsResBean.getInfo().getCode();
                    startCountBack();
                }
                ToastUtils.showShort(smsResBean.getMessage());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("修改手机号-获取新手机号验证码:报异常2:", throwable.toString());
            }
        });
    }

    private void modifyPhone() {
        String phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return;
        }
        PersonLoader.getInstance().modifyPhone("修改手机号-安全中心", phone, SPUtils.getUserId()).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                ToastUtils.showShort(bean.getMessage());
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    SPUtils.setUserPhone(phone);
                    finish();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("修改手机号:报异常2:", throwable.toString());
            }
        });
    }

    private void modifyPass(){
        String oldPass=etOldPass.getText().toString();
        if (StringUtils.isEmpty(oldPass)) {
            showToast("请输入原来的登录密码");
            return;
        }
        String pass = etNewPass.getText().toString();
        if (StringUtils.isEmpty(pass)) {
            showToast("请输入新的登录密码");
            return;
        }
        String checkPass=TextUtils.checkPass(etNewPass, etConfirmPass);
        if (!StringUtils.isEmpty(checkPass)) {
            ToastUtils.showShort(checkPass);
            return;
        }

        PersonLoader.getInstance().modPass("修改登录密码-安全中心", oldPass,pass, SPUtils.getUserId()).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                ToastUtils.showShort(bean.getMessage());
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
//                    Bundle phone = new Bundle();
//                    phone.putString("type", "忘记支付密码");
//                    jumpToPage(SafetyEditActivity.class, phone);
                   finish();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("修改登录密码:报异常2:", throwable.toString());
            }
        });
    }


    private int countSeconds = 60;
    private String yanzhengma;

    // 开始倒计时
    private void startCountBack() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvGetCode.setText(countSeconds + "s");
                hanlder.sendEmptyMessage(0);
                tvGetCode.setClickable(false);
            }
        });
    }

    private void stopCount() {
        hanlder.removeMessages(0);
        countSeconds = 60;
        tvGetCode.setText("|   获取验证码");
        tvGetCode.setClickable(true);
    }

    private Handler hanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    if (countSeconds > 0) {
                        --countSeconds;
                        tvGetCode.setText(countSeconds + "s");
                        hanlder.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        countSeconds = 60;
                        tvGetCode.setText("|   重新获取");
                        tvGetCode.setClickable(true);
                    }
                    break;
                default:
                    break;
            }
        }
    };


}
