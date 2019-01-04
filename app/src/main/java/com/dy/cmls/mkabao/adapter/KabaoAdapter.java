package com.dy.cmls.mkabao.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.KabaoBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/6.
 */

public class KabaoAdapter extends BaseQuickAdapter<KabaoBean, BaseViewHolder> {
    private Context context;

    public KabaoAdapter(int layoutResId, List<KabaoBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final KabaoBean item) {
//        Glide.with(context).load(item.getBankIcon()).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_bank_name,item.getBankName());
        helper.setText(R.id.tv_zd_date,item.getZdDate());
        helper.setText(R.id.tv_hk_date,item.getHkDate());
        helper.setText(R.id.tv_money,item.getMoney());


    }



}
