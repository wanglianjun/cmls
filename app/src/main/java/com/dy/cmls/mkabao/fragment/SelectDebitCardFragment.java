package com.dy.cmls.mkabao.fragment;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.mkabao.adapter.SelectCardAdapter;
import com.dy.cmls.mkabao.bean.CardBean;
import com.dy.cmls.view.interfaces.ListBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/29.
 */

public class SelectDebitCardFragment extends BaseListFragment {



    private ListBack back;

    public void setBack(ListBack back) {
        this.back = back;
    }

    private List<CardBean> list;
    @Override
    protected void initAdapter() {
        list=new ArrayList<>();
        adapter=new SelectCardAdapter(R.layout.item_select_card,list,getContext());
        ((SelectCardAdapter)adapter).setType("储蓄卡");
        adapter.setOnItemClickListener((adapter, view, position) -> {
            if (back!=null) {
                back.back(2,list.get(position).getId());
            }
        });
        requestList(true,true,true);

    }

    int page=0;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            list.clear();
            page = 0;
        }
        page++;
        list.add(new CardBean("6228480263096603 216","农业银行"));
        list.add(new CardBean("6214835411782277","招商银行"));
        list.add(new CardBean("6228480263096603 216","农业银行"));
        adapter.notifyDataSetChanged();
        setRefresh(false);
        if (Integer.parseInt("2") <= page) {
            adapter.loadMoreEnd(false);
        } else
            adapter.loadMoreComplete();
    }
}