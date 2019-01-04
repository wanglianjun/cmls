package com.dy.cmls.mkabao.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mkabao.adapter.ZhangDanAdapter;
import com.dy.cmls.mkabao.bean.ZhangdanBean;
import com.dy.cmls.view.CustomLoadMoreView;
import com.dy.cmls.view.ErrorHintView;
import com.dy.cmls.view.dialog.ZhangdanActionDialog;
import com.dy.cmls.view.interfaces.AbstractVerticalScrollListener;
import com.dy.cmls.view.interfaces.TJCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class ZhangdanActivity extends BaseActivity {

    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ehv)
    ErrorHintView ehv;
    @BindView(R.id.ivRight)
    public ImageView ivRight;
    @BindView(R.id.waveSwipeRefreshLayout)
    WaveSwipeRefreshLayout waveSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhangdan);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        initAdapter();
        requestList(true, false, false);
    }

    private List<ZhangdanBean> list = new ArrayList<>();
    private ZhangDanAdapter adapter;
    private String type = "1";
    private ZhangdanActionDialog zhangdanActionDialog;

    protected void initAdapter() {
        adapter = new ZhangDanAdapter(R.layout.item_zhangdan, list, this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                jumpToPage(ZhangDanInfoActivity.class);
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

    @OnClick({R.id.ivLeft, R.id.llRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.llRight:
                if (!rightShow) {
                    showRightPop();
                }
                break;
        }
    }

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
                requestList(false, false, false);
//                waveSwipeRefreshLayout.setEnabled(false);
            }
        }, recyclerView);
        //数据适配
        recyclerView.setAdapter(adapter);
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


    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        list.clear();
        switch (type) {
            case "1"://全部
                tvRight.setText("全部");
                for (int i = 0; i < 10; i++) {
                    list.add(new ZhangdanBean("还款"));
                    list.add(new ZhangdanBean("消费"));
                }
                break;
            case "2"://还款
                tvRight.setText("还款");
                for (int i = 0; i < 10; i++) {
                    list.add(new ZhangdanBean("还款"));
                }
                break;
            case "3"://消费
                tvRight.setText("消费");
                for (int i = 0; i < 10; i++) {
                    list.add(new ZhangdanBean("消费"));
                }
                break;
        }
        setRefresh(false);
        adapter.setEnableLoadMore(false);
        adapter.notifyDataSetChanged();
//        adapter.loadMoreComplete();
    }

    private boolean rightShow = false;


    private void showRightPop() {
        rightShow = true;
        ivRight.setImageResource(R.mipmap.nav_sl);
        if (zhangdanActionDialog == null) {
            zhangdanActionDialog = new ZhangdanActionDialog(this, new TJCallBack() {
                @Override
                public void callBack(Intent intent) {
                    type = intent.getStringExtra("callBack");
                    requestList(false, false, false);
                }
            });
            zhangdanActionDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    rightShow = false;
                    ivRight.setImageResource(R.mipmap.nav_xl);
                }
            });
        }
        zhangdanActionDialog.show();
    }


}
