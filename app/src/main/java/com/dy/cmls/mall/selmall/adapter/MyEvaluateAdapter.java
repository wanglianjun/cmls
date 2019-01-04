package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.selmall.bean.MyEvaBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/25.
 */
//我的评价
public class MyEvaluateAdapter extends BaseQuickAdapter<MyEvaBean, BaseViewHolder> {
    private Context context;

    public MyEvaluateAdapter(int layoutResId, List<MyEvaBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyEvaBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_type,item.getGoodsType());
        helper.setText(R.id.tv_content,item.getContent());
        helper.setText(R.id.tv_date,item.getDate());
        helper.setText(R.id.tv_price,item.getGoodsPrice());
        helper.setText(R.id.tv_goods_name,item.getGoodsName());

        if (StringUtils.isEmpty(item.getShopContent())) {
            helper.setVisible(R.id.tv_shop_content,false);
        }else {
            helper.setVisible(R.id.tv_shop_content,true);
            helper.setText(R.id.tv_shop_content,item.getShopContent());
        }
        Glide.with(context).load(item.getImgHead()).into((ImageView) helper.getView(R.id.riv_head));
        if (StringUtils.isEmpty(item.getImg1())) {
            helper.getView(R.id.iv_1).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.iv_1).setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getImg1()).into((ImageView) helper.getView(R.id.iv_1));
        }
        if (StringUtils.isEmpty(item.getImg2())) {
            helper.getView(R.id.iv_2).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.iv_2).setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getImg2()).into((ImageView) helper.getView(R.id.iv_2));
        }
        if (StringUtils.isEmpty(item.getImg3())) {
            helper.getView(R.id.iv_3).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.iv_3).setVisibility(View.VISIBLE);
            Glide.with(context).load(item.getImg3()).into((ImageView) helper.getView(R.id.iv_3));
        }
        Glide.with(context).load(item.getImgGoods()).into((ImageView) helper.getView(R.id.iv_goods));

    }
}
