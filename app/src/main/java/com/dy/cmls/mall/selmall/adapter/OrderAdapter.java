package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.mall.selmall.activity.OrderInfoActivity;
import com.dy.cmls.mall.selmall.bean.OrderBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/27.
 */

public class OrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {
    private Context context;
    private int type;//1001售后

    public OrderAdapter(int layoutResId, List<OrderBean> data, Context context, int type) {
        super(layoutResId, data);
        this.context = context;
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, final OrderBean item) {
        if (!StringUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.tv_title_name, item.getTitle());
        }
        if(type==CMLSConstant.ORDER_SHOUHOU){
            initShouhou(helper, item);
        }else if(type<10){
            initOrder(helper, item);
        }
        OrderGoodsAdapter adapter=new OrderGoodsAdapter(R.layout.item_sel_order_goods,item.getGoodsBeans(),context);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle info=new Bundle();
                Intent intent =new Intent(context,OrderInfoActivity.class);
                intent.putExtra("status",item.getStatus());
                info.putString("status",item.getStatus());
                context.startActivity(intent);
            }
        });

        RecyclerView goods=helper.getView(R.id.rv_goods);
        goods.setLayoutManager(new LinearLayoutManager(context));
        goods.setAdapter(adapter);
        helper.addOnClickListener(R.id.fl_goods);



    }


    //售后
    private void initShouhou(BaseViewHolder helper, final OrderBean item) {
        helper.setVisible(R.id.ll_shouhou, true);
        if (item.getType().equals("仅退款")) {
            helper.setImageResource(R.id.iv_type, R.mipmap.ic_tuik);
        } else {
            helper.setImageResource(R.id.iv_type, R.mipmap.ic_tuih);
        }
        helper.setText(R.id.tv_type, item.getType());
        helper.setText(R.id.tv_status, item.getStatus());
        helper.setVisible(R.id.tv_cancel, false);
        helper.setVisible(R.id.tv_shouhou1, false);
        helper.setVisible(R.id.tv_del, false);
        helper.setVisible(R.id.tv_info, true);
        helper.setVisible(R.id.tv_shouhuo, false);
        helper.setVisible(R.id.tv_shouhou, false);
        helper.setVisible(R.id.tv_eva, false);
        helper.setVisible(R.id.tv_fk, false);
        helper.addOnClickListener(R.id.tv_info);
    }

    private void initOrder(BaseViewHolder helper, final OrderBean item){
        helper.setVisible(R.id.ll_shouhou, false);
        helper.setText(R.id.tv_title_status, item.getStatus());
        helper.setVisible(R.id.tv_fk, false);
        helper.setVisible(R.id.tv_cancel, false);
        helper.setVisible(R.id.tv_shouhou1, false);
        helper.setVisible(R.id.tv_del, false);
        helper.setVisible(R.id.tv_info, false);
        helper.setVisible(R.id.tv_shouhuo, false);
        helper.setVisible(R.id.tv_shouhou, false);
        helper.setVisible(R.id.tv_eva, false);
        switch (item.getStatus()){
            case "待付款":
                helper.addOnClickListener(R.id.tv_fk);
                helper.addOnClickListener(R.id.tv_cancel);
                helper.setVisible(R.id.tv_fk, true);
                helper.setVisible(R.id.tv_cancel, true);
                break;
            case "待发货":
                helper.addOnClickListener(R.id.tv_shouhou);
                helper.setVisible(R.id.tv_shouhou, true);
                break;
            case "待收货":
                helper.addOnClickListener(R.id.tv_shouhou1);
                helper.addOnClickListener(R.id.tv_shouhuo);
                helper.setVisible(R.id.tv_shouhou1, true);
                helper.setVisible(R.id.tv_shouhuo, true);
                break;
            case "待评价":
                helper.addOnClickListener(R.id.tv_del);
                helper.addOnClickListener(R.id.tv_eva);
                helper.addOnClickListener(R.id.tv_shouhou1);
                helper.setVisible(R.id.tv_del, true);
                helper.setVisible(R.id.tv_eva, true);
                helper.setVisible(R.id.tv_shouhou1, true);
                break;
            case "交易关闭":
                helper.addOnClickListener(R.id.tv_del);
                helper.setVisible(R.id.tv_del, true);
                break;
            case "交易成功":
                helper.addOnClickListener(R.id.tv_del);
                helper.addOnClickListener(R.id.tv_shouhou);
                helper.setVisible(R.id.tv_del, true);
                helper.setVisible(R.id.tv_shouhou, true);
                break;
        }
    }

}
