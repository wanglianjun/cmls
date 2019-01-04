package com.dy.cmls.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.mine.AddLocationActivity;
import com.dy.cmls.mine.adapter.LocationAdapter;
import com.dy.cmls.mine.bean.AddressListBean;
import com.dy.cmls.mine.bean.LocationBean;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.view.CustomLoadMoreView;
import com.dy.cmls.view.interfaces.AbstractVerticalScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import rx.functions.Action1;

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
//                        setDefault(position);
                        break;
                    case R.id.iv_delete:
                        del(position);
                        break;
                    case R.id.iv_edit:
                        Bundle bundle=new Bundle();
                        bundle.putBoolean("isEdit",true);
                        bundle.putString("id",list.get(position).getId());
                        bundle.putString("name",list.get(position).getName());
                        bundle.putString("location",list.get(position).getLocation());
                        bundle.putString("pId",list.get(position).getpId());
                        bundle.putString("cId",list.get(position).getcId());
                        bundle.putString("aId",list.get(position).getaId());
                        bundle.putString("address",list.get(position).getAddressInfo());
                        bundle.putString("phone",list.get(position).getPhone());
                        bundle.putBoolean("isDef",list.get(position).isIdDefault());
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

    private void del(int position){
        showProgressDialog();
        PersonLoader.getInstance().delAddress("删除收货地址", SPUtils.getUserId(),list.get(position).getId()).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {

                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                   requestList(true);
                }
                ToastUtils.showShort(bean.getMessage());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("删除收货地址:报异常2:", throwable.toString());

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestList(true);
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



    private void requestList(boolean refresh) {
        if (refresh) {
            list.clear();
        }

        showProgressDialog();
        PersonLoader.getInstance().getAddressList("获取收货地址", SPUtils.getUserId()).subscribe(new Action1<AddressListBean>() {
            @Override
            public void call(AddressListBean bean) {

                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    for (int i = 0; i < bean.getInfo().size(); i++) {
                        list.add(new LocationBean(bean.getInfo().get(i)));
                    }
                }
                adapter.notifyDataSetChanged();
                setRefresh(false);
                adapter.loadMoreEnd(false);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                adapter.notifyDataSetChanged();
                setRefresh(false);

                    adapter.loadMoreEnd(false);
                dismissProgressDialog();
                showToastFailure();
                showLog("获取收货地址:报异常2:", throwable.toString());

            }
        });
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
