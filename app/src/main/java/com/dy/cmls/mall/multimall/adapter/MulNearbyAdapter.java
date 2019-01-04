package com.dy.cmls.mall.multimall.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.KabaoBean;

import java.util.List;

/**
 * Created by lcjing on 2019/1/2.
 * 对接口的时候实体类要改
 */

public class MulNearbyAdapter extends BaseQuickAdapter<KabaoBean, BaseViewHolder> {
    private Context context;

    //
    public MulNearbyAdapter(int layoutResId, List<KabaoBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final KabaoBean item) {

        helper.setText(R.id.tv_name,item.getBankName());
        Glide.with(context).load(item.getBankIcon()).into((ImageView) helper.getView(R.id.iv_content));
        helper.addOnClickListener(R.id.iv_sign);


    }
}
