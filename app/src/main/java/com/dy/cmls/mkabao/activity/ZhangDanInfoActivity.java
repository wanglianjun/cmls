package com.dy.cmls.mkabao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhangDanInfoActivity extends BaseActivity {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_card_num)
    TextView tvCardNum;
    @BindView(R.id.tv_ser_num)
    TextView tvSerNum;
    @BindView(R.id.tv_fee_money)
    TextView tvFeeMoney;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhang_dan_info);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("账单详情");

    }

    @OnClick({R.id.ivLeft, R.id.tv_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_money:
                break;
        }
    }
}
