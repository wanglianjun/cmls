package com.dy.cmls.mkabao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditCreditCardActivity extends BaseActivity {

    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.et_cvn2)
    EditText etCvn2;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_zd_date)
    TextView tvZdDate;
    @BindView(R.id.tv_hk_date)
    TextView tvHkDate;
    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_credit_card);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("修改资料");
    }

    @OnClick({R.id.ivLeft, R.id.ll_date, R.id.ll_zd_date, R.id.ll_hk_date, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ll_date:
                break;
            case R.id.ll_zd_date:
                break;
            case R.id.ll_hk_date:
                break;
            case R.id.tv_confirm:
                break;
        }
    }
}
