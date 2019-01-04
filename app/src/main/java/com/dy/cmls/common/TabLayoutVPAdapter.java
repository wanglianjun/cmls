package com.dy.cmls.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class TabLayoutVPAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private String[] titles;

    public TabLayoutVPAdapter(FragmentManager fm, List<Fragment> list, String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }

    @Override public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override public int getCount() {
        return list != null ? list.size() : 0;
    }

    //重写这个方法，将设置每个Tab的标题
    @Override public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
