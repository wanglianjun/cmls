package com.dy.cmls.mall.multimall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mall.multimall.fragment.MultiHomeFragment;
import com.dy.cmls.mall.multimall.fragment.MultiMineFragment;
import com.dy.cmls.mall.selmall.fragment.CartFragment;
import com.dy.cmls.mall.selmall.mainfragment.CateFragment;
import com.dy.cmls.mall.selmall.mainfragment.SelHomeFragment;
import com.dy.cmls.mall.selmall.mainfragment.SelMineFragment;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultiHomeActivity extends BaseActivity {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_cate)
    RadioButton rbCate;
    @BindView(R.id.rb_cart)
    RadioButton rbCart;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.rg_button)
    RadioGroup rgButton;

    private MultiHomeFragment homeFragment;
    private CateFragment cateFragment;
    private CartFragment cartFragment;
    private MultiMineFragment mineFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_muilt_home);
        ButterKnife.bind(this);
        initView();
    }


    @Override
    protected void initView() {
        rgButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        if (homeFragment==null) {
                            homeFragment=new MultiHomeFragment();
                        }
                        replaceFragment(homeFragment);
                        break;
                    case R.id.rb_cate:
                        if (cateFragment==null) {
                            cateFragment=CateFragment.getInstance(true);
                        }
                        replaceFragment(cateFragment);
                        break;
                    case R.id.rb_cart:
                        if (cartFragment==null) {
                            cartFragment= CartFragment.getInstance(true);
                        }
                        replaceFragment(cartFragment);
                        break;
                    case R.id.rb_mine:
                        if (mineFragment==null) {
                            mineFragment=new MultiMineFragment();
                        }
                        replaceFragment(mineFragment);
                        break;
                }
            }
        });
        rbHome.setChecked(true);
        if (homeFragment==null) {
            homeFragment=new MultiHomeFragment();
        }
        replaceFragment(homeFragment);

    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.fl_content,fragment).commit();
    }


}
