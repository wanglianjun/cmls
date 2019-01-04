package com.dy.cmls.view;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class EditChangedListener implements TextWatcher {

    // 输入文本之前的状态
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    // 输入文字中的状态，count是一次性输入字符数
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    // 输入文字后的状态
    @Override
    public void afterTextChanged(Editable s) {

    }
}