package com.dy.cmls.mine.adapter;

import android.content.Context;
import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mine.bean.InviteImageBean;
import com.dy.cmls.mine.bean.InviteLogBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/19.
 */

public class InviteLogAdapter extends BaseQuickAdapter<InviteLogBean, BaseViewHolder> {
    private Context context;

    public InviteLogAdapter(int layoutResId, List<InviteLogBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, InviteLogBean item) {
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_phone,item.getName());
        if(item.isShiming()){
            helper.setText(R.id.tv_shiming,"已实名");
            helper.setTextColor(R.id.tv_shiming,context.getResources().getColor(R.color.app_main));
        }else {
            helper.setText(R.id.tv_shiming,"未实名");
            helper.setTextColor(R.id.tv_shiming, Color.parseColor("#ff666666"));
        }
    }
}
