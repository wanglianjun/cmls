package com.dy.cmls.mall.multimall.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.view.MRatingBar;
import com.dy.cmls.view.dialog.RedPacketDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SellerDesActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.riv_seller_logo)
    RoundedImageView rivSellerLogo;
    @BindView(R.id.tv_seller_name)
    TextView tvSellerName;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.star_goods)
    MRatingBar starGoods;
    @BindView(R.id.tv_score_goods)
    TextView tvScoreGoods;
    @BindView(R.id.star_logistics)
    MRatingBar starLogistics;
    @BindView(R.id.tv_score_logistics)
    TextView tvScoreLogistics;
    @BindView(R.id.star_service)
    MRatingBar starService;
    @BindView(R.id.tv_score_service)
    TextView tvScoreService;
    @BindView(R.id.iv_red_packet)
    ImageView ivRedPacket;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_num)
    TextView tvNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_des);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("店铺简介");
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.mipmap.nav_share);
        starGoods.setStar(5);
        tvScoreGoods.setText("5");
        starLogistics.setStar(5);
        tvScoreLogistics.setText("5");
        starService.setStar(4);
        tvScoreService.setText("4");
    }

    @OnClick({R.id.ivLeft, R.id.ivRight, R.id.ll_nav,R.id.iv_red_packet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight:
                break;
            case R.id.ll_nav:
                break;
            case R.id.iv_red_packet:
                RedPacketDialog redPacketDialog=new RedPacketDialog();
                redPacketDialog.show(getSupportFragmentManager(),"");
                break;
        }
    }
}
