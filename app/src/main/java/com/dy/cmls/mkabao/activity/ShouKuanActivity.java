package com.dy.cmls.mkabao.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.blankj.utilcode.util.StringUtils;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mkabao.fragment.TongDaoSelectFragment;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShouKuanActivity extends BaseActivity {

    @BindView(R.id.et_money)
    EditText etMoney;


    private TongDaoSelectFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_shou_kuan);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        fragment=new TongDaoSelectFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_tongdao,fragment).commit();
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String money=etMoney.getText().toString();
                if (StringUtils.isEmpty(money)) {
                    money="0";
                }
                fragment.getData(money);
            }
        });

    }

    @OnClick({R.id.iv_back, R.id.tv_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_menu:
                jumpToPage(ShouKuanLogActivity.class);
                break;
        }
    }
}
