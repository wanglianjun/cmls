package com.dy.cmls.mkabao.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.HKPlanBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/6.
 */

public class PlanAdapter extends BaseQuickAdapter<HKPlanBean, BaseViewHolder> {
    private Context context;

    public PlanAdapter(int layoutResId, List<HKPlanBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final HKPlanBean item) {
//        Glide.with(context).load(item.getBankIcon()).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_money, item.getMoney());
        helper.setText(R.id.tv_status, item.getStatus());
        helper.setText(R.id.tv_fee_money, item.getFeeMoney());
        helper.setText(R.id.tv_time, item.getTime());


    }
}