package com.dy.cmls.mstart;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.utils.LogUtil;
import com.dy.cmls.utils.SharedpreferncesUtil;


/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class StartActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
    }

    @Override
    protected void initView() {
        LogUtil.isShowLog = true;
        checkStatue();
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private void checkStatue() {
        boolean isFirst = (boolean) SharedpreferncesUtil.getData(this, "isFirst", true);
        if (isFirst) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(500);
                        SharedpreferncesUtil.saveData(StartActivity.this, "isFirst", false);
                        jumpToPage(GuideActivity.class);
                        StartActivity.this.finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } else {
            new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(500);
                        checkLogin();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    private void checkLogin() {
        String name = (String) SharedpreferncesUtil.getData(this, CMLSConstant.LOGIN_NAME, "");
        if (name != null && !name.isEmpty()) {
            showLog("自动登录:", "");
            login(name);
        } else {
            jumpToPage(MainActivity.class);
            finish();
        }
    }

    private void login(final String phone) {
//        LoginLoader.getInstance().getUserData("自动登录", phone).subscribe(new Action1<UserDataBean>() {
//            @Override public void call(UserDataBean bean) {
//                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus()) && bean.getInfo() != null) {
//                    CMLSApp.userData = bean.getInfo();
//                    CMLSApp.za_userid = phone;
//                }
//                jumpToPage(MainActivity.class);
//                StartActivity.this.finish();
//            }
//        }, new Action1<Throwable>() {
//            @Override public void call(Throwable throwable) {
//                showLog("自动登录:报异常2:", throwable.getMessage());
//                jumpToPage(MainActivity.class);
//                StartActivity.this.finish();
//            }
//        });
    }
}
