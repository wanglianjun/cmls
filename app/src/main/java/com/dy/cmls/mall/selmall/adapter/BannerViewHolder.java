package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dy.cmls.R;
import com.dy.cmls.utils.GlideUtils;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * Created by lcjing on 2018/11/26.
 *         作者：依然范特稀西
 链接：https://juejin.im/post/599d9452f265da24744d86d4
 */

public class BannerViewHolder implements MZViewHolder<String> {
    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        mImageView =  view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int position, String data) {
        // 数据绑定
        GlideUtils.setImage(mImageView,R.mipmap.banner,data);
    }
}

