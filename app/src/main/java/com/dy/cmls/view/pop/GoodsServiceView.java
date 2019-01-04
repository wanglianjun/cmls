package com.dy.cmls.view.pop;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dy.cmls.R;
import com.dy.cmls.view.PopupWindow7;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.dy.cmls.view.pop.adapter.AttrAdapter;
import com.dy.cmls.view.pop.bean.AttrBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/26.
 */

public class GoodsServiceView implements View.OnClickListener{
    View vTran;
    ImageView ivClose;
    private Context context;
    private PopupWindow7 popupWindow7;

    public GoodsServiceView() {
    }

    public GoodsServiceView(Context context) {
        this.context = context;
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
        popupWindow7 =
                new PopupWindow7(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = View.inflate(context, R.layout.pop_goods_service, null);
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
            case R.id.tv_confirm:
                dismiss();
                break;
        }
    }
}
