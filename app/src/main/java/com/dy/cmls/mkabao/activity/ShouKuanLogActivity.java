package com.dy.cmls.mkabao.activity;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseListActivity;
import com.dy.cmls.mkabao.adapter.ShoukuanLogAdapter;
import com.dy.cmls.mkabao.bean.ShouKuanLogBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/29.
 */

public class ShouKuanLogActivity extends BaseListActivity {
    List<ShouKuanLogBean> list;

    @Override
    protected void initAdapter() {
        tvTitleTitle.setText("收款记录");
        list = new ArrayList<>();
        adapter = new ShoukuanLogAdapter(R.layout.item_shoukuan_log, list, this);
        requestList(true, true, true);
    }

    private int page;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            list.clear();
            page = 0;
        }
        page++;
        list.add(new ShouKuanLogBean("成功", "2018-11-28 14:32:20", "通道名称￥5000.00"));
        list.add(new ShouKuanLogBean("成功", "2018-11-28 14:32:20", "通道名称￥5000.00"));
        list.add(new ShouKuanLogBean("成功", "2018-11-28 14:32:20", "通道名称￥5000.00"));
        list.add(new ShouKuanLogBean("失败", "2018-11-28 14:32:20", "通道名称￥5000.00"));
        list.add(new ShouKuanLogBean("失败", "2018-11-28 14:32:20", "通道名称￥5000.00"));
        list.add(new ShouKuanLogBean("失败", "2018-11-28 14:32:20", "通道名称￥5000.00"));
        adapter.notifyDataSetChanged();
        setRefresh(false);
        if (Integer.parseInt("2") <= page) {
            adapter.loadMoreEnd(false);
        } else
            adapter.loadMoreComplete();
    }
}
