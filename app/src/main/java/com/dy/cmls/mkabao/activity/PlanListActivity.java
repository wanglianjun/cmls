package com.dy.cmls.mkabao.activity;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListActivity;
import com.dy.cmls.mkabao.adapter.PlanAdapter;
import com.dy.cmls.mkabao.bean.HKPlanBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/6.
 */

public class PlanListActivity extends BaseListActivity {


    List<HKPlanBean> list;
    @Override
    protected void initAdapter() {
        list=new ArrayList<>();
        tvTitleTitle.setText("已执行计划");
        adapter=new PlanAdapter(R.layout.item_hk_plan,list,this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                jumpToPage(PlanInfoActivity.class);
            }
        });
        requestList(true,false,false);
    }

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        list.add(new HKPlanBean("1500.00","待执行","2.00","2018-11-28 14:32:20"));
        list.add(new HKPlanBean("1500.00","待执行","2.00","2018-11-28 14:32:20"));
        list.add(new HKPlanBean("1500.00","待执行","2.00","2018-11-28 14:32:20"));
        list.add(new HKPlanBean("1500.00","待执行","2.00","2018-11-28 14:32:20"));
        list.add(new HKPlanBean("1500.00","待执行","2.00","2018-11-28 14:32:20"));
        setRefresh(false);
        if (list.size() > 20) {
            adapter.loadMoreEnd(false);
        } else
            adapter.loadMoreComplete();
    }
}
