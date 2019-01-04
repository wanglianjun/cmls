package com.dy.cmls.mall.selmall.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {

    @BindView(R.id.ll_wx)
    LinearLayout llWx;
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.iv_alipay)
    ImageView ivAlipay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {

    }

    private boolean isWx = true;


//    private void ali(final String orderInfo){
//
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                PayTask alipay = new PayTask(PayActivity.this);
//                Map<String,String> result = alipay.payV2(orderInfo,true);
//
//                Message msg = new Message();
//                msg.what = 1;
//                msg.obj = result;
//                handler.sendMessage(msg);
//            }
//        };
//        // 必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Map<String,String> resultObj = ((Map<String,String>) msg.obj);
                    String resultStatus = resultObj.get("resultStatus");
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        showToast("支付成功");
                        // 跳转到支付成功页面
//                        goToSuccessActivity();
                        setResult(1);
                        finish();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000” 代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            showToast("支付结果确认中");
                        } else {
                            showToast("支付失败");
                        }
                    }

                    break;
            }
        }
    };


    @OnClick({R.id.v_tran, R.id.iv_close, R.id.ll_wx, R.id.ll_alipay, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.v_tran:
            case R.id.iv_close:
                finish();
                break;
            case R.id.ll_wx:
                isWx = true;
                ivWx.setImageResource(R.mipmap.bnt_xuan_sel);
                ivAlipay.setImageResource(R.mipmap.btn_xuan);
                break;
            case R.id.ll_alipay:
                isWx = false;
                ivAlipay.setImageResource(R.mipmap.bnt_xuan_sel);
                ivWx.setImageResource(R.mipmap.btn_xuan);
                break;
            case R.id.tv_confirm:

                break;
        }
    }
}
