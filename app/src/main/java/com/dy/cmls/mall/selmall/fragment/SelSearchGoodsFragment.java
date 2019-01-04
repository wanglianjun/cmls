package com.dy.cmls.mall.selmall.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lcjing on 2018/12/24.
 */

public class SelSearchGoodsFragment extends BaseFragment {

    @BindView(R.id.tv_colligate)
    TextView tvColligate;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.fl_list)
    FrameLayout flList;
    Unbinder unbinder;
    @BindView(R.id.iv_count)
    ImageView ivCount;
    @BindView(R.id.iv_price)
    ImageView ivPrice;
    private GoodsListFragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sel_search, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        fragment = new GoodsListFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.fl_list, fragment).commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private int count=0,price=0;
    public void setKey(String key){
        fragment.setKey(key);
    }

    @OnClick({R.id.tv_colligate, R.id.ll_count, R.id.ll_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_colligate:
                price=0;
                ivPrice.setImageResource(R.mipmap.btn_hui);
                tvPrice.setTextColor(Color.parseColor("#ff999999"));
                count=0;
                ivCount.setImageResource(R.mipmap.btn_hui);
                tvCount.setTextColor(Color.parseColor("#ff999999"));
                fragment.setShort("");
                break;
            case R.id.ll_count:
                if (count==0||count==2) {
                    count=1;
                    ivCount.setImageResource(R.mipmap.btn_xia);
                    fragment.setShort("count=1");
                }else {
                    count=2;
                    ivCount.setImageResource(R.mipmap.btn_shang);
                    fragment.setShort("count==2");
                }
                tvCount.setTextColor(Color.parseColor("#FFFE9D35"));
                price=0;
                ivPrice.setImageResource(R.mipmap.btn_hui);
                tvPrice.setTextColor(Color.parseColor("#ff999999"));
                break;
            case R.id.ll_price:
                if (price==0||price==2) {
                    price=1;
                    ivPrice.setImageResource(R.mipmap.btn_xia);
                    fragment.setShort(" price=1");
                }else {
                    price=2;
                    ivPrice.setImageResource(R.mipmap.btn_shang);
                    fragment.setShort(" price=2");
                }
                tvPrice.setTextColor(Color.parseColor("#FFFE9D35"));
                count=0;
                ivCount.setImageResource(R.mipmap.btn_hui);
                tvCount.setTextColor(Color.parseColor("#ff999999"));
                break;
        }
    }
}
