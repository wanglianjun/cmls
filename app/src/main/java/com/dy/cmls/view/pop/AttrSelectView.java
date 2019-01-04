package com.dy.cmls.view.pop;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.view.PopupWindow7;
import com.dy.cmls.view.interfaces.AttrBack;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.dy.cmls.view.pop.adapter.SingleAttrAdapter;

import java.util.List;

/**
 * Created by lcjing on 2018/12/28.
 */

public class AttrSelectView implements View.OnClickListener{

    private Context context;
    private List<String> attrs;
    private PopupWindow7 popupWindow7;
    private AttrBack callBack;

    public AttrSelectView(Context context, List<String> attrs, AttrBack callBack) {
        this.context = context;
        this.attrs = attrs;
        this.callBack = callBack;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public void show(View view) {
        if (popupWindow7 == null) {
            init();
        }
        popupWindow7.showAsDropDown(view);
    }

    public void dismiss() {
        if (popupWindow7 != null) {
            popupWindow7.dismiss();
        }
    }



    private void init() {
        if (attrs==null) {
            return;
        }
        popupWindow7 =
                new PopupWindow7(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = View.inflate(context, R.layout.pop_attr_select, null);
        RecyclerView recyclerView=view.findViewById(R.id.rv_attr);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        SingleAttrAdapter attrAdapter=new SingleAttrAdapter(R.layout.pop_item_attr_select,attrs,context);
        attrAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                callBack.back(attrs.get(position));
                dismiss();
            }
        });
        recyclerView.setAdapter(attrAdapter);
        view.findViewById(R.id.v_tran).setOnClickListener(this);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        popupWindow7.setContentView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
            case R.id.v_tran:
                dismiss();
                break;
        }
    }
}
