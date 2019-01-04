package com.dy.cmls.mine.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.common.WebViewActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.MsgInfoBean;
import com.dy.cmls.mine.resBean.SettingBean;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.utils.date.DateFormatUtil;
import com.dy.cmls.view.dialog.CallPhoneDialog;
import com.dy.cmls.view.interfaces.TJCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.tv_kf_phone)
    TextView tvKfPhone;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("设置");
        getSettingInfo();
    }

    private void getSettingInfo(){
        showProgressDialog();
        PersonLoader.getInstance().getSetting("设置" ).subscribe(new Action1<SettingBean>() {
            @Override
            public void call(SettingBean userInfoBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(userInfoBean.getStatus())) {
                    tvKfPhone.setText(userInfoBean.getInfo().getPhone());
                }else {
                    showToast(userInfoBean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("设置:报异常2:", throwable.toString());
            }
        });
    }

    @OnClick({R.id.ivLeft, R.id.ll_kf_phone, R.id.ll_about, R.id.ll_version, R.id.tv_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ll_kf_phone:
                String phone = tvKfPhone.getText().toString().trim();
                CallPhoneDialog callPhoneDialog=new CallPhoneDialog(this, phone, new TJCallBack() {
                    @Override
                    public void callBack(Intent intent) {
                        if ("callPhone".equals( intent.getStringExtra("callBack"))) {
                            Intent call = new Intent(Intent.ACTION_DIAL);
                            Uri data = Uri.parse("tel:" + phone);
                            call.setData(data);
                            startActivity(call);
                        }
                    }
                });
                callPhoneDialog.show();
                break;
            case R.id.ll_about:
                jumpToPage(AboutActivity.class);
                break;
            case R.id.ll_version:
                break;
            case R.id.tv_out:
                SPUtils.setUserInfo("","","");
                finish();
                break;
        }
    }
}
