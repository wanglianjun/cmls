package com.dy.cmls.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.utils.LogUtil;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

public abstract class BaseFragment extends Fragment {
    private QMUITipDialog loadingDialog;
    public Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract void initView(View view);

    public boolean isLogin(){
        return SPUtils.getInstance(CMLSConstant.SP_PATH).getBoolean(CMLSConstant.IS_LOGIN);
    }

    //显示进度条
    protected void showProgressDialog() {
        if (loadingDialog == null) {
            loadingDialog =
                new QMUITipDialog.Builder(activity).setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .setTipWord("正在加载")
                    .create();
        }
        loadingDialog.show();
    }

    //隐藏进度条
    protected void dismissProgressDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    /**
     * General method for starting a new activity either for result or not.
     *
     * @param activityClass The activity to start
     * @param bundle Extra information with this intent.
     * @param isReturn If start for result or not
     * @param requestCode The request code.
     */
    public void jumpToPage(Class<?> activityClass, Bundle bundle, boolean isReturn, int requestCode) {
        if (activityClass == null) {
            return;
        }

        Intent intent = new Intent();
        intent.setClass(getActivity(), activityClass);

        if (bundle != null) {
            intent.putExtras(bundle);
        }

        if (isReturn) {
            startActivityForResult(intent, requestCode);
            String type = activityClass.getSimpleName();
            if ("LoginActivity".equals(type)) {
                getActivity().overridePendingTransition(R.anim.activity_bottom_to_top,
                    R.anim.activity_top_to_bottom);
            }
        } else {
            startActivity(intent);
            String type = activityClass.getSimpleName();
            if ("LoginActivity".equals(type)) {
                getActivity().overridePendingTransition(R.anim.activity_bottom_to_top,
                    R.anim.activity_top_to_bottom);
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

    /**
     * 弹出吐司
     */
    protected void showToast(final String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /* 弹出吐司
    */
    protected void showToastLong(final String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    //弹出吐司
    protected void showToastFailure() {
        Toast.makeText(getActivity(), "请求失败,请重试", Toast.LENGTH_SHORT).show();
    }

    //打Log
    public void showLog(String info, String content) {
        LogUtil.e("TJ", info + content);
    }
}
