package com.dy.cmls.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.dy.cmls.CMLSConstant;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, CMLSConstant.WX_APP_ID, false);
        api.registerApp(CMLSConstant.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq arg0) {

    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                if (ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX == resp.getType()) {
                    Toast.makeText(getApplication(), "分享成功", Toast.LENGTH_SHORT).show();
                    break;
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX == resp.getType()) {
                    Toast.makeText(getApplication(), "分享取消", Toast.LENGTH_SHORT).show();
                    break;
                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                if (ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX == resp.getType()) {
                    Toast.makeText(getApplication(), "分享被拒绝", Toast.LENGTH_SHORT).show();
                    break;
                }
                break;
        }
        finish();
    }
}
