package com.dy.cmls.view.pop;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dy.cmls.R;
import com.dy.cmls.view.PopupWindow7;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.dy.cmls.view.pop.bean.AttrBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/27.
 */

public class AfterSaleView implements View.OnClickListener{

    private Context context;
    private PopupWindow7 popupWindow7;
    private TJCallBack callBack;



    public AfterSaleView(Context context, TJCallBack callBack) {
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
        popupWindow7 =
                new PopupWindow7(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = View.inflate(context, R.layout.pop_after_select, null);
        view.findViewById(R.id.iv_close).setOnClickListener(this);
        view.findViewById(R.id.fl_root).setOnClickListener(this);
        view.findViewById(R.id.fl_tuihuo).setOnClickListener(this);
        view.findViewById(R.id.fl_tuikuan).setOnClickListener(this);
        popupWindow7.setContentView(view);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.putExtra("call","afterType");
        switch (v.getId()) {
            case R.id.fl_root:
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.fl_tuihuo:
                intent.putExtra("type","th");
                callBack.callBack(intent);
                dismiss();
                break;
            case R.id.fl_tuikuan:
                intent.putExtra("type","tk");
                callBack.callBack(intent);
                dismiss();
                break;
        }
    }
}
