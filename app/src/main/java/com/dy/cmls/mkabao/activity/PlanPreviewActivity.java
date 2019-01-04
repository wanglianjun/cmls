package com.dy.cmls.mkabao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mkabao.adapter.PlanPreviewAdapter;
import com.dy.cmls.mkabao.bean.PlanPreviewBean;
import com.dy.cmls.view.dialog.NotifyImageDialog;
import com.dy.cmls.view.interfaces.TJCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlanPreviewActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    PlanPreviewAdapter adapter;
    List<PlanPreviewBean> list;

    public static final int SUC=1;
    public static final int FAIL=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_review);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("计划预览");
        for (int i = 0; i < 5; i++) {
            list.add(new PlanPreviewBean("2018-11-28 14:32:20", "￥2000.00"));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlanPreviewAdapter(R.layout.item_plan_preview, list, this);
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.ivLeft, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_confirm:
                is_success = !is_success;
                showImagePop(is_success);
                break;
        }
    }

    private boolean is_success = false;

    private void showImagePop(boolean is_success) {
        int icon;
        String message;
        String ok;
        if (is_success) {
            icon = R.mipmap.ic_success;
            message = "还款计划提交成功";
            ok = "好的，我知道了";
        } else {
            icon = R.mipmap.ic_failure;
            message = "还款计划提交失败";
            ok = "重新去设置";
        }
        NotifyImageDialog notifyImageDialog = new NotifyImageDialog(
                this, icon, message, ok, new TJCallBack() {
            @Override
            public void callBack(Intent intent) {
                    setResult(is_success?SUC:FAIL);
                    finish();
            }
        });
        notifyImageDialog.show();
    }

}
