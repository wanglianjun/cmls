package com.dy.cmls.mkabao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.mkabao.activity.AddCreditCardActivity;
import com.dy.cmls.mkabao.activity.CreditCardInfoActivity;
import com.dy.cmls.mkabao.activity.ZhangdanActivity;
import com.dy.cmls.mkabao.adapter.KabaoAdapter;
import com.dy.cmls.mkabao.bean.KabaoBean;
import com.dy.cmls.view.CustomLoadMoreView;
import com.dy.cmls.view.ErrorHintView;
import com.dy.cmls.view.interfaces.AbstractVerticalScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

/**
 * Created by lcjing on 2018/12/6.
 */

public class KaBaoFragment extends BaseFragment {



    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fl_empty)
    FrameLayout flEmpty;
    @BindView(R.id.ehv)
    ErrorHintView ehv;
    @BindView(R.id.waveSwipeRefreshLayout)
    WaveSwipeRefreshLayout waveSwipeRefreshLayout;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kabao, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        initAdapter();
    }


    @OnClick({R.id.tv_left, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left://账单
                jumpToPage(ZhangdanActivity.class);
                break;
            case R.id.iv_right:
                jumpToPage(AddCreditCardActivity.class);
                break;
        }
    }

    private List<KabaoBean> list = new ArrayList<>();
    private KabaoAdapter adapter;

    protected void initAdapter() {
        adapter = new KabaoAdapter(R.layout.item_kabao, list, getContext());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                jumpToPage(CreditCardInfoActivity.class);
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addOnScrollListener(new AbstractVerticalScrollListener() {
            @Override
            public void onScrolledToTop() {
                super.onScrolledToTop();
                waveSwipeRefreshLayout.setEnabled(true);
            }

            @Override
            public void onScrolledToBottom() {
                super.onScrolledToBottom();
                waveSwipeRefreshLayout.setEnabled(false);
            }

            @Override
            public void onScrolledUp() {
                super.onScrolledUp();
                waveSwipeRefreshLayout.setEnabled(false);
            }

            @Override
            public void onScrolledDown() {
                super.onScrolledDown();
                waveSwipeRefreshLayout.setEnabled(false);
            }
        });
        initBRVAH();
        initRefreshView();
        requestList(true);
    }

    int page = 0;

    protected void requestList( boolean isRefresh) {
        if (isRefresh) {
            list.clear();
            page = 0;
        }
        page++;
        for (int i = 0; i < 10; i++) {
            list.add(new KabaoBean("", "中国银行（1608）", "06", "22", "2000.00"));
        }
        adapter.notifyDataSetChanged();
        setRefresh(false);
        if (list.size() > 20) {
            adapter.loadMoreEnd(false);
        } else
            adapter.loadMoreComplete();


        if (list.size() < 1) {
            flEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            flEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }


    }

    //BRVAH的初始化
    public void initBRVAH() {
        if (adapter == null) {
            return;
        }
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//加载动画
        adapter.isFirstOnly(true);//是否只显示一次动画
        adapter.setNotDoAnimationCount(1);//没有动画的view个数
        adapter.setLoadMoreView(new CustomLoadMoreView());//设置上拉view
        adapter.loadMoreEnd(true);//隐藏上拉view

        //上拉监听
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                requestList(false);
//                waveSwipeRefreshLayout.setEnabled(false);
            }
        }, recyclerView);
        //数据适配
        recyclerView.setAdapter(adapter);
        adapter.setEmptyView(R.layout.base_empty);
    }

    public void initRefreshView() {
        //下拉控件初始化
        waveSwipeRefreshLayout.setWaveARGBColor(CMLSConstant.REFRESH_A, CMLSConstant.REFRESH_R,
                CMLSConstant.REFRESH_G, CMLSConstant.REFRESH_B);//背景色
        int[] a = {R.color.white};
        waveSwipeRefreshLayout.setShadowRadius(5);
        waveSwipeRefreshLayout.setColorSchemeResources(a);//进度色
        //下拉监听
        waveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do work to refresh the list here.
                requestList(true);
            }
        });
    }

    public void setRefresh(boolean refresh) {
        waveSwipeRefreshLayout.setRefreshing(refresh);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
