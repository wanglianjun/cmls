package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.selmall.adapter.helper.SelectListener;
import com.dy.cmls.mall.selmall.bean.GoodsBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/24.
 */

public class CartAdapter  extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {
    private Context context;
    private SelectListener selectListener;

    public CartAdapter(int layoutResId, List<GoodsBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_price,item.getPrice());
        helper.setText(R.id.tv_count,item.getmCount()+"");
        helper.setChecked(R.id.cb_cart,item.isSelect());
        ImageView iv=helper.getView(R.id.cb_cart);
        iv.setImageResource(item.isSelect()?R.mipmap.btn_check_pre:R.mipmap.btn_check);

        Glide.with(context).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.iv_content));
//        ((CheckBox)helper.getView(R.id.cb_cart)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                item.setSelect(isChecked);
//                if (selectListener!=null) {
//                    selectListener.select(helper.getLayoutPosition(),isChecked);
//                }
//            }
//        });
        helper.addOnClickListener(R.id.cb_cart);
        helper.addOnClickListener(R.id.iv_add);
        helper.addOnClickListener(R.id.iv_minus);
        helper.addOnClickListener(R.id.ll_item);
//        helper.addOnClickListener(R.id.v_check);
    }




}
