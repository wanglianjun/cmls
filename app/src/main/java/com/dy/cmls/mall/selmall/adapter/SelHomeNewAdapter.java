package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.KabaoBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/24.
 */

public class SelHomeNewAdapter extends BaseQuickAdapter<KabaoBean, BaseViewHolder> {
    private Context context;

    public SelHomeNewAdapter(int layoutResId, List<KabaoBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final KabaoBean item) {


        Glide.with(context).load(item.getBankIcon()).into((ImageView) helper.getView(R.id.iv_content));


    }
}
