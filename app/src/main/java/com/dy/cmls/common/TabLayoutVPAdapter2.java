package com.dy.cmls.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class TabLayoutVPAdapter2 extends FragmentPagerAdapter {
    private List<Fragment> list;

    public TabLayoutVPAdapter2(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override public int getCount() {
        return list != null ? list.size() : 0;
    }
}
