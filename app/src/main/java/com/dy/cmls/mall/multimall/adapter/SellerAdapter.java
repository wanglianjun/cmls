package com.dy.cmls.mall.multimall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.multimall.bean.MultiHomeCateBean;
import com.dy.cmls.mall.multimall.bean.SellerBean;
import com.dy.cmls.mall.selmall.activity.SelGoodsInfoActivity;
import com.dy.cmls.mall.selmall.bean.GoodsBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created by lcjing on 2019/1/2.
 */

public class SellerAdapter extends BaseQuickAdapter<SellerBean, BaseViewHolder> {
    private Context context;

    public SellerAdapter(int layoutResId, List<SellerBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SellerBean item) {
        helper.setText(R.id.tv_seller_name, item.getName());
        Glide.with(context).load(item.getUrl()).into((RoundedImageView) helper.getView(R.id.iv_seller_logo));
        RecyclerView recyclerView=helper.getView(R.id.rv_goods);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        GoodsAdapter adapter=new GoodsAdapter(R.layout.item_mul_seller_goods,item.getGoods(),context);
        if (item.isHasRedPacket()) {
            helper.setVisible(R.id.iv_red_packet,true);
            helper.addOnClickListener(R.id.iv_red_packet);
        }
        helper.addOnClickListener(R.id.tv_go_shop);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.iv_content:
                        Intent intent=new Intent(context, SelGoodsInfoActivity.class);
                        intent.putExtra("id",item.getGoods().get(position).getId());
                        context.startActivity(intent);
                        break;
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }


    public static class GoodsAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {
        private Context context;

        public GoodsAdapter(int layoutResId, List<GoodsBean> data, Context context) {
            super(layoutResId, data);
            this.context = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, final GoodsBean item) {
            helper.setText(R.id.tv_price, "￥"+item.getPrice());
            Glide.with(context).load(item.getImgUrl()).into((RoundedImageView) helper.getView(R.id.iv_content));
            helper.addOnClickListener(R.id.iv_content);

        }
    }

}
