package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.KabaoBean;
import com.dy.cmls.utils.CommonUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created by lcjing on 2018/12/24.
 */

public class SelHomeCateAdapter extends BaseQuickAdapter<KabaoBean, BaseViewHolder> {
    private Context context;

    public SelHomeCateAdapter(int layoutResId, List<KabaoBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final KabaoBean item) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                ((int) ((context.getResources().getDisplayMetrics().widthPixels - CommonUtil.dip2px(context, 0)) / 4f),
                ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout llItem=helper.getView(R.id.ll_item);
        llItem.setLayoutParams(params);
        helper.setText(R.id.tv_name,item.getBankName());
                Glide.with(context).load(item.getBankIcon()).into((RoundedImageView) helper.getView(R.id.riv_image));


    }

}
