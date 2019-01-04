package com.dy.cmls.view.pop.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.view.pop.GoodsSelectView;
import com.dy.cmls.view.pop.bean.AttrBean;
import com.dy.cmls.view.pop.bean.SelectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/12.
 */

public class GoodsSelectAdapter extends BaseQuickAdapter<SelectBean, BaseViewHolder> {
    private Context context;

    public GoodsSelectAdapter(int layoutResId, List<SelectBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }



    @Override
    protected void convert(final BaseViewHolder helper, final SelectBean item) {
        helper.setText(R.id.tv_select_name,item.getSelectName());
        final List<GoodsSelectView.TypeBean> vals=new ArrayList<>();
        for (int i = 0; i < item.getSelectVal().length; i++) {
            vals.add(new GoodsSelectView.TypeBean(item.getSelectVal()[i]));
        }
       RecyclerView rvType = helper.getView(R.id.rv_type);
        rvType.setLayoutManager(new GridLayoutManager(context, 3));
        GoodsSelectView.TypeAdapter typeAdapter=new GoodsSelectView.TypeAdapter(R.layout.item_goods_type, vals, context);
        rvType.setAdapter(typeAdapter);
        typeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (!vals.get(position).isShow()) {
                    return;
                }
                for (int i = 0; i < vals.size(); i++) {
                    if(i==position){
                        vals.get(i).setSelect(true);
                        if (call!=null) {
                            call.select(helper.getAdapterPosition(),i);
                        }
//                        item.setSelectAttr( vals.get(i).getName());
                    }else {
                        vals.get(i).setSelect(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void setCall(SelectCall call) {
        this.call = call;
    }

    private SelectCall call;

    public interface SelectCall{
        void select(long position, int selectPosition);
    }

}
