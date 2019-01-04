package com.dy.cmls.view.interfaces;

import com.dy.cmls.view.ObservedScrollView;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public interface ScrollViewListener {
    void onScrollChanged(ObservedScrollView scrollView, int x, int y, int oldx, int oldy);
}
