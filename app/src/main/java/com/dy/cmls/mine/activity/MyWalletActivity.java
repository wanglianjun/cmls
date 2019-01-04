package com.dy.cmls.mine.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.CrashLoader;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.mine.bean.CrashBean;
import com.dy.cmls.utils.ArithUtil;
import com.dy.cmls.utils.GlideUtils;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.view.paydialog.PayPasswordDialog;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class MyWalletActivity extends BaseActivity {

    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_rash_des)
    TextView tvRashDes;
    @BindView(R.id.et_crash)
    EditText etCrash;
    @BindView(R.id.tv_pay_fre)
    TextView tvPayFre;
    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_card_num)
    TextView tvCardNum;
    @BindView(R.id.tv_fre_des)
    TextView tvFreDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        getCashInfo();
        etCrash.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String a = etCrash.getText().toString();
                if (StringUtils.isEmpty(a)) {
                    a="0";
                }
                try {
                    double money=Double.parseDouble(a);
                    double feeMoney=money/100*fee;
                    tvPayFre.setText(String.format("%.2f", feeMoney));
                } catch (Exception e) {
                    ToastUtils.showShort("您输入的提现金额不合法");
                }


            }
        });
    }

    private double fee = 2;
    private double canM = 0;
    private double minM = 0;
    //可提现金额
    private String canMoney = "0.00";
    //最低提现金额
    private String memberCashMin = "0.00";

    private void getCashInfo() {
        showProgressDialog();
        CrashLoader.getInstance().getCashInfo("获取提现信息-我的钱包", SPUtils.getUserId()).subscribe(new Action1<CrashBean>() {
            @Override
            public void call(CrashBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    tvYue.setText(bean.getInfo().getAccount_money());
                    if (StringUtils.isEmpty(bean.getInfo().getBank_name())) {
                        tvBankName.setText("去绑定");
                        tvCardNum.setText("您还没有绑定储蓄卡");
                    } else {
                        tvBankName.setText(bean.getInfo().getBank_name());
                        String num= bean.getInfo().getBank_num();
                        tvCardNum.setText("尾号" + num.substring(num.length()-4) + "储蓄卡");
                        GlideUtils.setImage(ivBankIcon,R.mipmap.bg_yhk,bean.getInfo().getBank_logo());
                    }
                    tvFreDes.setText("说明：手续费为提现金额的" + bean.getInfo().getMember_cash_fee() + "%");
                    tvPayFre.setText("0.00");
                    canMoney = bean.getInfo().getCan_money();
                    memberCashMin = bean.getInfo().getMember_cash_min();
                    tvRashDes.setText("可提现金额：" + bean.getInfo().getCan_money());
                    fee = Double.parseDouble(bean.getInfo().getMember_cash_fee());
                    canM = Double.parseDouble(bean.getInfo().getCan_money());
                    minM = Double.parseDouble(bean.getInfo().getMember_cash_min());
                }else {
                    ToastUtils.showShort(bean.getMessage());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("获取提现信息-我的钱包:报异常2:", throwable.toString());
            }
        });
    }

    private void showPayDialog(String money) {
        new PayPasswordDialog.Builder(this)
                .setTitle("请输入支付密码")
                .setSubTitle("提现金额￥" + money)
//                .setMoney("提现金额￥1564.00")
                //.setAutoDismiss(false) // 设置点击按钮后不关闭对话框
                .setListener(new PayPasswordDialog.OnListener() {

                    @Override
                    public void complete(Dialog dialog, String password) {
                        cashApply(password,money);
                    }

                    @Override
                    public void cancel(Dialog dialog) {
                        ToastUtils.showShort("取消了");
                    }
                })
                .show();
    }

    private void cashApply(String pass,String money){
        showProgressDialog();
        CrashLoader.getInstance().cashApply("申请提现-我的钱包", SPUtils.getUserId(),money,pass)
                .subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                ToastUtils.showShort(bean.getMessage());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("申请提现-我的钱包:报异常2:", throwable.toString());
            }
        });
    }

    @OnClick({R.id.iv_left, R.id.tv_right, R.id.tv_all, R.id.tv_confirm, R.id.tv_log, R.id.fl_bind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right://明细
                Bundle info = new Bundle();
                info.putString("title", "明细");
                jumpToPage(WalletListActivity.class, info);
                break;
            case R.id.tv_all://全部提现
                etCrash.setText(canMoney);
                break;
            case R.id.tv_confirm://确定
                String money = etCrash.getText().toString();
                if (StringUtils.isEmpty(money)) {
                    ToastUtils.showShort("请输入提现金额");
                    return;
                }
                double m = Double.parseDouble(money);

                if (canM < m) {
                    ToastUtils.showShort("余额不足");
                    return;
                }
                if (minM > m) {
                    ToastUtils.showShort("提现金额不能少于"+memberCashMin+"元");
                    return;
                }
                showPayDialog(money);
                break;
            case R.id.tv_log://提现记录
                Bundle log = new Bundle();
                log.putString("title", "提现记录");
                jumpToPage(WalletListActivity.class, log);
                break;
            case R.id.fl_bind:
                if (tvBankName.getText().equals("去绑定")) {
                    jumpToPage(BindCardActivity.class,true,1);
                } else {
                    jumpToPage(SafetyCardListActivity.class);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getCashInfo();
    }
}
