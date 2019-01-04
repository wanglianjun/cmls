package com.dy.cmls.mine.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mine.fragment.MemberListFragment;
import com.dy.cmls.view.xtablayout.XTabLayout;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemberManageActivity extends BaseActivity {

    @BindView(R.id.tab_x)
    XTabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mViewPage;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    private String[] mTitles = {
            "直推用户", "间推用户"
    };
    private List<Fragment> listPages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_manage);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        listPages.clear();
        for (int i = 0; i < mTitles.length; i++) {
            listPages.add(MemberListFragment.getInstance(mTitles[i]));
        }
        mTabLayout.setTabMode(XTabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(XTabLayout.GRAVITY_CENTER);

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

        mViewPage.setAdapter(adapterPage);

        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        mTabLayout.setupWithViewPager(mViewPage);
    }

    @OnClick({R.id.ivLeft, R.id.tab_x})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tab_x:
                break;
        }
    }
}
