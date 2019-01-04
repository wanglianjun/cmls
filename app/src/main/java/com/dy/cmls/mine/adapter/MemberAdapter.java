package com.dy.cmls.mine.adapter;

import android.content.Context;
import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mine.bean.InviteLogBean;
import com.dy.cmls.mine.bean.MemberBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/20.
 */

public class MemberAdapter extends BaseQuickAdapter<MemberBean, BaseViewHolder> {
    private Context context;

    public MemberAdapter(int layoutResId, List<MemberBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberBean item) {
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_type, item.getType());
        helper.setText(R.id.tv_jiangli,"奖励："+item.getMoney());
    }

}
