package com.dy.cmls.mall.multimall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.multimall.bean.MultiHomeCateBean;
import com.dy.cmls.mall.selmall.bean.CateBean;
import com.dy.cmls.utils.CommonUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created by lcjing on 2019/1/2.
 */

public class MultiHomeCateAdapter extends BaseQuickAdapter<MultiHomeCateBean, BaseViewHolder> {
    private Context context;

    public MultiHomeCateAdapter(int layoutResId, List<MultiHomeCateBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MultiHomeCateBean item) {
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
//                ((int) ((context.getResources().getDisplayMetrics().widthPixels - CommonUtil.dip2px(context, 0)) / 4f),
//                        ViewGroup.LayoutParams.MATCH_PARENT);
//        LinearLayout llItem=helper.getView(R.id.ll_item);
//        llItem.setLayoutParams(params);
        helper.setText(R.id.tv_name,item.getName());
        helper.setImageResource(R.id.riv_image,item.getImgResource());
//        Glide.with(context).load(item.getBankIcon()).into((RoundedImageView) helper.getView(R.id.riv_image));


    }
}
