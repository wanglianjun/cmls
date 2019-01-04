package com.dy.cmls.view.dialog;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.dy.cmls.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lcjing on 2019/1/2.
 */

public class RedPacketDialog extends DialogFragment {

    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.ll_money)
    LinearLayout llMoney;
    @BindView(R.id.ll_receive)
    LinearLayout llReceive;
    @BindView(R.id.fl_root)
    FrameLayout flRoot;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_red_packet, null);
        unbinder = ButterKnife.bind(this, view);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        String tag=getTag();
        if (StringUtils.isEmpty(tag)) {
            llMoney.setVisibility(View.VISIBLE);
            llReceive.setVisibility(View.GONE);
        }else {
            llMoney.setVisibility(View.GONE);
            llReceive.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_close, R.id.iv_red_bg, R.id.fl_root})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.iv_red_bg:
            case R.id.fl_root:
                dismiss();
                break;
        }
    }
}
