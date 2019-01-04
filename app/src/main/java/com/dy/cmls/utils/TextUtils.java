package com.dy.cmls.utils;

import android.os.Build;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;

/**
 * Created by lcjing on 2018/12/26.
 */

public class TextUtils {

    public static void setMaxEcplise(final TextView textView, final int maxLines, final String content) {
        ViewTreeObserver observer = textView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {


            @Override
            public void onGlobalLayout() {
                textView.setText(content);
                if (textView.getLineCount() > maxLines) {
                    int lineEndIndex = textView.getLayout().getLineEnd(maxLines - 1);
//下面这句代码中：我在项目中用数字3发现效果不好，改成1了
                    String text = content.subSequence(0, lineEndIndex - 3) + "...";
                    textView.setText(text);
                } else {
                    removeGlobalOnLayoutListener(textView.getViewTreeObserver(), this);
                }
            }
        });
    }


    public static String checkCode(String smsCode,String editCode){
        if (StringUtils.isEmpty(smsCode)) {
            return "请先获取短信验证码";
        }
        if(StringUtils.isEmpty(editCode)){
            return "请输入短信验证码";
        }
        if(!smsCode.equals(editCode)){
            return "验证码输入错误";
        }
        return "";
    }

    public static String checkPass(EditText etPass, EditText etConfirmPass){
        String pass=etPass.getText().toString();
        String confirmPass=etConfirmPass.getText().toString();
        if (StringUtils.isEmpty(pass)) {
            etPass.requestFocus();
            return "请输入密码";
        }
        if (pass.length()<6) {
            return "密码长度不能小于6位";
        }
        if (pass.length()>20) {
            return "密码长度不能大于20位";
        }
        if(StringUtils.isEmpty(confirmPass)){
            etConfirmPass.requestFocus();
            return "请再次输入密码";
        }
        if(!pass.equals(confirmPass)){
            return "您两次输入的密码不同";
        }
        return "";
    }

    private static void removeGlobalOnLayoutListener(ViewTreeObserver obs, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (obs == null) return;
        if (Build.VERSION.SDK_INT < 16) {
            obs.removeGlobalOnLayoutListener(listener);
        } else {
            obs.removeOnGlobalLayoutListener(listener);
        }
    }


}
