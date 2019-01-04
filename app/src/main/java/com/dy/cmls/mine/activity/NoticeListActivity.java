package com.dy.cmls.mine.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mine.fragment.MessageListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeListActivity extends BaseActivity {
    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        ButterKnife.bind(this);
        title=getIntent().getStringExtra("title");
        initView();
    }

    @Override
    protected void initView() {

        tvTitleTitle.setText(title);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fl_content, MessageListFragment.getInstance(title)).commit();

    }

    @OnClick(R.id.ivLeft)
    public void onViewClicked() {
        finish();
    }
}
