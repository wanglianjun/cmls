package com.dy.cmls.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.dy.cmls.CMLSApp;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.utils.LogUtil;
import com.noober.background.BackgroundLibrary;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

/**
 * Created by Administrator on
 */

public abstract class BaseActivity extends FragmentActivity {
    public boolean isFirstShow;
    private QMUITipDialog loadingDialog;
    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        CMLSApp.getInstance().addActivity(this);
        isFirstShow = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isFirstShow = false;
    }

    @Override
    protected void onDestroy() {
        CMLSApp.getInstance().finishActivity(this);
        super.onDestroy();
    }

    protected abstract void initView();

    public boolean isLogin() {
        return SPUtils.getInstance(CMLSConstant.SP_PATH).getBoolean(CMLSConstant.IS_LOGIN);
    }

    public void initTitle(String title) {
        ImageView ivLeft;
        ivLeft = (ImageView) findViewById(R.id.ivLeft);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.this.finish();
            }
        });

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setText(title);
    }

    public void initTitle() {
        tvTitle = (TextView) findViewById(R.id.tvTitle);
    }

    /**
     * General method for starting a new activity either for result or not.
     *
     * @param activityClass The activity to start
     * @param bundle        Extra information with this intent.
     * @param isReturn      If start for result or not
     * @param requestCode   The request code.
     */
    public void jumpToPage(Class<?> activityClass, Bundle bundle, boolean isReturn, int requestCode) {
        if (activityClass == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setClass(this, activityClass);

        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (isReturn) {
            startActivityForResult(intent, requestCode);
            String type = activityClass.getSimpleName();
            if ("LoginActivity".equals(type)) {
                overridePendingTransition(R.anim.activity_bottom_to_top, R.anim.activity_top_to_bottom);
            }
        } else {
            startActivity(intent);
            String type = activityClass.getSimpleName();
            if ("LoginActivity".equals(type)) {
                overridePendingTransition(R.anim.activity_bottom_to_top, R.anim.activity_top_to_bottom);
            }
        }
    }

    public void jumpToPage(Class<?> activityClass) {
        jumpToPage(activityClass, null, false, 0);
    }

    public void jumpToPage(Class<?> activityClass, Bundle bundle) {
        jumpToPage(activityClass, bundle, false, 0);
    }

    public void jumpToPage(Class<?> activityClass, boolean isReturn, int requestCode) {
        jumpToPage(activityClass, null, isReturn, requestCode);
    }

    public void jumpToPage(Class<?> activityClass, boolean isReturn, Bundle bundle, int requestCode) {
        jumpToPage(activityClass, bundle, isReturn, requestCode);
    }

    //弹出吐司
    protected void showToastLong(final String msg) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    //弹出吐司
    protected void showToast(final String msg) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //弹出吐司
    protected void showToastFailure() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "请求失败,请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //弹出吐司
    protected void showToastFailure(final String info) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), info, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //打Log
    public void showLog(String info, String content) {
        LogUtil.e("", info + content);
    }

    /**
     * 显示进度条
     */
    public void showProgressDialog() {
        if (loadingDialog == null) {
            loadingDialog = new QMUITipDialog.Builder(this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord("正在加载")
                    .create();
        }
        loadingDialog.show();
    }

    /**
     * 显示进度条
     */
    protected void showProgressDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new QMUITipDialog.Builder(this).setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord(msg)
                    .create();
        }
        loadingDialog.show();
    }

    /**
     * 隐藏进度条
     */
    public void dismissProgressDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    public void errorOut() {
        showToast("获取数据失败,请重试");
        this.finish();
    }

    public void notifyPopup(String textContent, final boolean isResultRefresh, final boolean isFinish) {
        if (textContent == null) {
            textContent = "获取数据失败,请重试";
        }
        View popView = View.inflate(BaseActivity.this, R.layout.popup_notify, null);
        final PopupWindow notifyPopup =
                new PopupWindow(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        ImageView ivClose;
        TextView tvTtiel;
        ivClose = (ImageView) popView.findViewById(R.id.ivClose);
        tvTtiel = (TextView) popView.findViewById(R.id.tvTtiel);

        tvTtiel.setText(textContent);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyPopup.dismiss();
                if (isResultRefresh) {
                    BaseActivity.this.setResult(CMLSConstant.RESULT_REFRESH);
                }
                if (isFinish) {
                    BaseActivity.this.finish();
                }
            }
        });
        notifyPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (isResultRefresh) {
                    BaseActivity.this.setResult(CMLSConstant.RESULT_REFRESH);
                }
                if (isFinish) {
                    BaseActivity.this.finish();
                }
            }
        });
        notifyPopup.setFocusable(true);
        notifyPopup.setContentView(popView);
        if (tvTitle == null) {
            showToastLong("获取数据失败,请重试");
            return;
        }
        notifyPopup.showAtLocation(tvTitle, Gravity.TOP, 0, 0);
    }
}
