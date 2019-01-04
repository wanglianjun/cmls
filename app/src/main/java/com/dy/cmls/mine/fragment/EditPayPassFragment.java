package com.dy.cmls.mine.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.SmsResBean;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.utils.TextUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by lcjing on 2019/1/4.
 */

public class EditPayPassFragment extends BaseFragment {

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
    Unbinder unbinder;
    @BindView(R.id.tv_phone_name)
    TextView tvPhoneName;
    @BindView(R.id.et_phone)
    TextView tvPhone;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.ll_code)
    LinearLayout llCode;
    @BindView(R.id.tv_next)
    TextView tvNext;

    private String type;

    public static EditPayPassFragment getInstance(String type) {
        EditPayPassFragment fragment = new EditPayPassFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_pay_pass, null);
        unbinder = ButterKnife.bind(this, view);
        type = getArguments().getString("type");
        return view;
    }

    @Override
    protected void initView(View view) {
        switch (type) {
            case "支付密码":
                llPhone.setVisibility(View.GONE);
                llOldPass.setVisibility(View.GONE);
                llCode.setVisibility(View.GONE);
                tvPass.setText("设置密码");
                etNewPass.setHint("请输入6位数字的支付密码");
                etConfirmPass.setHint("请再次输入支付密码");
                break;
            case "修改支付密码":
                llPhone.setVisibility(View.GONE);
                llCode.setVisibility(View.GONE);
                break;
            case "忘记支付密码":
                llOldPass.setVisibility(View.GONE);
                tvPhone.setText(SPUtils.getUserPhone());
                etNewPass.setHint("请输入6位数字的支付密码");
                etConfirmPass.setHint("请再次输入支付密码");
                break;

        }
    }


    @OnClick({R.id.tv_get_code,R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_get_code:
                if(tvGetCode.getText().toString().contains("获取")){
                    getCode();
                }
                break;
            case R.id.tv_next:
                switch (type) {
                    case "支付密码":
                        setPayPass();
                        break;
                    case "修改支付密码":
                        modifyPayPass();
                        break;
                    case "忘记支付密码":
                        if(checkCode()){
                            forget();
                        }
                        break;

                }
                break;
        }
    }

    private void setPayPass() {
        String pass = etNewPass.getText().toString();
        if (StringUtils.isEmpty(pass)) {
            showToast("请输入支付密码");
            return;
        }
        String checkPass=TextUtils.checkPass(etNewPass, etConfirmPass);
        if (!StringUtils.isEmpty(checkPass)) {
            ToastUtils.showShort(checkPass);
            return;
        }
        PersonLoader.getInstance().setPayPass("设置交易密码-安全中心", pass, SPUtils.getUserId()).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                ToastUtils.showShort(bean.getMessage());
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("设置交易密码:报异常2:", throwable.toString());
            }
        });
    }

    private void modifyPayPass(){
        String oldPass=etOldPass.getText().toString();
        if (StringUtils.isEmpty(oldPass)) {
            showToast("请输入原来的支付密码");
            return;
        }
        String pass = etNewPass.getText().toString();
        if (StringUtils.isEmpty(pass)) {
            showToast("请输入新的支付密码");
            return;
        }
        String checkPass=TextUtils.checkPass(etNewPass, etConfirmPass);
        if (!StringUtils.isEmpty(checkPass)) {
            ToastUtils.showShort(checkPass);
            return;
        }

        PersonLoader.getInstance().modPayPass("修改交易密码-安全中心", oldPass,pass, SPUtils.getUserId()).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                ToastUtils.showShort(bean.getMessage());
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("修改交易密码:报异常2:", throwable.toString());
            }
        });
    }


    private void getCode() {
        String phone = tvPhone.getText().toString();
//        if (StringUtils.isEmpty(phone)) {
//            showToast("请输入手机号");
//            return;
//        }
        PersonLoader.getInstance().payPassSms("修改交易密码获取验证码-安全中心", phone).subscribe(new Action1<SmsResBean>() {
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
                showLog("修改交易密码获取验证码:报异常2:", throwable.toString());
            }
        });
    }

    private void forget(){

        String pass = etNewPass.getText().toString();
        if (StringUtils.isEmpty(pass)) {
            showToast("请输入新的支付密码");
            return;
        }
        String checkPass=TextUtils.checkPass(etNewPass, etConfirmPass);
        if (!StringUtils.isEmpty(checkPass)) {
            ToastUtils.showShort(checkPass);
            return;
        }

        PersonLoader.getInstance().setNewPayPass("忘记交易密码-安全中心",pass, SPUtils.getUserId()).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                ToastUtils.showShort(bean.getMessage());
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("忘记交易密码:报异常2:", throwable.toString());
            }
        });

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

    private int countSeconds = 60;
    private String yanzhengma;

    // 开始倒计时
    private void startCountBack() {
        if (getActivity()==null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
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




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
