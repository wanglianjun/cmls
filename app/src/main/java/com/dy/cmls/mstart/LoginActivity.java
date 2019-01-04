package com.dy.cmls.mstart;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.LoginLoader;
import com.dy.cmls.loader.bean.RegisterResBean;
import com.dy.cmls.utils.MyBDLocationManager;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.utils.SoftHideKeyBoardUtil;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class LoginActivity extends BaseActivity implements SoftHideKeyBoardUtil.ChangedListener {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.fl_top)
    FrameLayout flTop;
    @BindView(R.id.iv_close1)
    ImageView ivClose1;
    @BindView(R.id.fl_top1)
    FrameLayout flTop1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        SoftHideKeyBoardUtil softHideKeyBoardUtil = SoftHideKeyBoardUtil.assistActivity(this);
        initView();
        softHideKeyBoardUtil.setChangedListener(this);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.iv_close,R.id.iv_close1, R.id.tv_login, R.id.tv_forget, R.id.tv_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.iv_close1:
                finish();
                break;
            case R.id.tv_login:
                String phone=etPhone.getText().toString();
                if (StringUtils.isEmpty(phone)) {
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                String pass=etPass.getText().toString();
                if (StringUtils.isEmpty(pass)) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                login(phone,pass);
                break;
            case R.id.tv_forget:
                Bundle bundle =new Bundle();
                bundle.putString("phone",etPhone.getText().toString());
                jumpToPage(ForgetPassActivity.class,bundle);
                break;
            case R.id.tv_reg:
                jumpToPage(RegActivity.class);
                finish();
                break;
        }
    }

    private void login(String phone,String pass){
        showProgressDialog();
        LoginLoader.getInstance().login("登录", phone, pass).subscribe(new Action1<RegisterResBean>() {
            @Override
            public void call(RegisterResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    showToast(bean.getMessage());
                    SPUtils.setUserInfo(bean.getInfo().getMember_id(),bean.getInfo().getPhone(),bean.getInfo().getNick_name());
                    finish();
                } else if (bean.getMessage() != null) {
                    ToastUtils.showShort(bean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("登录:报异常2:", throwable.toString());
            }
        });
    }


    @Override
    public void onKeyBoardChanged(boolean isShow) {
        flTop.setVisibility(isShow ? View.GONE : View.VISIBLE);
        flTop1.setVisibility(!isShow ? View.GONE : View.VISIBLE);
    }
}
