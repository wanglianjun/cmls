package com.dy.cmls.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mine.fragment.MyScoreListFragment;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyScoreActivity extends BaseActivity implements TJCallBack{

    @BindView(R.id.tv_score)
    TextView tvScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_my_score);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_list,new MyScoreListFragment()).commit();
    }

    @OnClick({R.id.ivLeft, R.id.tv_score})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_score:
                break;
        }
    }

    @Override
    public void callBack(Intent intent) {
        String score=intent.getStringExtra("score");
        if (StringUtils.isEmpty(score)) {
            score="0";
        }
        tvScore.setText(score);
    }
}
