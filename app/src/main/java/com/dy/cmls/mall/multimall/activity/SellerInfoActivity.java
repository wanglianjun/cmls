package com.dy.cmls.mall.multimall.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mall.selmall.fragment.SelSearchGoodsFragment;
import com.dy.cmls.view.dialog.RedPacketDialog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SellerInfoActivity extends BaseActivity {

    @BindView(R.id.iv_seller_logo)
    RoundedImageView ivSellerLogo;
    @BindView(R.id.tv_seller_name)
    TextView tvSellerName;
    @BindView(R.id.fl_list)
    FrameLayout flList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_seller_info);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        SelSearchGoodsFragment fragment=new SelSearchGoodsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_list,fragment).commit();
    }

    @OnClick({R.id.iv_left, R.id.iv_share, R.id.ll_nav, R.id.iv_red_packet,R.id.ll_seller})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.ll_nav:
                break;
            case R.id.iv_red_packet:
                RedPacketDialog redPacketDialog=new RedPacketDialog();
                redPacketDialog.show(getSupportFragmentManager(),"");
                break;
            case R.id.ll_seller:
                jumpToPage(SellerDesActivity.class);
                break;
        }
    }
}
