package com.dy.cmls.base;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.view.CustomLoadMoreView;
import com.dy.cmls.view.ErrorHintView;
import com.dy.cmls.view.interfaces.AbstractVerticalScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public abstract class BaseListActivity extends BaseActivity {

    @BindView(R.id.viewStatusBar)
   public View viewStatusBar;
    @BindView(R.id.ivLeft)
    public ImageView ivLeft;
    @BindView(R.id.tvTitleTitle)
    public TextView tvTitleTitle;
    @BindView(R.id.ivRight)
    public ImageView ivRight;
    @BindView(R.id.tvRight)
    public TextView tvRight;
    @BindView(R.id.recyclerView)
    public RecyclerView recyclerView;
    @BindView(R.id.ehv)
    public ErrorHintView ehv;
    @BindView(R.id.waveSwipeRefreshLayout)
    public WaveSwipeRefreshLayout waveSwipeRefreshLayout;
//    @BindView(R.id.v_line)
//    public View vLine;


    public BaseQuickAdapter adapter;


    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setEhv(ErrorHintView ehv) {
        this.ehv = ehv;
    }

    public void setWaveSwipeRefreshLayout(WaveSwipeRefreshLayout waveSwipeRefreshLayout) {
        this.waveSwipeRefreshLayout = waveSwipeRefreshLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.BgWhiteFontBlack);
        setContentView(R.layout.activity_base_list);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        initAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//加载动画
        adapter.isFirstOnly(true);//是否只显示一次动画
        adapter.setNotDoAnimationCount(1);//没有动画的view个数
        adapter.setLoadMoreView(new CustomLoadMoreView());//设置上拉view
        adapter.loadMoreEnd(true);//隐藏上拉view
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//            }
//        });
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//
//            }
//        });
        //上拉监听
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                requestList(false,false,false);
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
        int[] a = { R.color.white };
        waveSwipeRefreshLayout.setShadowRadius(5);
        waveSwipeRefreshLayout.setColorSchemeResources(a);//进度色
        //下拉监听
        waveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do work to refresh the list here.
                requestList(false,true,true);
            }
        });
    }

    public void setRefresh(boolean refresh){
        waveSwipeRefreshLayout.setRefreshing(refresh);
    }

    protected abstract void initAdapter();//适配器实例化  配置点击事件

    protected abstract void requestList(final boolean isFirst, final boolean isRefresh, final boolean isLoading);


    @OnClick({R.id.ivLeft, R.id.ivRight, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight:
                ivRightClick();
                break;
            case R.id.tvRight:
                tvRightClick();
                break;
        }
    }

    public void ivRightClick(){
    }
    public void tvRightClick(){
    }
}
