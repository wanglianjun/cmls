package com.dy.cmls.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.base.BaseListActivity;
import com.dy.cmls.mine.adapter.InviteLogAdapter;
import com.dy.cmls.mine.bean.InviteLogBean;
import com.dy.cmls.mine.fragment.InviteLogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lcjing on 2018/12/19.
 */

public class InviteLogActivity extends BaseActivity {

    @BindView(R.id.viewStatusBar)
    View viewStatusBar;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.fl_content)
    FrameLayout flContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("推广记录");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content,new InviteLogFragment()).commit();
    }


    @OnClick({R.id.ivLeft, R.id.ivRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight:
                break;
        }
    }
}
