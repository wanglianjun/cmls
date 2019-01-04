package com.dy.cmls.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddLocationActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    @BindView(R.id.iv_default)
    ImageView ivDefault;
    private boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        ButterKnife.bind(this);
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        initView();
    }


    @Override
    protected void initView() {
        tvTitleTitle.setText("收货地址");
    }


    private  boolean isDefault=false;
    @OnClick({R.id.ivLeft, R.id.ll_location, R.id.ll_default, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ll_location:
                break;
            case R.id.ll_default:
                isDefault=!isDefault;
                ivDefault.setImageResource(isDefault?R.mipmap.btn_gx_p:R.mipmap.btn_goux);
                break;
            case R.id.tv_confirm:
                break;
        }
    }
}
