package com.dy.cmls.view.interfaces;

import android.content.Intent;
import android.text.Editable;
import android.text.Selection;
import android.widget.EditText;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class TextListener implements MaxLengthListener {
    private int maxLen = 0;
    private EditText editText = null;
    private TJCallBack tjCallBack;

    public TextListener(int maxLen, EditText editText, TJCallBack tjCallBack) {
        this.maxLen = maxLen;
        this.editText = editText;
        this.tjCallBack = tjCallBack;
    }

    @Override
    public void afterTextChanged(Editable arg0) {

    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        Editable editable = editText.getText();
        int len = editable.length();

        if (len > maxLen) {
            int selEndIndex = Selection.getSelectionEnd(editable);
            String str = editable.toString();
            //截取新字符串
            String newStr = str.substring(0, maxLen);
            editText.setText(newStr);
            editable = editText.getText();

            //新字符串的长度
            int newLen = editable.length();
            //旧光标位置超过字符串长度
            if (selEndIndex > newLen) {
                selEndIndex = editable.length();
            }
            //设置新光标所在的位置
            Selection.setSelection(editable, selEndIndex);
            len = editText.getText().length();
            Intent intent = new Intent();
            intent.putExtra("back", String.valueOf(len + "/" + maxLen));
            tjCallBack.callBack(intent);
        } else {
            Intent intent = new Intent();
            intent.putExtra("back", String.valueOf(len + "/" + maxLen));
            tjCallBack.callBack(intent);
        }
    }
}
