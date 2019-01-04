package com.dy.cmls.view.dialog.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;

import java.util.List;

/**
 * Created by lcjing on 2018/12/4.
 */

public class WuLiuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context context;

    public WuLiuAdapter(int layoutResId, List<String> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        helper.setText(R.id.tv_txt, item);
        if (helper.getLayoutPosition()>2) {
            helper.setTextColor(R.id.tv_txt,context.getResources().getColor(R.color.c63));
        }else {
            helper.setTextColor(R.id.tv_txt,context.getResources().getColor(R.color.text_black));
        }

    }

}


