package com.dy.cmls.mkabao.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.PlanInfoBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/7.
 */

public class PlanInfoAdapter extends BaseQuickAdapter<PlanInfoBean, BaseViewHolder> {
    private Context context;

    public PlanInfoAdapter(int layoutResId, List<PlanInfoBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final PlanInfoBean item) {
//        Glide.with(context).load(item.getBankIcon()).into((ImageView) helper.getView(R.id.iv_icon));
        String name=item.getType();
        helper.setText(R.id.tv_xf_name,name);
        helper.setImageResource(R.id.iv_status,item.isOpen()?R.mipmap.ic_blue_zhic:R.mipmap.icon_select);
        helper.setVisible(R.id.ll_info,item.isOpen());
        if (item.isOpen()) {
            helper.setText(R.id.tv_xf_money,"￥"+ item.getMoney());
            helper.setText(R.id.tv_xf_type, name+"金额");
            helper.setText(R.id.tv_dh_name, name+"单号");
            helper.setText(R.id.tv_dh_value, item.getDh());
            helper.setText(R.id.tv_ls_name, name+"流水号");
            helper.setText(R.id.tv_ls_value, item.getLs());
            helper.setText(R.id.tv_sxf_name, name+"手续费");
            helper.setText(R.id.tv_sxf_value, "￥"+item.getFeeMoney());
            helper.setText(R.id.tv_time_name, name+"时间");
            helper.setText(R.id.tv_time_value, item.getXfTime());
            helper.setText(R.id.tv_cl_name, name+"处理时间");
            helper.setText(R.id.tv_cl_value, item.getClTime());
        }
    }
}