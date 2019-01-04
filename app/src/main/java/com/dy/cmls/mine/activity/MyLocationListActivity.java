package com.dy.cmls.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mine.AddLocationActivity;
import com.dy.cmls.mine.adapter.LocationAdapter;
import com.dy.cmls.mine.bean.LocationBean;
import com.dy.cmls.view.CustomLoadMoreView;
import com.dy.cmls.view.interfaces.AbstractVerticalScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class MyLocationListActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.v_top)
    View vTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.waveSwipeRefreshLayout)
    WaveSwipeRefreshLayout waveSwipeRefreshLayout;
    List<LocationBean> list;
    LocationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_locatin_list);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("收货地址");
        adapter = new LocationAdapter(R.layout.item_location, list, this);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_default:
                        setDefault(position);
                        break;
                    case R.id.iv_delete:
                        break;
                    case R.id.iv_edit:
                        Bundle bundle=new Bundle();
                        bundle.putBoolean("isEdit",true);
                        bundle.putString("id",list.get(position).getId());
                        jumpToPage(AddLocationActivity.class,bundle);
                        break;
                }
            }
        });
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


    private void setDefault(int position){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIdDefault(position==i);
        }
        adapter.notifyDataSetChanged();
    }


    //BRVAH的初始化
    public void initBRVAH() {
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

    private int page;

    private void requestList(boolean refresh) {
        if (refresh) {
            list.clear();
            page = 0;
        }
        page++;
        list.add(new LocationBean("李丽丽", "1233456655", "山东省济南市历山北路黄台电子商务产业园A厅16楼1616室", true));
        list.add(new LocationBean("李丽丽", "1233456655", "山东省济南市历山北路黄台电子商务产业园A厅16楼1616室"));
        list.add(new LocationBean("李丽丽", "1233456655", "山东省济南市历山北路黄台电子商务产业园A厅16楼1616室"));

        adapter.notifyDataSetChanged();
        setRefresh(false);
        if (Integer.parseInt("2") <= page) {
            adapter.loadMoreEnd(false);
        } else
            adapter.loadMoreComplete();
    }

    public void setRefresh(boolean refresh) {
        waveSwipeRefreshLayout.setRefreshing(refresh);
    }

    @OnClick({R.id.ivLeft,R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_add:
                jumpToPage(AddLocationActivity.class);
                break;
        }

    }
}
