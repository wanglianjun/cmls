package com.dy.cmls.mall.selmall.fragment;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.mall.selmall.adapter.EvaGoodsAdapter;
import com.dy.cmls.mall.selmall.bean.MyEvaBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/26.
 */

public class GoodsEvaFragment extends BaseListFragment {
    List<MyEvaBean> list;
    @Override
    protected void initAdapter() {
        list=new ArrayList<>();
        adapter=new EvaGoodsAdapter(R.layout.item_sel_goods_eva,list,getContext());
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
        for (int i = 0; i < 5; i++) {
            list.add(new MyEvaBean(""));
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
