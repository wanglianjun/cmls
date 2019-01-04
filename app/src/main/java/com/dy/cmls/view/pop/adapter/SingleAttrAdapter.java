package com.dy.cmls.view.pop.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.view.pop.bean.AttrBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/28.
 */

public class SingleAttrAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context context;

    public SingleAttrAdapter(int layoutResId, List<String> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final String item) {
        helper.setText(R.id.tv_attr, item);

    }
}
