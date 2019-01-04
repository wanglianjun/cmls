package com.dy.cmls.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.dy.cmls.CMLSConstant;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wechat_entry);
        QMUIStatusBarHelper.translucent(this);

        api = WXAPIFactory.createWXAPI(this, CMLSConstant.WX_APP_ID);
        api.handleIntent(getIntent(), this);

        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            api.handleIntent(getIntent(), this);
            //        api = WXAPIFactory.createWXAPI(this, "");
            //        api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Intent intent = null;
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (baseResp.errCode) {
                case 0:
                    intent = new Intent("wechatPay");
                    intent.putExtra("callback", "yes");
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                    break;
                case -1:
                    intent = new Intent("wechatPay");
                    intent.putExtra("callback", "no");
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                    Toast.makeText(this, "抱歉，支付出错了，可能原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等，请联系客服处理！",
                        Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    intent = new Intent("wechatPay");
                    intent.putExtra("callback", "no");
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                    Toast.makeText(this, "您取消了支付操作！", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(this, "抱歉，支付未完成，发生了未知错误，错误号：" + baseResp.errCode + ", 请联系客服处理！", Toast.LENGTH_SHORT).show();
                    break;
            }
            finish();
        }
    }
}
