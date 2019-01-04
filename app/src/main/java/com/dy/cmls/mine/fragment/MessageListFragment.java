package com.dy.cmls.mine.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.common.WebViewActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.MsgListBean;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.mhome.NoticeInfoActivity;
import com.dy.cmls.mine.adapter.MessageAdapter;
import com.dy.cmls.mine.bean.MessageBean;
import com.dy.cmls.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by lcjing on 2018/12/29.
 */

public class MessageListFragment extends BaseListFragment {

    List<MessageBean> list;
    private String type;
    public static MessageListFragment getInstance(String type){
        MessageListFragment fragment=new MessageListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("type",type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initAdapter() {
        list = new ArrayList<>();
        type=getArguments().getString("type");
        if (("消息").equals(type)) {
            adapter=new MessageAdapter(R.layout.item_message,list,getContext());
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle=new Bundle();
                    bundle.putString("id",list.get(position).getId());
                    jumpToPage(NoticeInfoActivity.class,bundle);
                }
            });
        }else {
            adapter=new MessageAdapter(R.layout.item_notice,list,getContext());
        }

        requestList(true,true,true);
    }

    private int page;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            list.clear();
            page = 0;
        }
        page++;
        if (type.equals("消息")) {
            getMessage();
        } else {
            getNotice();
        }


    }

    private void getMessage(){
        showProgressDialog();
        PersonLoader.getInstance().getMsgList("消息列表", SPUtils.getUserId()).subscribe(new Action1<MsgListBean>() {
            @Override
            public void call(MsgListBean infoBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(infoBean.getStatus())) {
                    for (int i = 0; i < infoBean.getInfo().getMessage().size(); i++) {
                        list.add(new MessageBean(infoBean.getInfo().getMessage().get(i)));
                    }
                        adapter.notifyDataSetChanged();
                    setRefresh(false);
                    if (infoBean.getInfo().getNow_page()==infoBean.getInfo().getAll_page()) {
                        adapter.loadMoreEnd(false);
                    } else
                        adapter.loadMoreComplete();
                }else {
                    ToastUtils.showShort(infoBean.getMessage());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("消息列表:报异常2:", throwable.toString());
                setRefresh(false);
                    adapter.loadMoreEnd(false);
            }
        });

//        for (int i = 0; i < 2; i++) {
//            list.add(new MessageBean("消息标题","2018-02-08 14:30",
//                    "您好，您的信用卡(6123123412341234123)成功消费198.00功消费198.00功消费198.00",true));
//            list.add(new MessageBean("消息标题","2018-02-08 14:30",
//                    "您好，您的信用卡(6123123412341234123)成功消费198.00功消费198.00功消费198.00"));
//            list.add(new MessageBean("消息标题","2018-02-08 14:30",
//                    "您好，您的信用卡(6123123412341234123)成功消费198.00功消费198.00功消费198.00"));
//        }

    }



    private void getNotice(){
        for (int i = 0; i < 2; i++) {
            list.add(new MessageBean("农行暂停服务公告","2018-02-08 14:30",
                    "由于农行系统维护，暂停农行储蓄卡借款、还款相关功能。"));
            list.add(new MessageBean("农行暂停服务公告","2018-02-08 14:30",
                    "由于农行系统维护，暂停农行储蓄卡借款、还款相关功能。功消费198.00功消费198.00"));
            list.add(new MessageBean("农行暂停服务公告","2018-02-08 14:30",
                    "由于农行系统维护，暂停农行储蓄卡借款、还款相关功能。"));
        }
    }
}
