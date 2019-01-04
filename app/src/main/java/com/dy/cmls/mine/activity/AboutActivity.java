package com.dy.cmls.mine.activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.mine.resBean.SettingBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("关于我们");
        getAboutData();
    }

    private void getAboutData() {
        showProgressDialog();
        PersonLoader.getInstance().getAbout("关于我们").subscribe(new Action1<SettingBean>() {
            @Override
            public void call(SettingBean userInfoBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(userInfoBean.getStatus())) {
                    tvContent.setText(Html.fromHtml(userInfoBean.getInfo().getContent()));
                } else {
                    showToast(userInfoBean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("关于我们:报异常2:", throwable.toString());
            }
        });
    }

    @OnClick(R.id.ivLeft)
    public void onViewClicked() {
        finish();
    }
}
