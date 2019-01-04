package com.dy.cmls.mhome;

import android.os.Bundle;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuiZeActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_ze);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("规则");
    }

    @OnClick(R.id.ivLeft)
    public void onViewClicked() {
        finish();
    }
}
