package com.dy.cmls.view.pop;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.view.PopupWindow7;
import com.dy.cmls.view.interfaces.TJCallBack;

import butterknife.BindView;

/**
 * Created by lcjing on 2018/11/28.
 */

public class PayTypeSelectWindow implements View.OnClickListener {

    @BindView(R.id.v_tran)
    View vTran;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.cb_alipay)
    CheckBox cbAlipay;
    @BindView(R.id.ll_alipay)
    LinearLayout llAlipay;
    @BindView(R.id.cb_weixin)
    CheckBox cbWeixin;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    private Context context;
    private PopupWindow7 popupWindow7;
    private TJCallBack callBack;

    public PayTypeSelectWindow(Context context, TJCallBack callBack) {
        this.context = context;
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

    private void init() {
        popupWindow7 =
                new PopupWindow7(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = View.inflate(context, R.layout.pop_pay_type_select, null);
        vTran = view.findViewById(R.id.v_tran);
        ivClose = view.findViewById(R.id.iv_close);
        tvConfirm = view.findViewById(R.id.tv_confirm);
        cbAlipay = view.findViewById(R.id.cb_alipay);
        llAlipay = view.findViewById(R.id.ll_alipay);
        cbWeixin = view.findViewById(R.id.cb_weixin);
        llWeixin = view.findViewById(R.id.ll_weixin);
        llWeixin.setOnClickListener(this);
        llAlipay.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        vTran.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        cbAlipay.setOnClickListener(this);
        cbWeixin.setOnClickListener(this);
        cbAlipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    type=2;
                    cbWeixin.setChecked(false);
                }
            }
        });
        cbWeixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    type=1;
                    cbAlipay.setChecked(false);
                }
            }
        });
        popupWindow7.setContentView(view);
    }

    private int type=1;//1微信 2支付宝


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_weixin:
                type=1;
                cbWeixin.setChecked(true);
                cbAlipay.setChecked(false);
                break;
            case R.id.ll_alipay:
                type=2;
                cbWeixin.setChecked(false);
                cbAlipay.setChecked(true);
                break;
            case R.id.cb_weixin:
                type=1;
                cbAlipay.setChecked(false);
                cbWeixin.setChecked(true);
                break;
            case R.id.cb_alipay:
                type=2;
                cbAlipay.setChecked(true);
                cbWeixin.setChecked(false);
                break;
            case R.id.iv_close:
            case R.id.v_tran:
                dismiss();
                break;
            case R.id.tv_confirm:
                Intent intent=new Intent();
                intent.putExtra("action","payType");
                intent.putExtra("payType",type);
                callBack.callBack(intent);
                dismiss();
                break;
        }
    }
}
