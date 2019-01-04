package com.dy.cmls.common;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class PhotoViewActivity extends BaseActivity {
    @BindView(R.id.photoView) PhotoView photoView;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);
        ButterKnife.bind(this);
        initView();
    }

    @Override protected void initView() {
        String url = getIntent().getStringExtra("url");
        if (url == null || url.isEmpty()) {
            errorOut();
            return;
        }
        showLog("url:", url);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                PhotoViewActivity.this.finish();
            }
        });
        Glide.with(this).load(url).listener(new RequestListener<String, GlideDrawable>() {
            @Override public boolean onException(Exception e, String model, Target<GlideDrawable> target,
                boolean isFirstResource) {
                return false;
            }

            //这个用于监听图片是否加载完成
            @Override public boolean onResourceReady(GlideDrawable resource, String model,
                Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(photoView);
    }



    @Override public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
