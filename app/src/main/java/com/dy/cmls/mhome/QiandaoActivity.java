package com.dy.cmls.mhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.view.calendar.MyCalendarView;
import com.dy.cmls.view.calendar.MyCalendarView.DateChangedListener;
import com.dy.cmls.view.dialog.NotifyImageDialog;
import com.dy.cmls.view.dialog.ShowImageDialog;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QiandaoActivity extends BaseActivity implements DateChangedListener{

    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.m_cal_view)
    MyCalendarView mCalView;
    @BindView(R.id.tv_qiandao)
    TextView tvQiandao;
    @BindView(R.id.tv_des)
    TextView tvDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_qiandao);
        ButterKnife.bind(this);
        initView();
    }
    private boolean hasQiandao=false;

    @Override
    protected void initView() {
        mCalView.setDateChanged(this);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        onDateChanged(year,month,1,1);

    }

    private void initStatus(){
        if (hasQiandao) {
            tvStatus.setText("今天已签到，明日再来吧");
            tvQiandao.setBackground(getResources().getDrawable(R.drawable.rectangle_gray_full_20));
        }else {
            tvStatus.setText("今天还没有签到，赶紧签到领积分吧");
            tvQiandao.setBackground(getResources().getDrawable(R.drawable.app_bt));
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.iv_left, R.id.iv_right, R.id.tv_qiandao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:
                jumpToPage(GuiZeActivity.class);
                break;
            case R.id.iv_left:
                setCalView(true);
                break;
            case R.id.iv_right:
                setCalView(false);
                break;
            case R.id.tv_qiandao:
                if (hasQiandao) {
                    return;
                }
                ShowImageDialog imageDialog=new ShowImageDialog(this,R.mipmap.ic_blue_zhic);
                imageDialog.show();
                hasQiandao=true;
                initStatus();
                break;
        }
    }

    private void setCalView(boolean isLeft){
        if (isLeft) {
            mCalView.onLeftClick();
        }else {
            mCalView.onRightClick();
        }
    }

    @Override
    public void onDateChanged(int year, int month, int day, int rows) {
        month=month+1;
        String m;
        if(month<10){
            m="0"+month;
        }else {
            m=""+month;
        }
        tvDate.setText(year+"年"+m+"月");
    }

    @Override
    public void otherChange(int lOR, int day) {

    }
}
