package com.dy.cmls.mkabao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mkabao.fragment.SelectCreditCardFragment;
import com.dy.cmls.mkabao.fragment.SelectDebitCardFragment;
import com.dy.cmls.view.interfaces.ListBack;
import com.dy.cmls.view.interfaces.TJCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lcjing on 2018/12/29.
 */

public class SelectCardActivity extends BaseActivity implements ListBack {


    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    private SelectCreditCardFragment creditFragment;
    private SelectDebitCardFragment debitFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        ButterKnife.bind(this);
        initView();
    }

    private int step = 0;

    @Override
    protected void initView() {
        creditFragment = new SelectCreditCardFragment();
        creditFragment.setBack(this);
        debitFragment = new SelectDebitCardFragment();
        debitFragment.setBack(this);
        credit();

    }

    private void credit() {
        step=0;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, creditFragment).commit();
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.mipmap.nav_add);
        tvTitleTitle.setText("选择信用卡");
    }

    private void debit() {
        step=1;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, debitFragment).commit();
        ivRight.setVisibility(View.GONE);
        tvTitleTitle.setText("选择储蓄卡");
    }

    @OnClick({R.id.ivLeft, R.id.ivRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                back();
                break;
            case R.id.ivRight:
                jumpToPage(AddCreditCardActivity.class, true, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 1) {//添加信用卡
            credit();
        }
    }

    private void back(){
        if(step==0)
            finish();
        else
            credit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
          back();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void back(int type, String id) {
        switch (type) {
            case 1://选择信用卡
                debit();
                break;
            case 2://选择储蓄卡
                ToastUtils.showShort(id);
                break;
        }

    }
}
