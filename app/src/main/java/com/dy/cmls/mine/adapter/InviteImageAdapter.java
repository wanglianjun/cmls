package com.dy.cmls.mine.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mine.bean.InviteImageBean;

import java.util.List;

/**
 * Created by tangji on 2017/7/10.
 */

public class InviteImageAdapter extends BaseQuickAdapter<InviteImageBean, BaseViewHolder> {
    private Context context;

    public InviteImageAdapter(int layoutResId, List<InviteImageBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, InviteImageBean item) {
        helper.setImageResource(R.id.ivImage, item.getImage());
        helper.setText(R.id.tvText, item.getName());
    }
}