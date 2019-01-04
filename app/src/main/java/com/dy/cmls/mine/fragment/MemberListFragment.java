package com.dy.cmls.mine.fragment;

import android.os.Bundle;

import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.mine.adapter.MemberAdapter;
import com.dy.cmls.mine.bean.MemberBean;
import com.dy.cmls.mine.bean.MyInviteListBean;
import com.dy.cmls.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by lcjing on 2018/12/20.
 */

public class MemberListFragment extends BaseListFragment {
    String type;
    List<MemberBean> members;

    public static MemberListFragment getInstance(String type) {
        MemberListFragment fragment = new MemberListFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initAdapter() {
        type = getArguments().getString("type");
        members = new ArrayList<>();
        adapter = new MemberAdapter(R.layout.item_member, members, getContext());
        requestList(true, true, true);
    }

    int page = 0;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            members.clear();
            page = 0;
        }
        page++;
        for (int i = 0; i < 10; i++) {

            members.add(new MemberBean("188****999" + i, type + "普通会员", "10.00", "2018-12-20 12:40"));
        }
        //1-直推，2-间推
        String dir=type.contains("直")?"1":"2";
        showProgressDialog();
        PersonLoader.getInstance().getSpreadMemList("会员管理" + type, SPUtils.getUserId(),dir , page + "")
                .subscribe(new Action1<MyInviteListBean>() {
                    @Override
                    public void call(MyInviteListBean bean) {
                        dismissProgressDialog();
//                        CMLSConstant.REQUEST_SUCCESS.equals()
                        adapter.notifyDataSetChanged();
                        setRefresh(false);
                        if (Integer.parseInt("2") <= page) {
                            adapter.loadMoreEnd(false);
                        } else
                            adapter.loadMoreComplete();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        adapter.notifyDataSetChanged();
                        setRefresh(false);
                        adapter.loadMoreEnd(false);
                        dismissProgressDialog();
                        showToastFailure();
                        showLog("会员管理"+type+":报异常2:", throwable.toString());
                    }
                });
    }
}
