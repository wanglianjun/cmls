package com.dy.cmls.mall.selmall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.mall.selmall.activity.OrderInfoActivity;
import com.dy.cmls.mall.selmall.activity.ReturnApplyActivity;
import com.dy.cmls.mall.selmall.adapter.OrderAdapter;
import com.dy.cmls.mall.selmall.bean.GoodsBean;
import com.dy.cmls.mall.selmall.bean.OrderBean;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.dy.cmls.view.pop.AfterSaleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/27.
 */

public class OrderListFragment extends BaseListFragment {
    List<OrderBean> list;
    private int type;

    public static OrderListFragment getInstance(int type) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initAdapter() {
        type = getArguments().getInt("type", 0);
        list = new ArrayList<>();
        adapter = new OrderAdapter(R.layout.item_sel_order, list, getContext(), type);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_cancel:
                        break;
                    case R.id.tv_shouhou1://申请售后
                    case R.id.tv_shouhou:
                        AfterSaleView afterSaleView=new AfterSaleView(getContext(), new TJCallBack() {
                            @Override
                            public void callBack(Intent intent) {
                                String type = intent.getStringExtra("type");
                                Bundle bundle =new Bundle();
                                bundle.putString("type",type);
                                jumpToPage(ReturnApplyActivity.class,bundle);
                            }
                        });
                        afterSaleView.show(vTop);
                        break;
                    case R.id.tv_del:
                        break;
                    case R.id.fl_goods:
                    case R.id.tv_info:
                        Bundle info=new Bundle();
                        info.putString("status",list.get(position).getStatus());
                        jumpToPage(OrderInfoActivity.class,info);
                        break;
                    case R.id.tv_shouhuo://收货
                        break;

                    case R.id.tv_eva://评价
                        break;
                    case R.id.tv_fk://付款
                        break;
                }

            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle info=new Bundle();
                info.putString("status",list.get(position).getStatus());
                jumpToPage(OrderInfoActivity.class,info);
            }
        });
        requestList(true, true, true);
    }

    int page = 0;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            page = 0;
            list.clear();
        }
        page++;

        List<String> status=new ArrayList<>();
        status.add("待付款");
        status.add("待付款");
        status.add("待发货");
        status.add("待收货");
        status.add("待评价");
        status.add("交易成功");
        status.add("交易关闭");
        showProgressDialog();
        for (int i = 0; i < 7; i++) {
            String sta="";
            if (type==0) {
                sta=status.get(i);
            }else sta=status.get(type);
            List<GoodsBean> goodsBeans = new ArrayList<>();
            goodsBeans.add(new GoodsBean("农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"
                    , "1", "600.00", "5斤装"));
            list.add(new OrderBean( sta,"588.99","1", goodsBeans));
            goodsBeans = new ArrayList<>();
            goodsBeans.add(new GoodsBean("农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"
                    , "1", "600.00", "5斤装"));
            goodsBeans.add(new GoodsBean("瓷罐金骏眉茶叶礼盒装武夷山金俊眉正宗红茶", "199.99",
                    "2", "http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg"));
            list.add(new OrderBean(sta, "1588.99","3", goodsBeans));

        }
        adapter.notifyDataSetChanged();
        setRefresh(false);
        dismissProgressDialog();
        if (page > 2) {
            adapter.loadMoreEnd(false);
        } else
            adapter.loadMoreComplete();
        dismissProgressDialog();
    }

}
