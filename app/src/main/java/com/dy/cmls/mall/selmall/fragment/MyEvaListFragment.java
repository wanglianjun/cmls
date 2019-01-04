package com.dy.cmls.mall.selmall.fragment;

import android.os.Bundle;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.mall.selmall.adapter.MyEvaluateAdapter;
import com.dy.cmls.mall.selmall.bean.MyEvaBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/25.
 * 我的评价
 */

public class MyEvaListFragment extends BaseListFragment{

    List<MyEvaBean> list;

    public static MyEvaListFragment getInstance(boolean isMul){
        MyEvaListFragment fragment=new MyEvaListFragment();
        Bundle bundle=new Bundle();
        bundle.putBoolean("isMul",isMul);
        fragment.setArguments(bundle);
        return fragment;
    }

    private boolean isMul;

    @Override
    protected void initAdapter() {
        isMul=getArguments().getBoolean("isMul",false);
        list=new ArrayList<>();
        if (isMul) {
            adapter=new MyEvaluateAdapter(R.layout.item_mul_me_evaluate,list,getContext());
        }else
        adapter=new MyEvaluateAdapter(R.layout.item_sel_me_evaluate,list,getContext());
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
            list.add(new MyEvaBean("",""));
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
