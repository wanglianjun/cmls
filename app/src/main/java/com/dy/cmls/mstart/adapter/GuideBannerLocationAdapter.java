package com.dy.cmls.mstart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dy.cmls.view.interfaces.TJCallBack;


/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class GuideBannerLocationAdapter extends PagerAdapter {
    private Context context;
    private int[] list;
    private View[] views;
    private TJCallBack tjCallBack;

    public GuideBannerLocationAdapter(Context context, int[] list, TJCallBack tjCallBack) {
        this.context = context;
        this.list = list;
        this.tjCallBack = tjCallBack;
        views = new View[list.length];
    }

    @Override public int getCount() {
        return list.length;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return object == (view);
    }

    @Override public Object instantiateItem(ViewGroup container, final int position) {
        if (views[position] == null) {
            views[position] = new ImageView(context);
            ((ImageView) views[position]).setScaleType(ImageView.ScaleType.FIT_XY);
            views[position].setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("position", position);
                    tjCallBack.callBack(intent);
                }
            });
        }
        ((ImageView) views[position]).setImageResource(list[position]);
        container.addView(views[position]);
        return views[position];
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        //        super.destroyItem(container, position, object);
        container.removeView(views[position]);
    }
}
