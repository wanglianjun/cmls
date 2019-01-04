package com.dy.cmls.mkabao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mkabao.adapter.PlanAdapter;
import com.dy.cmls.mkabao.bean.HKPlanBean;
import com.dy.cmls.view.dialog.CreditCardActionDialog;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreditCardInfoActivity extends BaseActivity {

    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.tv_bank)
    TextView tvBank;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_zd_date)
    TextView tvZdDate;
    @BindView(R.id.tv_hk_date)
    TextView tvHkDate;
    @BindView(R.id.fl_empty)
    FrameLayout flEmpty;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    PlanAdapter adapter;
    List<HKPlanBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_credit_card_info);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        initView();
    }

    @Override
    protected void initView() {
        adapter=new PlanAdapter(R.layout.item_hk_plan,list,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getList();
    }

    private void getList() {
        list.add(new HKPlanBean("1500.00","待执行","2.00","2018-11-28 14:32:20"));
        list.add(new HKPlanBean("1500.00","待执行","2.00","2018-11-28 14:32:20"));
        list.add(new HKPlanBean("1500.00","待执行","2.00","2018-11-28 14:32:20"));
        list.add(new HKPlanBean("1500.00","待执行","2.00","2018-11-28 14:32:20"));
        list.add(new HKPlanBean("1500.00","待执行","2.00","2018-11-28 14:32:20"));
        adapter.notifyDataSetChanged();
        if(list.size()<1){
            recyclerView.setVisibility(View.GONE);
            flEmpty.setVisibility(View.VISIBLE);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            flEmpty.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.iv_back, R.id.iv_menu, R.id.tv_add_plan, R.id.tv_check_plan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_menu:
                showActionDialog();
                break;
            case R.id.tv_add_plan:
                jumpToPage(AddPlanActivity.class);
                break;
            case R.id.tv_check_plan:
                jumpToPage(PlanListActivity.class);
                break;
        }
    }

    private void showActionDialog(){
        CreditCardActionDialog creditCardActionDialog=new CreditCardActionDialog(this, new TJCallBack() {
            @Override
            public void callBack(Intent intent) {
             switch (intent.getStringExtra("callBack")){
                 case "1"://解绑

                     break;
                 case "2"://修改资料
                     jumpToPage(EditCreditCardActivity.class);
                     break;
                 case "3"://计划解冻
                     break;
             }
            }
        });
        creditCardActionDialog.show();
    }
}
