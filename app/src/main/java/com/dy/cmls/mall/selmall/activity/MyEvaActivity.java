package com.dy.cmls.mall.selmall.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.mall.selmall.fragment.MyEvaListFragment;
import com.dy.cmls.mall.selmall.fragment.UnEvaListFragment;
import com.dy.cmls.view.xtablayout.XTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyEvaActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.m_tab)
    XTabLayout mTab;
    @BindView(R.id.vp)
    ViewPager vp;

    private boolean isMul=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_eva);
        ButterKnife.bind(this);
        listPages = new ArrayList<>();
        isMul=getIntent().getBooleanExtra("isMul",false);
        initView();
    }

    String[] mTitles = {"已评价", "未评价"};

    private List<BaseFragment> listPages;

    @Override
    protected void initView() {
        tvTitleTitle.setText("我的评价");
        listPages.clear();
        listPages.add(MyEvaListFragment.getInstance(isMul));
        listPages.add(UnEvaListFragment.getInstance(isMul));
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
    }

    @OnClick(R.id.ivLeft)
    public void onViewClicked() {
        finish();
    }
}
