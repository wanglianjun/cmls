package com.dy.cmls.mall.selmall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mall.selmall.fragment.CartFragment;
import com.dy.cmls.mall.selmall.mainfragment.CateFragment;
import com.dy.cmls.mall.selmall.mainfragment.SelHomeFragment;
import com.dy.cmls.mall.selmall.mainfragment.SelMineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

//自營商城首頁
public class SelHomeActivity extends BaseActivity {

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

    private SelHomeFragment selHomeFragment;
    private CateFragment cateFragment;
    private CartFragment cartFragment;
    private SelMineFragment selMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_home);
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
                        if (selHomeFragment==null) {
                            selHomeFragment=new SelHomeFragment();
                        }
                        replaceFragment(selHomeFragment);
                        break;
                    case R.id.rb_cate:
                        if (cateFragment==null) {
                            cateFragment=CateFragment.getInstance(false);
                        }
                        replaceFragment(cateFragment);
                        break;
                    case R.id.rb_cart:
                        if (cartFragment==null) {
                            cartFragment=CartFragment.getInstance(false);
                        }
                        replaceFragment(cartFragment);
                        break;
                    case R.id.rb_mine:
                        if (selMineFragment==null) {
                            selMineFragment=new SelMineFragment();
                        }
                        replaceFragment(selMineFragment);
                        break;
                }
            }
        });
        rbHome.setChecked(true);
        if (selHomeFragment==null) {
            selHomeFragment=new SelHomeFragment();
        }
        replaceFragment(selHomeFragment);

    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.fl_content,fragment).commit();
    }

}
