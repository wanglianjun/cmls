package com.dy.cmls.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.view.CustomLoadMoreView;
import com.dy.cmls.view.ErrorHintView;
import com.dy.cmls.view.interfaces.AbstractVerticalScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

/**
 * Created by lcjing on 2018/11/26.
 */

public abstract class BaseListFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ehv)
    ErrorHintView ehv;
    @BindView(R.id.waveSwipeRefreshLayout)
    WaveSwipeRefreshLayout waveSwipeRefreshLayout;
    Unbinder unbinder;
    public BaseQuickAdapter adapter;
    @BindView(R.id.v_top)
    public View vTop;


    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setEhv(ErrorHintView ehv) {
        this.ehv = ehv;
    }

    public void setWaveSwipeRefreshLayout(WaveSwipeRefreshLayout waveSwipeRefreshLayout) {
        this.waveSwipeRefreshLayout = waveSwipeRefreshLayout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        initAdapter();
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
                requestList(false, false, false);
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
                requestList(false, true, true);
            }
        });
    }

    public void setRefresh(boolean refresh) {
        waveSwipeRefreshLayout.setRefreshing(refresh);
    }

    protected abstract void initAdapter();//适配器实例化  配置点击事件

    protected abstract void requestList(final boolean isFirst, final boolean isRefresh, final boolean isLoading);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
