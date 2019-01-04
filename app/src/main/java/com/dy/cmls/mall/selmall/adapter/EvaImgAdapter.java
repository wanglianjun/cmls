package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;

import java.util.List;

/**
 * Created by lcjing on 2018/12/26.
 */

public class EvaImgAdapter  extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context context;
    private int defaultImageResource=R.mipmap.btn_tian_h;

    public EvaImgAdapter(int layoutResId, List<String> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    public void setDefaultImageResource(int defaultImageResource) {
        this.defaultImageResource = defaultImageResource;
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        if (StringUtils.isEmpty(item)) {
            helper.setImageResource(R.id.iv_content,defaultImageResource);
            helper.setVisible(R.id.iv_c,false);
        }else {
            Glide.with(context).load(item).into((ImageView) helper.getView(R.id.iv_content));
            helper.setVisible(R.id.iv_c,true);
        }

        helper.addOnClickListener(R.id.iv_content);
        helper.addOnClickListener(R.id.iv_c);
    }
}
