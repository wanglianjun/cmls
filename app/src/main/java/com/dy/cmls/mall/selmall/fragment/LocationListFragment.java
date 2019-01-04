package com.dy.cmls.mall.selmall.fragment;


import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.mall.selmall.adapter.SelLocationAdapter;
import com.dy.cmls.mall.selmall.bean.LocationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/26.
 */

public class LocationListFragment extends BaseListFragment {

    List<LocationBean> list;

    @Override
    protected void initAdapter() {
        list=new ArrayList<>();
        adapter=new SelLocationAdapter(R.layout.item_sel_location,list,getContext());
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
            list.add(new LocationBean("赵军军","18666668959","山东省济南市天桥区黄台电子产业园1616"));
        }
        list.get(0).setDefault(true);
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
