package com.dy.cmls.mine.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mine.bean.InviteImageBean;
import com.dy.cmls.mine.bean.MessageBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/29.
 */

public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    private Context context;

    public MessageAdapter(int layoutResId, List<MessageBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        helper.setVisible(R.id.tv_unread,item.isUnRead());
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_content, item.getContent());
    }
}
