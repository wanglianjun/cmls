package com.dy.cmls.mall.selmall.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mkabao.activity.EditWuliuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//退款详情界面
public class ReturnInfoActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
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
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.tv_return_money)
    TextView tvReturnMoney;
    @BindView(R.id.tv_apply_time)
    TextView tvApplyTime;
    @BindView(R.id.tv_return_no)
    TextView tvReturnNo;
    @BindView(R.id.tv_reject)
    TextView tvReject;
    @BindView(R.id.ll_reject)
    LinearLayout llReject;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_header)
    LinearLayout llHeader;
    @BindView(R.id.tv_edit_wuliu)
    TextView tvEditWuliu;
    @BindView(R.id.ll_host_address)
    LinearLayout llHostAddress;
    @BindView(R.id.tv_wuliu)
    TextView tvWuliu;
    @BindView(R.id.ll_wuliu)
    LinearLayout llWuliu;
    @BindView(R.id.tv_apply_count)
    TextView tvApplyCount;
    @BindView(R.id.ll_apply_count)
    LinearLayout llApplyCount;
    @BindView(R.id.tv_refound_time)
    TextView tvRefoundTime;
    @BindView(R.id.ll_refund_time)
    LinearLayout llRefundTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrun_info);
        ButterKnife.bind(this);
        initView();
    }

    private String type = "th";

    @Override
    protected void initView() {
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "th":
                tvTitleTitle.setText("申请退货退款");
                break;
            case "tk":
                tvTitleTitle.setText("申请退款");
                break;
        }
        llReject.setVisibility(View.GONE);//驳回理由
        llHostAddress.setVisibility(View.GONE);//商家信息
        llWuliu.setVisibility(View.GONE);//物流信息
        llApplyCount.setVisibility(View.GONE);//申请件数
        llRefundTime.setVisibility(View.GONE);//退款时间
    }



    @OnClick({R.id.ivLeft,R.id.tv_edit_wuliu})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_edit_wuliu:
                jumpToPage(EditWuliuActivity.class);
                break;
        }


    }


}
