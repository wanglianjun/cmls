package com.dy.cmls.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class BuyUsufructActivity extends BaseActivity {

    @BindView(R.id.viewStatusBar)
    View viewStatusBar;
    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.cb_wx)
    CheckBox cbWx;
    @BindView(R.id.cb_zfb)
    CheckBox cbZfb;
    @BindView(R.id.tv_des)
    TextView tvDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_usufruct);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("购买使用权");
        cbZfb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbWx.setChecked(false);
                }
            }
        });
        cbWx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbZfb.setChecked(false);
                }
            }
        });
        getInfo();
    }

    private void getInfo() {
        showProgressDialog();
        PersonLoader.getInstance().getUse("使用权", SPUtils.getUserId()).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    tvPrice.setText(bean.getInfo().getUse_price());
                    tvDes.setText("说明："+bean.getInfo().getUse_info());
                } else {
                    ToastUtils.showShort(bean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("使用权:报异常2:", throwable.toString());
            }
        });
    }

    @OnClick({R.id.ivLeft, R.id.tv_confirm, R.id.ll_wx, R.id.ll_zfb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ll_wx:
                cbWx.setChecked(true);
                break;
            case R.id.ll_zfb:
                cbZfb.setChecked(true);
                break;
            case R.id.tv_confirm:
                break;
        }
    }
}
