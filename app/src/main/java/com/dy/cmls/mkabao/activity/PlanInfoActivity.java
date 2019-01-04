package com.dy.cmls.mkabao.activity;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListActivity;
import com.dy.cmls.mkabao.adapter.PlanInfoAdapter;
import com.dy.cmls.mkabao.bean.PlanInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/7.
 */

public class PlanInfoActivity extends BaseListActivity {
    List<PlanInfoBean> list;

    @Override
    protected void initAdapter() {
        tvTitleTitle.setText("计划详情");
        list = new ArrayList<>();
        adapter = new PlanInfoAdapter(R.layout.item_plan_info, list, this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!list.get(position).isOpen()) {
                    for (PlanInfoBean bean:list) {
                        bean.setOpen(false);
                    }
                }
                list.get(position).setOpen(!list.get(position).isOpen());
                adapter.notifyDataSetChanged();
            }
        });
        requestList(true,false,false);

    }

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        list.clear();
        list.add(new PlanInfoBean("初次消费","1000.00","DH78945632"
                ,"XF624153468","2.00","2018-02-10","已处理"));
        list.add(new PlanInfoBean("二次消费","1000.00","DH78945632"
                ,"XF624153468","2.00","2018-02-10","已处理"));
        list.add(new PlanInfoBean("三次消费","1000.00","DH78945632"
                ,"XF624153468","2.00","2018-02-10","已处理"));
        list.add(new PlanInfoBean("还款","1000.00","DH78945632"
                ,"XF624153468","2.00","2018-02-10","已处理"));
        setRefresh(false);
        adapter.loadMoreEnd(false);
//        if (list.size() > 20) {
//            adapter.loadMoreEnd(false);
//        } else
//            adapter.loadMoreComplete();
    }
}
