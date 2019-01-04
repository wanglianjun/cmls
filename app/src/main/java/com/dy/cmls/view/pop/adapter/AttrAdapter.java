package com.dy.cmls.view.pop.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.view.pop.bean.AttrBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/26.
 */

public class  AttrAdapter  extends BaseQuickAdapter<AttrBean, BaseViewHolder> {
    private Context context;

    public AttrAdapter(int layoutResId, List<AttrBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final AttrBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_value,item.getValue());

    }
}
