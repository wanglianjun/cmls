package com.dy.cmls.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

/**
 * h5图片加载工具
 */

public class URLImageParser implements Html.ImageGetter {
    TextView mTextView;

    public URLImageParser(TextView textView) {
        this.mTextView = textView;
    }

    @Override
    public Drawable getDrawable(String source) {
        final URLDrawable urlDrawable = new URLDrawable();
        int loadWidth = Target.SIZE_ORIGINAL;
        int loadHeight = Target.SIZE_ORIGINAL;

        //.centerCrop()
        Glide.with(mTextView.getContext())
            .load(source)
            .asBitmap()
            .centerCrop()
            .dontAnimate()
            .into(new SimpleTarget<Bitmap>(loadWidth, loadHeight) {
                @Override
                public void onResourceReady(Bitmap loadedImg, GlideAnimation glideAnimation) {
                    Bitmap newBitmap;

                    if (loadedImg.getWidth() < 25) {
                        newBitmap = setImgScale(loadedImg, 2.1f);
                    } else if (loadedImg.getWidth() < 800) {
                        newBitmap = loadedImg;
                    } else {
                        newBitmap = setImgScale(loadedImg, 0.7f);
                    }

                    urlDrawable.bitmap = newBitmap;
                    urlDrawable.setBounds(0, 0, newBitmap.getWidth(), newBitmap.getHeight());
                    mTextView.invalidate();
                    mTextView.setText(mTextView.getText());
                }
            });
        return urlDrawable;
    }

    private Bitmap setImgScale(Bitmap bm, float scale) {
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        // 得到新的图片.
        return Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
    }
}

