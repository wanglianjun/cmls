package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.selmall.bean.GoodsBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/25.
 */

public class SelCollectionAdapter  extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {
    private Context context;

    public SelCollectionAdapter(int layoutResId, List<GoodsBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_price,item.getPrice());
        helper.setText(R.id.tv_count,item.getmCount()+"人收藏");
        Glide.with(context).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.iv_content));
        helper.addOnClickListener(R.id.tv_add_cart);
        helper.addOnClickListener(R.id.tv_remove_collect);
    }
}