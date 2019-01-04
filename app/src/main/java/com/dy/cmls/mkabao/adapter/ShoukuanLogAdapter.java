package com.dy.cmls.mkabao.adapter;

import android.content.Context;
import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.KabaoBean;
import com.dy.cmls.mkabao.bean.ShouKuanLogBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/29.
 */

public class ShoukuanLogAdapter extends BaseQuickAdapter<ShouKuanLogBean, BaseViewHolder> {
    private Context context;

    public ShoukuanLogAdapter(int layoutResId, List<ShouKuanLogBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShouKuanLogBean item) {

        helper.setTextColor(R.id.tv_status,item.getStatus().equals("成功")? Color.parseColor("#FF3570FE"):Color.parseColor("#FFFE3535"));

        helper.setText(R.id.tv_status,item.getStatus());
        helper.setText(R.id.tv_time,item.getTime());
        helper.setText(R.id.tv_title,item.getMoney());


    }
}
