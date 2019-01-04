package com.dy.cmls.mhome;

import android.os.Bundle;
import android.widget.TextView;

import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.MsgInfoBean;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.utils.date.DateFormatUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class NoticeInfoActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tvv_time)
    TextView tvvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_info);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("消息详情");
        String id=getIntent().getStringExtra("id");
        getMsgInfo(id);


    }


    private void getMsgInfo(String id){
        showProgressDialog();
        PersonLoader.getInstance().getMsgInfo("消息详情", id).subscribe(new Action1<MsgInfoBean>() {
            @Override
            public void call(MsgInfoBean userInfoBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(userInfoBean.getStatus())) {
                    tvTitle.setText(userInfoBean.getInfo().getTitle());
                    tvContent.setText(userInfoBean.getInfo().getContent());
                    tvvTime.setText(DateFormatUtil.getTime(userInfoBean.getInfo().getTime()));
                }else {
                    showToast(userInfoBean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("消息详情:报异常2:", throwable.toString());
            }
        });
    }

    @OnClick(R.id.ivLeft)
    public void onViewClicked() {
        finish();
    }
}
