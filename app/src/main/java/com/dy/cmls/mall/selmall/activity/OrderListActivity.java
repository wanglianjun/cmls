package com.dy.cmls.mall.selmall.activity;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListActivity;
import com.dy.cmls.mall.selmall.adapter.OrderAdapter;
import com.dy.cmls.mall.selmall.bean.GoodsBean;
import com.dy.cmls.mall.selmall.bean.OrderBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/25.
 */

public class OrderListActivity extends BaseListActivity {


    List<OrderBean> list;
    private int type;

    @Override
    protected void initAdapter() {
        type = getIntent().getIntExtra("type", 0);
        switch (type) {
            case CMLSConstant.ORDER_SHOUHOU:
                tvTitleTitle.setText("售后管理");
                break;
        }

        list = new ArrayList<>();
        adapter = new OrderAdapter(R.layout.item_sel_order, list, this, type);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                jumpInfo(position);
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                jumpInfo(position);
            }
        });
        requestList(true, true, true);
    }

    private void jumpInfo(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("id", list.get(position).getId());
        if (list.get(position).getType().equals("仅退款")) {
            bundle.putString("type", "tk");
        } else {
            bundle.putString("type", "th");
        }
        jumpToPage(ReturnInfoActivity.class, bundle);
    }

    int page = 0;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            page = 0;
            list.clear();
        }
        page++;
        showProgressDialog();
        showProgressDialog();
        for (int i = 0; i < 2; i++) {
            List<GoodsBean> goodsBeans = new ArrayList<>();
            goodsBeans.add(new GoodsBean("农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"
                    , "1", "600.00", "5斤装"));
            list.add(new OrderBean("仅退款", "退款成功", goodsBeans));
            goodsBeans = new ArrayList<>();
            goodsBeans.add(new GoodsBean("农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"
                    , "1", "600.00", "5斤装"));
            goodsBeans.add(new GoodsBean("瓷罐金骏眉茶叶礼盒装武夷山金俊眉正宗红茶", "199.99",
                    "2", "http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg"));
            list.add(new OrderBean("退款退货", "退款中", goodsBeans));

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
