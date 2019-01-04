package com.dy.cmls.view.pop;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.view.PopupWindow7;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.dy.cmls.view.pop.adapter.AttrAdapter;
import com.dy.cmls.view.pop.bean.AttrBean;

import java.util.List;

/**
 * Created by lcjing on 2018/11/27.
 */

public class GoodsInfoView implements View.OnClickListener {


    View vTran;
    ImageView ivClose;
    private Context context;
    private PopupWindow7 popupWindow7;
    private TJCallBack callBack;

    public GoodsInfoView() {
    }

    public GoodsInfoView(Context context, TJCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TJCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(TJCallBack callBack) {
        this.callBack = callBack;
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

    List<AttrBean> attrBeans;

    public void setAttrBeans(List<AttrBean> attrBeans) {
        this.attrBeans = attrBeans;
    }

    private void init() {
        if (attrBeans==null) {
            return;
        }
        popupWindow7 =
                new PopupWindow7(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = View.inflate(context, R.layout.pop_goods_info, null);
        RecyclerView recyclerView=view.findViewById(R.id.rv_info);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        AttrAdapter attrAdapter=new AttrAdapter(R.layout.pop_item_sel_goods_info,attrBeans,context);
        recyclerView.setAdapter(attrAdapter);
        vTran = view.findViewById(R.id.v_tran);
        ivClose = view.findViewById(R.id.iv_close);
        vTran.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        popupWindow7.setContentView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.v_tran:
            case R.id.iv_close:
                dismiss();
                break;
        }
    }
}
