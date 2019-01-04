package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.selmall.bean.LikeBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/24.
 */

public class CateTypeAdapter  extends BaseQuickAdapter<LikeBean, BaseViewHolder> {
    private Context context;

    public CateTypeAdapter(int layoutResId, List<LikeBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final LikeBean item) {
        helper.setText(R.id.tv_name,item.getName());
        Glide.with(context).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.iv_content));

    }
}