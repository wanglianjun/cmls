package com.dy.cmls.mhome;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuyVipActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener{

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.cb_wx)
    CheckBox cbWx;
    @BindView(R.id.cb_zfb)
    CheckBox cbZfb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_buy_vip);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        cbZfb.setOnCheckedChangeListener(this);
        cbWx.setOnCheckedChangeListener(this);
        tvContent.setText("VIP权益：成为本平台的终身VIP会员，享有最高级别会员权益。\n" +
                "                  享受直接推荐会员及间接推荐的所有会员的奖励。\n" +
                "                  一次升级，终身受益。");

    }

    @OnClick({R.id.iv_back, R.id.ll_wx, R.id.ll_zfb, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_wx:
                cbWx.setChecked(true);
                cbZfb.setChecked(false);
                break;
            case R.id.ll_zfb:
                cbZfb.setChecked(true);
                cbWx.setChecked(false);
                break;
            case R.id.tv_confirm:
                break;
        }
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
          return;
        }
        switch (buttonView.getId()){
            case R.id.cb_zfb:
                cbWx.setChecked(false);
                break;
            case R.id.cb_wx:
                cbZfb.setChecked(false);
                break;
        }
    }
}
