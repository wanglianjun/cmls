package com.dy.cmls.mall.selmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.dy.cmls.view.pop.AfterSaleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInfoActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv_content)
    ImageView ivContent;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_liuyan)
    TextView tvLiuyan;
    @BindView(R.id.tv_zongjia)
    TextView tvZongjia;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.rv_order_des)
    RecyclerView rvOrderDes;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_del)
    TextView tvDel;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_fk)
    TextView tvFk;
    @BindView(R.id.tv_shouhuo)
    TextView tvShouhuo;
    @BindView(R.id.tv_eva)
    TextView tvEva;
    @BindView(R.id.ll_apply)
    LinearLayout llApply;
    @BindView(R.id.tv_shouhou_apply)
    TextView tvShouhouApply;
    @BindView(R.id.ll_button)
    LinearLayout llButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        ButterKnife.bind(this);
        initView();
    }

    private String status = "";

    @Override
    protected void initView() {
        status = getIntent().getStringExtra("status");
        tvTitleTitle.setText("订单详情");

        if (status.equals("待评价")) {
            tvStatus.setText("");
        }
        initStatus();
        initButton();
    }

    private void initStatus() {
        switch (status) {
            case "待付款":
                ivStatus.setImageResource(R.mipmap.ic_dfk1);
                tvStatus.setText(status);
                tvDes.setText("订单已经提交，请在01时30分钟内完成支付，超时订单将自动取消");
                break;
            case "待发货":
                ivStatus.setImageResource(R.mipmap.ic_dfh1);
                tvStatus.setText(status);
                tvDes.setText("支付已完成，正等待商家发货");
                break;
            case "待收货":
                ivStatus.setImageResource(R.mipmap.ic_dsh1);
                tvStatus.setText(status);
                tvDes.setText("还剩6天12时订单将自动确认");
                break;
            case "待评价":
                ivStatus.setImageResource(R.mipmap.ic_jycg);
                tvStatus.setText("交易成功");
                tvDes.setText("订单未进行评论");
                break;
            case "交易成功":
                ivStatus.setImageResource(R.mipmap.ic_jycg);
                tvStatus.setText(status);
                tvDes.setText("订单已完成");
                break;
            case "交易关闭":
                ivStatus.setImageResource(R.mipmap.ic_jygb);
                tvStatus.setText(status);
                tvDes.setText("交易已取消");
                break;
        }
    }


    private void initButton() {
        switch (status) {
            case "待付款":
                tvFk.setVisibility(View.VISIBLE);
                tvCancel.setVisibility(View.VISIBLE);
                llApply.setVisibility(View.GONE);
                break;
            case "待发货":
                llButton.setVisibility(View.GONE);
                break;
            case "待收货":
                tvShouhuo.setVisibility(View.VISIBLE);
                break;
            case "待评价":
                tvDel.setVisibility(View.VISIBLE);
                tvEva.setVisibility(View.VISIBLE);
                break;
            case "交易关闭":
                tvDel.setVisibility(View.VISIBLE);
                llApply.setVisibility(View.GONE);
                break;
            case "交易成功":
                tvDel.setVisibility(View.VISIBLE);
                break;
        }
    }

    @OnClick({R.id.ivLeft, R.id.tv_cancel, R.id.tv_del, R.id.tv_info, R.id.tv_fk,
            R.id.tv_shouhuo, R.id.tv_eva, R.id.tv_shouhou_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_cancel:
                break;
            case R.id.tv_del:
                break;
            case R.id.tv_shouhou_apply:
                AfterSaleView afterSaleView=new AfterSaleView(this, new TJCallBack() {
                    @Override
                    public void callBack(Intent intent) {
                        String type = intent.getStringExtra("type");
                        Bundle bundle =new Bundle();
                        bundle.putString("type",type);
                        jumpToPage(ReturnApplyActivity.class,bundle);
                    }
                });
                afterSaleView.show(tvTitleTitle);
                break;
            case R.id.tv_info:
                break;
            case R.id.tv_fk:
                break;
            case R.id.tv_shouhuo:
                break;
            case R.id.tv_eva:
                break;
        }
    }

}
