package com.dy.cmls.mkabao.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.PlanPreviewBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/7.
 */

public class PlanPreviewAdapter extends BaseQuickAdapter<PlanPreviewBean, BaseViewHolder> {
    private Context context;

    public PlanPreviewAdapter(int layoutResId, List<PlanPreviewBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final PlanPreviewBean item) {
//        Glide.with(context).load(item.getBankIcon()).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_plan, "计划总额：" + item.getPlanMoney());
        helper.setText(R.id.tv_hk, "还款总额：" + item.getPlanMoney());
        helper.setText(R.id.tv_first, "初次消费：" + item.getPlanMoney());
        helper.setText(R.id.tv_second, "二次消费：" + item.getPlanMoney());
        helper.setText(R.id.tv_third, "三次消费：" + item.getPlanMoney());
        helper.setText(R.id.tv_fee, "手续费用：" + item.getPlanMoney());
    }
}