package com.dy.cmls.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.CrashLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.mine.bean.CrashBean;
import com.dy.cmls.mine.bean.CrashCardBean;
import com.dy.cmls.utils.GlideUtils;
import com.dy.cmls.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class SafetyCardListActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.fl_bind)
    FrameLayout flBind;
    @BindView(R.id.iv_bank_icon)
    ImageView ivBankIcon;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_card_type)
    TextView tvCardType;
    @BindView(R.id.tv_card_num)
    TextView tvCardNum;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.fl_card)
    FrameLayout flCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_card_list);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("银行卡");
        tvName.setText(SPUtils.getRealName());

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCashCard();
    }

    private void getCashCard() {
        showProgressDialog();
        CrashLoader.getInstance().getCashCard("获取银行卡信息-银行卡", SPUtils.getUserId()).subscribe(new Action1<CrashCardBean>() {
            @Override
            public void call(CrashCardBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    flCard.setVisibility(View.VISIBLE);
                    flBind.setVisibility(View.GONE);
                    tvBankName.setText(bean.getInfo().getBank_name());
                    tvCardNum.setText(bean.getInfo().getBank_num());
                    GlideUtils.setImage(ivBankIcon, R.mipmap.ic_card, bean.getInfo().getBank_logo());
                } else {
                    ToastUtils.showShort(bean.getMessage());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("获取银行卡信息-银行卡:报异常2:", throwable.toString());
            }
        });
    }


    private void unbind() {
        showProgressDialog();
        CrashLoader.getInstance().cashUnbind("解绑提现卡-银行卡", SPUtils.getUserId()).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                ToastUtils.showShort(bean.getMessage());
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    flCard.setVisibility(View.GONE);
                    flBind.setVisibility(View.VISIBLE);
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("解绑提现卡-银行卡:报异常2:", throwable.toString());
            }
        });
    }

    @OnClick({R.id.ivLeft, R.id.tv_bind, R.id.tv_unbind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_bind:
                jumpToPage(BindCardActivity.class);
                break;
            case R.id.tv_unbind:
                unbind();
                break;
        }
    }
}
