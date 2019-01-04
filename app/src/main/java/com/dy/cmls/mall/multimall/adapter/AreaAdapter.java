package com.dy.cmls.mall.multimall.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.multimall.bean.AreaBean;
import com.dy.cmls.view.dialog.SelectAreaDialog;

import java.util.List;

/**
 * Created by lcjing on 2019/1/2.
 */

public class AreaAdapter extends BaseQuickAdapter<AreaBean, BaseViewHolder> {


    public AreaAdapter(int layoutResId, List<AreaBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AreaBean item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setTextColor(R.id.tv_name, item.isSelect() ? Color.parseColor("#FF3570FE") : Color.parseColor("#ff666666"));
        helper.setVisible(R.id.tv_position, item.isPosition());
        helper.setVisible(R.id.iv_position, item.isPosition());
        helper.setVisible(R.id.v_b_line, true);
        helper.setVisible(R.id.v_t_line, false);
    }
}
