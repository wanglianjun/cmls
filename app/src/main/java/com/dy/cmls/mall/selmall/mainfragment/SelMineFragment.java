package com.dy.cmls.mall.selmall.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.mall.selmall.activity.CollectListActivity;
import com.dy.cmls.mall.selmall.activity.MyEvaActivity;
import com.dy.cmls.mall.selmall.activity.MyOrderActivity;
import com.dy.cmls.mall.selmall.activity.OrderListActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lcjing on 2018/12/25.
 */

public class SelMineFragment extends BaseFragment {

    @BindView(R.id.viewStatusBar)
    View viewStatusBar;
    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.riv_head)
    RoundedImageView rivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.fl_dfk)
    FrameLayout flDfk;
    @BindView(R.id.fl_dfh)
    FrameLayout flDfh;
    @BindView(R.id.fl_dsh)
    FrameLayout flDsh;
    @BindView(R.id.fl_dpj)
    FrameLayout flDpj;
    @BindView(R.id.ll_order)
    LinearLayout llOrder;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.ll_pingjia)
    LinearLayout llPingjia;
    @BindView(R.id.ll_shouhou)
    LinearLayout llShouhou;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sel_mine, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        tvTitleTitle.setText("我的");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({R.id.ivLeft,R.id.riv_head, R.id.fl_dfk, R.id.fl_dfh, R.id.fl_dsh, R.id.fl_dpj, R.id.ll_order, R.id.ll_collect, R.id.ll_pingjia, R.id.ll_shouhou})
    public void onViewClicked(View view) {
        Bundle bundle=new Bundle();
        switch (view.getId()) {
            case R.id.ivLeft:
                if (getActivity() != null) {
                    getActivity().finish();
                }
                break;
            case R.id.riv_head:
                break;
            case R.id.fl_dfk:
                bundle.putInt("type",1);
                jumpToPage(MyOrderActivity.class,bundle);
                break;
            case R.id.fl_dfh:
                bundle.putInt("type",2);
                jumpToPage(MyOrderActivity.class,bundle);
                break;
            case R.id.fl_dsh:
                bundle.putInt("type",3);
                jumpToPage(MyOrderActivity.class,bundle);
                break;
            case R.id.fl_dpj:
                bundle.putInt("type",4);
                jumpToPage(MyOrderActivity.class,bundle);
                break;
            case R.id.ll_order:
                jumpToPage(MyOrderActivity.class);
                break;
            case R.id.ll_collect:
                jumpToPage(CollectListActivity.class);
                break;
            case R.id.ll_pingjia:
                jumpToPage(MyEvaActivity.class);
                break;
            case R.id.ll_shouhou:
                Bundle shouhou=new Bundle();
                shouhou.putInt("type", CMLSConstant.ORDER_SHOUHOU);
                jumpToPage(OrderListActivity.class,shouhou);
                break;
        }
    }
}
