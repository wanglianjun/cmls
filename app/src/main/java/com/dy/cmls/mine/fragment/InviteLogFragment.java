package com.dy.cmls.mine.fragment;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.mine.adapter.InviteLogAdapter;
import com.dy.cmls.mine.bean.InviteLogBean;
import com.dy.cmls.mine.bean.MyInviteListBean;
import com.dy.cmls.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by lcjing on 2018/12/20.
 */

public class InviteLogFragment extends BaseListFragment {
    private List<InviteLogBean> logBeans;

    @Override
    protected void initAdapter() {
        logBeans = new ArrayList<>();
        adapter = new InviteLogAdapter(R.layout.item_invite_log, logBeans, getContext());
        requestList(true, true, true);
    }

    private int page = 0;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            page = 0;
            logBeans.clear();
        }
        page++;
        for (int i = 0; i < 2; i++) {
            logBeans.add(new InviteLogBean("13333333333", "2018-11-11 11:11:11", true));
            logBeans.add(new InviteLogBean("139****3333", "2018-11-11 11:11:11", true));
            logBeans.add(new InviteLogBean("138****8888", "2018-11-11 11:11:11", false));
            logBeans.add(new InviteLogBean("133****6666", "2018-11-11 11:11:11", false));
            logBeans.add(new InviteLogBean("133****4444", "2018-11-11 11:11:11", true));

        }

        getInviteList();
        adapter.notifyDataSetChanged();
        setRefresh(false);
        if (Integer.parseInt("5") <= page) {
            adapter.loadMoreEnd(false);
        } else
            adapter.loadMoreComplete();
    }

    private void getInviteList(){
        PersonLoader.getInstance().getSpreadList("我的邀请列表", SPUtils.getUserId()).subscribe(new Action1<MyInviteListBean>() {
            @Override
            public void call(MyInviteListBean bean) {
                dismissProgressDialog();

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("我的邀请列表:报异常2:", throwable.toString());
            }
        });
    }

}
