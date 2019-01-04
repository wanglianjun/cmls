package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.selmall.bean.CateBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/24.
 */

public class CateAdapter  extends BaseQuickAdapter<CateBean, BaseViewHolder> {
    private Context context;

    public CateAdapter(int layoutResId, List<CateBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final CateBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.getView(R.id.v_select).setVisibility(item.isSelect()? View.VISIBLE:View.INVISIBLE);

    }
}
