package com.dy.cmls.mkabao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mkabao.adapter.date.OneDayTimesPop;
import com.dy.cmls.mkabao.adapter.date.PopDate;
import com.dy.cmls.mkabao.bean.date.VDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPlanActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_day_times)
    TextView tvDayTimes;
    @BindView(R.id.tv_fee_money)
    TextView tvFeeMoney;
    @BindView(R.id.tv_xf_money)
    TextView tvXfMoney;
    @BindView(R.id.tv_hk_money)
    TextView tvHkMoney;
    @BindView(R.id.tv_ls_name)
    TextView tvLsName;
    @BindView(R.id.tv_yl_money)
    TextView tvYlMoney;


    //还款日期
    private PopDate popDate;
    private String huankuan = "05";
    private VDate _checkInDate_ed, _checkOutDate_ed;
    //每日还款次数
    private OneDayTimesPop timesPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("新增还款计划");
        tvTitleTitle.requestFocus();
    }


    @OnClick({R.id.ivLeft, R.id.ll_date, R.id.ll_day_times, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ll_date:
                if (popDate == null) {
                    popDate = new PopDate(this, tvDate, huankuan, _checkInDate_ed, _checkOutDate_ed);
                }
                popDate.show();
                break;
            case R.id.ll_day_times:
                if (timesPop==null) {
                    timesPop=new OneDayTimesPop(5,tvDayTimes);
                }
                timesPop.show();
                break;
            case R.id.tv_next:
                if (StringUtils.isEmpty(etMoney.getText().toString())) {
                    ToastUtils.showShort("请输入还款金额");
                    return;
                }

                if (StringUtils.isEmpty(tvDate.getText().toString())) {
                    ToastUtils.showShort("请选择还款日期");
                    return;
                }
                if (StringUtils.isEmpty(tvDayTimes.getText().toString())) {
                    ToastUtils.showShort("请选择每日还款次数");
                    return;
                }
                jumpToPage(PlanPreviewActivity.class,true,1);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            switch (resultCode){
                case 1:
                    //跳转到计划列表页面
                    finish();
                    break;
                case 2:
                    //重新制定计划
                    break;
            }
        }
    }
}
