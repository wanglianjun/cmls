package com.dy.cmls.mall.selmall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.mall.selmall.mainfragment.CartListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lcjing on 2018/12/24.
 */

public class CartFragment extends BaseFragment {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.ll_manage)
    LinearLayout llManage;
    @BindView(R.id.fl_list)
    FrameLayout flList;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    @BindView(R.id.tv_jiesuan)
    TextView tvJiesuan;
    Unbinder unbinder;
    @BindView(R.id.viewStatusBar)
    View viewStatusBar;

    private CartListFragment fragment;

    public static CartFragment getInstance(boolean isMul) {
        CartFragment cartFragment = new CartFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isMul", isMul);
        cartFragment.setArguments(bundle);
        return cartFragment;
    }

    private boolean isMul;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sel_cart, null);
        unbinder = ButterKnife.bind(this, view);
        isMul = getArguments().getBoolean("isMul", false);
        if (isMul) {
            viewStatusBar.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    protected void initView(View view) {
        fragment = new CartListFragment();
        fragment.setSelectListener((position, checked) -> {
            switch (position) {
                case 0://单选
                    checkTime = System.currentTimeMillis();
                    cbAll.setChecked(fragment.isSelectAll());
                    tvJiesuan.setText("结算(" + fragment.getCount() + ")");
                    break;
                case 1://全选
                    tvJiesuan.setText("结算(" + fragment.getCount() + ")");
                    break;
            }
        });
        tvTitleTitle.setText("购物车");
        tvRight.setText("管理");
        tvRight.setVisibility(View.VISIBLE);
        cbAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (System.currentTimeMillis() - checkTime > 50) {
                fragment.selectAll(isChecked);
            }
            checkTime = System.currentTimeMillis();

        });
        getChildFragmentManager().beginTransaction().replace(R.id.fl_list, fragment).commit();
    }

    private long checkTime = 0;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.v_check, R.id.tv_collect, R.id.tv_del, R.id.tv_jiesuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                if (getActivity() != null) {
                    getActivity().finish();
                }
                break;
            case R.id.tvRight:
                if (tvRight.getText().equals("管理")) {
                    llManage.setVisibility(View.VISIBLE);
                    tvRight.setText("完成");
                } else {
                    llManage.setVisibility(View.GONE);
                    tvRight.setText("管理");
                }
                break;
            case R.id.v_check:
                cbAll.setChecked(!cbAll.isChecked());
                break;
            case R.id.tv_collect:
                fragment.collect();
                break;
            case R.id.tv_del:
                fragment.delete();
                break;
            case R.id.tv_jiesuan:
                fragment.submit();
                break;
        }
    }
}
