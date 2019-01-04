package com.dy.cmls.mall.selmall.activity;

import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListActivity;
import com.dy.cmls.mall.selmall.adapter.SelCollectionAdapter;
import com.dy.cmls.mall.selmall.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/25.
 */

public class CollectListActivity extends BaseListActivity {

    List<GoodsBean> list;

    @Override
    protected void initAdapter() {
        tvTitleTitle.setText("收藏");
        list = new ArrayList<>();
        adapter = new SelCollectionAdapter(R.layout.item_sel_collect, list, this);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_add_cart:
                        ToastUtils.showShort(list.get(position).getName()+"购物车");
                        break;
                    case R.id.tv_remove_collect:
                        ToastUtils.showShort(list.get(position).getName()+"移除");
                        break;
                }
            }
        });
        requestList(true,true,true);
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
        for (int i = 0; i < 5; i++) {
            list.add(new GoodsBean("农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装", "600.00"
                    , "1", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"));
            list.add(new GoodsBean("瓷罐金骏眉茶叶礼盒装武夷山金俊眉正宗红茶", "199.99",
                    "2", "http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg"));

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
