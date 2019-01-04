package com.dy.cmls.mall.selmall.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.mall.selmall.fragment.OrderListFragment;
import com.dy.cmls.view.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrderActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.m_tab)
    XTabLayout mTab;
    @BindView(R.id.vp)
    ViewPager vp;

    String[] mTitles = {"全部的", "待付款", "待发货", "待收货", "待评价"};

    private int type;

    private List<BaseFragment> listPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        listPages=new ArrayList<>();
        type=getIntent().getIntExtra("type",0);
        initView();

    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("全部订单");
        listPages.clear();
        for (int i = 0; i < mTitles.length; i++) {
            listPages.add(OrderListFragment.getInstance(i));
        }

        mTab.setTabMode(XTabLayout.MODE_FIXED);
        mTab.setTabGravity(XTabLayout.GRAVITY_CENTER);

        FragmentPagerAdapter adapterPage = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return listPages.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return listPages.get(arg0);
            }

            //重写这个方法，将设置每个Tab的标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        };

        vp.setAdapter(adapterPage);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                try {
                    listPages.get(position).onResume();
                } catch (Exception e) {

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //  第三步：将ViewPager与TableLayout 绑定在一起
        mTab.setupWithViewPager(vp);
        vp.setCurrentItem(type);
    }

    @OnClick(R.id.ivLeft)
    public void onViewClicked() {
        finish();
    }
}
