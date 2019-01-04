package com.dy.cmls.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.CrashLoader;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.ReceiveLoader;
import com.dy.cmls.loader.bean.BankListBean;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.SmsResBean;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.mine.bean.CrashBean;
import com.dy.cmls.mine.bean.CrashCardBean;
import com.dy.cmls.utils.CharCheckUtil;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.view.dialog.BindDialog;
import com.dy.cmls.view.dialog.NotifyDialog;
import com.dy.cmls.view.dialog.NotifyDialog2;
import com.dy.cmls.view.dialog.WhellViewDialog;
import com.dy.cmls.view.interfaces.TJCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class BindCardActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tvRealName)
    TextView tvRealName;
    @BindView(R.id.tvIDCrad)
    TextView tvIDCrad;
    @BindView(R.id.etBanksNumber)
    EditText etBanksNumber;
    @BindView(R.id.tvBankName)
    TextView tvBankName;
    @BindView(R.id.llBankName)
    LinearLayout llBankName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_card);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("添加银行卡");
        getCashCard();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (StringUtils.isEmpty(SPUtils.getIdCard())) {
            getUserIndex();
        }else {
            tvIDCrad.setText(SPUtils.getIdCard());
            tvRealName.setText(SPUtils.getRealName());
        }
    }

    @OnClick({R.id.ivLeft, R.id.idebankcard_ib, R.id.llBankName,R.id.tvGetCode, R.id.tvConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.idebankcard_ib:
                break;
            case R.id.llBankName:
                getBankList();
                break;
            case R.id.tvGetCode:
                if (tvGetCode.getText().toString().contains("获取")) {
                    getCode();
                }
                break;
            case R.id.tvConfirm:
                if (checkCode()) {
                    String phone = etPhone.getText().toString();
                    if (StringUtils.isEmpty(phone)) {
                        showToast("请输入手机号");
                        return;
                    }
                    String cardNum = etBanksNumber.getText().toString();
                    if (StringUtils.isEmpty(cardNum)) {
                        showToast("请输入银行卡号");
                        return;
                    }
                    if (StringUtils.isEmpty(bankId)) {
                        showToast("请选择发卡银行");
                        return;
                    }

                    bind(tvBankName.getText().toString(), cardNum, bankId, phone);
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

    private void getCashCard(){
        showProgressDialog();
        CrashLoader.getInstance().getCashCard("获取提现银行卡-绑定提现银行卡",SPUtils.getUserId()).subscribe(new Action1<CrashCardBean>() {
            @Override
            public void call(CrashCardBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
//                    tvBankName.setText(bean.getInfo().getBank_name());
//                    bankId=bean.getInfo().getBank_num();
                    etBanksNumber.setText(bean.getInfo().getBank_num());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("获取提现银行卡-绑定提现银行卡:报异常2:", throwable.toString());
            }
        });
    }

    private void bind(String bankName, String bankNum, String bankId, String phone) {
        showProgressDialog();
        CrashLoader.getInstance().cashBind("添加银行卡", SPUtils.getUserId(), bankName, bankNum, bankId, phone).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                ToastUtils.showShort(bean.getMessage());
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    setResult(1);
                    finish();
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("添加银行卡:报异常2:", throwable.toString());
            }
        });
    }

    private void getBankList() {
        showProgressDialog();
        ReceiveLoader.getInstance().getBank("获取银行列表-绑定提现银行卡").subscribe(new Action1<BankListBean>() {
            @Override
            public void call(BankListBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    showBankList(bean.getInfo());
                }else {
                    ToastUtils.showShort(bean.getMessage());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("获取银行列表-绑定提现银行卡:报异常2:", throwable.toString());
            }
        });
    }

    String bankId="";

    private void showBankList( List<BankListBean.BankBean> bankBeans){
        List<String > banks=new ArrayList<>();
        for (int i = 0; i < bankBeans.size(); i++) {
            banks.add(bankBeans.get(i).getName());
        }
        WhellViewDialog dialog = new WhellViewDialog(this, "选择银行", banks, new TJCallBack() {
            @Override
            public void callBack(Intent intent) {
                int index = intent.getIntExtra("callBack", 0);
                tvBankName.setText(banks.get(index));
                bankId=bankBeans.get(index).getNumber();
            }
        });
        dialog.show();
    }

    private void getUserIndex() {
        showProgressDialog();
        PersonLoader.getInstance().getUserIndex("获取用户信息-实名认证", SPUtils.getUserId()).subscribe(new Action1<UserInfoBean>() {
            @Override
            public void call(UserInfoBean userInfoBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(userInfoBean.getStatus())) {
                    UserInfoBean.InfoBean info = userInfoBean.getInfo();
                    if ("1".equals(info.getIs_confirm())) {
                        tvRealName.setText(info.getRealname());
                        tvIDCrad.setText(info.getIdcard());
                        SPUtils.setRealNameAndId(info.getIdcard(),info.getRealname());
                    }else {
                        BindDialog dialog=new BindDialog(new TJCallBack() {
                            @Override
                            public void callBack(Intent intent) {
                                jumpToPage(SafetyShiMingActivity.class);
                            }
                        },BindCardActivity.this, "实名认证后才能绑定银行卡","去认证");
                        dialog.show();
                    }



                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("获取用户信息-实名认证:报异常2:", throwable.toString());
            }
        });
    }


    private void getCode() {
        String phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return;
        }
        CrashLoader.getInstance().cashSms("获取验证码-绑定储蓄卡", phone).subscribe(new Action1<SmsResBean>() {
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
                showLog("获取验证码-绑定储蓄卡:报异常2:", throwable.toString());
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
