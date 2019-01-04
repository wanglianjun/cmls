package com.dy.cmls.mine.adapter;

import android.content.Context;
import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mine.bean.LocationBean;
import com.dy.cmls.mine.bean.MemberBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/29.
 */

public class LocationAdapter  extends BaseQuickAdapter<LocationBean, BaseViewHolder> {
    private Context context;

    public LocationAdapter(int layoutResId, List<LocationBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, LocationBean item) {
        helper.setText(R.id.tv_phone, item.getPhone());
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_address, item.getAddress());

        if (item.isIdDefault()) {
            helper.setImageResource(R.id.iv_default,R.mipmap.btn_gx_p);
            helper.setTextColor(R.id.tv_default, Color.parseColor("#fffe9d35"));
        }else {
            helper.setImageResource(R.id.iv_default,R.mipmap.btn_gxuan);
            helper.setTextColor(R.id.tv_default, Color.parseColor("#FF999999"));
        }

        helper.addOnClickListener(R.id.ll_default);
        helper.addOnClickListener(R.id.iv_edit);
        helper.addOnClickListener(R.id.iv_delete);
    }

}
