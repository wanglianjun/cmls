package com.dy.cmls.mall.multimall.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mall.multimall.listFragment.SellerListFragment;
import com.dy.cmls.view.dialog.SelectAreaDialog;
import com.dy.cmls.view.interfaces.TJCallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SellerListActivity extends BaseActivity {

    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_list);
        ButterKnife.bind(this);
        initView();
    }

    SellerListFragment fragment;

    @Override
    protected void initView() {
        fragment = new SellerListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_list, fragment).commit();

    }


    private boolean popShow=false;
    SelectAreaDialog selectAreaDialog;
    @OnClick({R.id.iv_left, R.id.ll_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.ll_location:
                if (selectAreaDialog==null) {
                    selectAreaDialog=new SelectAreaDialog(new TJCallBack() {
                        @Override
                        public void callBack(Intent intent) {
                            String callback=intent.getStringExtra("callBack");
                            switch (callback){
                                case "action":
                                    popShow=false;
                                    break;
                            }

                        }
                    },this);
                }else  if (popShow) {
                       selectAreaDialog.dismiss();
                       return;
                }
                selectAreaDialog.show(tvLocation);
                popShow=true;
                break;
        }
    }
}
