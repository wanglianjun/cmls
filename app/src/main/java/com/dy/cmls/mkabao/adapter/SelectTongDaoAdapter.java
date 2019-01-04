package com.dy.cmls.mkabao.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.TongDaoBean;
import com.dy.cmls.utils.ArithUtil;

import java.util.List;

/**
 * Created by lcjing on 2018/12/29.
 */

public class SelectTongDaoAdapter extends BaseQuickAdapter<TongDaoBean, BaseViewHolder> {
    private Context context;

    public SelectTongDaoAdapter(int layoutResId, List<TongDaoBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, TongDaoBean item) {
        helper.addOnClickListener(R.id.tv_sk);

        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_rate,
                "费率: " + (Double.parseDouble(item.getRate()) / 10000) + "+" + item.getSingle() + "元/笔");
        helper.setText(R.id.tv_time, "交易时间:" + item.getTime());
        helper.setText(R.id.tv_edu, "额度:" + item.getDesc());
        helper.setText(R.id.tv_jiesuan, "结算:" + item.getJiesuan());

        if (Double.parseDouble(item.getMoney()) > 0) {
            double moeny = 0;
            moeny = Double.parseDouble(item.getMoney()) - (Double.parseDouble(item.getMoney())
                    * (Double.parseDouble(item.getRate()) / 10000) + Double.parseDouble(item.getSingle()));
            helper.setText(R.id.tv_money, "¥" + ArithUtil.round(moeny, 2));
        } else {
            helper.setText(R.id.tv_money, "¥0");
        }
    }
}
