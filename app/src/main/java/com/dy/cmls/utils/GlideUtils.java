package com.dy.cmls.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by lcjing on 2019/1/4.
 */

public class GlideUtils {

    public static void setImage(ImageView imageView, int defaultImg, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
        Glide.with(imageView.getContext()).
                load(url).error(defaultImg) //异常时候显示的图片
                .placeholder(defaultImg) //加载成功前显示的图片
                .fallback(defaultImg) //url为空的时候,显示的图片
                .into(imageView);//在RequestBuilder 中使用自定义的ImageViewTarget
//        ---------------------
//                作者：bt侠
//        来源：CSDN
//        原文：https://blog.csdn.net/qq_28339011/article/details/80283773
//        版权声明：本文为博主原创文章，转载请附上博文链接！
    }
}
