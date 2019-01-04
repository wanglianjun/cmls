package com.dy.cmls.view.interfaces;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public interface MaxLengthListener extends TextWatcher {

    void afterTextChanged(Editable arg0);

    void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3);

    void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3);
}
