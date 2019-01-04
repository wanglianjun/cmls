package com.dy.cmls.mall.multimall.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterStatusActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_operation)
    TextView tvOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_status);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        String type = getIntent().getStringExtra("type");
        if (type.equals("pay")) {
            tvTitleTitle.setText("支付成功");
            tvDes.setText("支付成功，请去填写入驻资料");
            tvOperation.setText("申请入驻");
        } else if (type.equals("commit")) {
            tvTitleTitle.setText("提交成功");
            tvDes.setText("资料已提交，正在审核中");
            tvOperation.setText("我知道了");
        }

    }

    @OnClick({R.id.ivLeft, R.id.tv_operation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_operation:
                String type = getIntent().getStringExtra("type");
                if (type.equals("pay")) {
                    jumpToPage(ApplyEnterActivity.class);
                } else if (type.equals("commit")) {
                    finish();
                }
                break;
        }
    }
}
