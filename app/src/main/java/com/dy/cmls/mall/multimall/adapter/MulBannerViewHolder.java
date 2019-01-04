package com.dy.cmls.mall.multimall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dy.cmls.R;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * Created by lcjing on 2019/1/2.
 */

public class MulBannerViewHolder implements MZViewHolder<String> {
    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.mul_banner_item,null);
        mImageView =  view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, String data) {
        // 数据绑定
//        mImageView.setImageResource(data);
        Glide.with(context).load(data).into(mImageView);
    }
}
