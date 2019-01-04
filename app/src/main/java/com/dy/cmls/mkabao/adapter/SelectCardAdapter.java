package com.dy.cmls.mkabao.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.CardBean;
import com.dy.cmls.utils.ArithUtil;

import java.util.List;

/**
 * Created by lcjing on 2018/12/29.
 */

public class SelectCardAdapter extends BaseQuickAdapter<CardBean, BaseViewHolder> {
    private Context context;

    public SelectCardAdapter(int layoutResId, List<CardBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    private String type="信用卡";

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, final CardBean item) {
//        Glide.with(context).load(item.getBankIcon()).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_bank_name, item.getBank_name());
        helper.setText(R.id.tv_num, ArithUtil.hintBank(item.getBank_num()));
        helper.setText(R.id.tv_card_type, type);

    }
}
