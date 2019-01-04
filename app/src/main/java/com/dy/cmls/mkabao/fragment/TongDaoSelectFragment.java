package com.dy.cmls.mkabao.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.mkabao.activity.SelectCardActivity;
import com.dy.cmls.mkabao.adapter.SelectTongDaoAdapter;
import com.dy.cmls.mkabao.bean.TongDaoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/29.
 */

public class TongDaoSelectFragment extends BaseListFragment {
    List<TongDaoBean> list;
    @Override
    protected void initAdapter() {
        list=new ArrayList<>();
        adapter=new SelectTongDaoAdapter(R.layout.item_select_shoukuan_tongdao,list,getContext());
//        adapter.setEnableLoadMore(false);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_sk:
                        jumpToPage(SelectCardActivity.class);
                        break;
                }
            }
        });

        requestList(true,true,true);

    }

    private String money="0";

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        list.clear();
        list.add(new TongDaoBean("收款通道1","","55","100","单笔限制最低消费500元",
                "立即到帐","08:00-22:00",money));
        list.add(new TongDaoBean("收款通道2","","43","100","单笔限制最低消费500元",
                "立即到帐","08:00-22:00",money));
        adapter.notifyDataSetChanged();
        setRefresh(false);
        adapter.loadMoreEnd(false);

    }

    public void getData(String money){
        this.money=money;
        requestList(true,true,true);
    }
}
