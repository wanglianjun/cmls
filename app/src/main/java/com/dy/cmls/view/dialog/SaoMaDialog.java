package com.dy.cmls.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dy.cmls.R;
import com.dy.cmls.utils.PermissionUtils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lcjing on 2018/11/27.
 */

public class SaoMaDialog extends DialogFragment implements View.OnClickListener {


    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置背景透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_saoma, null);
        view.findViewById(R.id.tvOk).setOnClickListener(this);
        view.findViewById(R.id.tvCancel).setOnClickListener(this);
        builder.setView(view);
        return builder.create();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvCancel:
                dismiss();
                break;
            case R.id.tvOk:
                codeRequest();
                break;
        }
    }

    private void codeRequest() {
        ZXingLibrary.initDisplayOpinion(getContext());
        PermissionUtils.getInstance()
                .requestPermission(getActivity(), PermissionUtils.TAG_CAMERA,
                        new PermissionUtils.OnRequestPermissionResult() {
                            @Override
                            public void onSucceed() {
                                openCameraQrCode();
                            }

                            @Override
                            public void onRequestSucceedTag(int tag, String other) {

                            }

                            @Override
                            public void onError(int tag, String error) {

                            }
                        });

    }

    //打开二维码扫描
    private void openCameraQrCode() {
        Intent ercode = new Intent(getContext(), CaptureActivity.class);
        startActivityForResult(ercode, 100);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.getInstance()
                .onRequestPermissionsResult(getActivity(), requestCode, permissions, grantResults,
                        new PermissionUtils.OnRequestPermissionResult() {
                            @Override
                            public void onSucceed() {

                            }

                            @Override
                            public void onRequestSucceedTag(int tag, String other) {
                                openCameraQrCode();
                            }

                            @Override
                            public void onError(int tag, String error) {

                            }
                        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    if (TextUtils.isEmpty(result)) {
                        return;
                    }
//                    verify_code(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    showToast("解析二维码失败");
                }
            }
        }
    }

}
