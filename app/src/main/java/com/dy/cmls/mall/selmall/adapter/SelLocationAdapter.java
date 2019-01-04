package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.selmall.bean.LocationBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/27.
 */

public class SelLocationAdapter  extends BaseQuickAdapter<LocationBean, BaseViewHolder> {
    private Context context;

    public SelLocationAdapter(int layoutResId, List<LocationBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final LocationBean item) {


       helper.setVisible(R.id.tv_default,item.isDefault());
       helper.setText(R.id.tv_name,item.getName());
       helper.setText(R.id.tv_address,item.getAddress());
       helper.setText(R.id.tv_phone,item.getPhone());


    }
}
