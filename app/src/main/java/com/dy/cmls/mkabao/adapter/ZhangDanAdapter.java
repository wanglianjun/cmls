package com.dy.cmls.mkabao.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mkabao.bean.ZhangdanBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/7.
 */

public class ZhangDanAdapter  extends BaseQuickAdapter<ZhangdanBean, BaseViewHolder> {
    private Context context;

    public ZhangDanAdapter(int layoutResId, List<ZhangdanBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ZhangdanBean item) {
//        Glide.with(context).load(item.getBankIcon()).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_type, item.getType());
        if(item.getType().equals("还款")){
            helper.setBackgroundRes(R.id.fl_type,R.drawable.bg_oval_blue);
            helper.setText(R.id.tv_type_o,"还");
        }else {
            helper.setBackgroundRes(R.id.fl_type,R.drawable.bg_oval_orange);
            helper.setText(R.id.tv_type_o,"消");
        }
        helper.setText(R.id.tv_money,item.getMoney());
        helper.setText(R.id.tv_num,item.getNum());
    }
}
