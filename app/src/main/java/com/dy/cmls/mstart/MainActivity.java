package com.dy.cmls.mstart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mine.MineFragment;
import com.dy.cmls.mstart.fragment.HomeFragment;
import com.dy.cmls.mkabao.KaBaoFragment;
import com.dy.cmls.utils.MyLocationManager;
import com.dy.cmls.utils.PhoneCameraUtil;
import com.dy.cmls.utils.SPUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    HomeFragment homeFragment;
    KaBaoFragment kaBaoFragment;
    MineFragment mineFragment;
    @BindView(R.id.radio_group_button)
    RadioGroup radioGroupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        homeFragment=new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, homeFragment).commit();
        radioGroupButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment=homeFragment;
                switch (checkedId){
                    case R.id.radio_button_home:
                        if (homeFragment==null) {
                           homeFragment=new HomeFragment();
                        }
                        fragment=homeFragment;
                        break;
                    case R.id.radio_button_kabao:
                        if (kaBaoFragment==null) {
                            kaBaoFragment=new KaBaoFragment();
                        }
                        fragment=kaBaoFragment;
                        if (StringUtils.isEmpty(SPUtils.getUserId())) {
                            ToastUtils.showShort("请先登录！");
                            jumpToPage(LoginActivity.class);
                            return;
                        }
                        break;
                    case R.id.radio_button_profile:
                        if (mineFragment==null) {
                            mineFragment=new MineFragment();
                        }
                        fragment=mineFragment;
                        if (StringUtils.isEmpty(SPUtils.getUserId())) {
                            ToastUtils.showShort("请先登录！");
                            jumpToPage(LoginActivity.class);
                            return;
                        }
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment).commit();
            }
        });


    }


}
