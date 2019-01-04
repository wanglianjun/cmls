package com.dy.cmls.mkabao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.view.dialog.WhellViewDialog;
import com.dy.cmls.view.interfaces.TJCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditWuliuActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tv_exp)
    TextView tvExp;
    @BindView(R.id.et_no)
    EditText etNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wuliu);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("填写物流");

    }

    private void showExp(List<String> expName) {
        WhellViewDialog dialog = new WhellViewDialog(this, "选择物流公司", expName, new TJCallBack() {
            @Override
            public void callBack(Intent intent) {
                int index = intent.getIntExtra("callBack", 0);
                tvExp.setText(expName.get(index));
            }
        });
        dialog.show();
    }

    @OnClick({R.id.ivLeft, R.id.ll_select, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ll_select:
                List<String> expName = new ArrayList<>();
                expName.add("中通快递");
                expName.add("申通快递");
                expName.add("顺丰快递");
                expName.add("圆通快递");
                expName.add("天天快递");
                expName.add("德邦快递");
                expName.add("中通快递");
                expName.add("申通快递");
                expName.add("顺丰快递");
                expName.add("圆通快递");
                expName.add("天天快递");
                expName.add("德邦快递");
                showExp(expName);
                break;
            case R.id.tv_confirm:
                break;
        }
    }
}
