package com.dy.cmls.mall.multimall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mall.selmall.activity.PayActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SellerEnterActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tv_month_1)
    TextView tvMonth1;
    @BindView(R.id.tv_month_3)
    TextView tvMonth3;
    @BindView(R.id.tv_month_12)
    TextView tvMonth12;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_into);
        ButterKnife.bind(this);
        initView();
    }

    private String type="3";

    @Override
    protected void initView() {
        tvTitleTitle.setText("商家入驻");

    }

    @OnClick({R.id.ivLeft, R.id.tv_month_1, R.id.tv_month_3, R.id.tv_month_12,R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_month_1:
                type="1";
                tvMonth1.setBackground(getResources().getDrawable(R.mipmap.selected));
                tvMonth3.setBackground(getResources().getDrawable(R.drawable.rectangle_gray_empty_5));
                tvMonth12.setBackground(getResources().getDrawable(R.drawable.rectangle_gray_empty_5));
                break;
            case R.id.tv_month_3:
                type="2";
                tvMonth1.setBackground(getResources().getDrawable(R.drawable.rectangle_gray_empty_5));
                tvMonth3.setBackground(getResources().getDrawable(R.mipmap.selected));
                tvMonth12.setBackground(getResources().getDrawable(R.drawable.rectangle_gray_empty_5));
                break;
            case R.id.tv_month_12:
                type="3";
                tvMonth1.setBackground(getResources().getDrawable(R.drawable.rectangle_gray_empty_5));
                tvMonth3.setBackground(getResources().getDrawable(R.drawable.rectangle_gray_empty_5));
                tvMonth12.setBackground(getResources().getDrawable(R.mipmap.selected));
                break;
            case R.id.tv_confirm:
                jumpToPage(PayActivity.class,true,1);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle=new Bundle();
        bundle.putString("type","pay");
        jumpToPage(EnterStatusActivity.class,bundle);
        finish();
        if(requestCode==1&&resultCode==1){//支付成功
//            Bundle bundle=new Bundle();
//            bundle.putString("type","pay");
//            jumpToPage(EnterStatusActivity.class,bundle);
//            finish();
        }else if(requestCode==1&&resultCode==2){//支付失败

        }
    }
}
