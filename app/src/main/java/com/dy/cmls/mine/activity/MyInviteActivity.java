package com.dy.cmls.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.mine.adapter.InviteImageAdapter;
import com.dy.cmls.mine.bean.InviteImageBean;
import com.dy.cmls.mine.bean.MyInviteListBean;
import com.dy.cmls.mine.bean.MyInviteResBean;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.utils.WechatShareManager;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class MyInviteActivity extends BaseActivity {

    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_des)
    TextView tvDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        setContentView(R.layout.activity_my_invite);
        ButterKnife.bind(this);
        WXmanager=WechatShareManager.getInstance(this);
        initView();
    }

    @Override
    protected void initView() {
        getInviteInfo();
    }



    private void getInviteInfo(){
        PersonLoader.getInstance().getSpreadInfo("我的邀请", SPUtils.getUserId()).subscribe(new Action1<MyInviteResBean>() {
            @Override
            public void call(MyInviteResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    tvCode.setText("您的邀请码："+bean.getInfo().getCode());
                    Glide.with(MyInviteActivity.this).load(bean.getInfo().getQrcode()).into(ivCode);
                    url=bean.getInfo().getUrl();
                    String des="1、"+bean.getInfo().getLevel().get_$1()+"\n2、"+bean.getInfo().getLevel().get_$2()+"\n3、"+bean.getInfo().getLevel().get_$3();
                    tvDes.setText(des);
                }else {
                    ToastUtils.showShort(bean.getMessage());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("我的邀请:报异常2:", throwable.toString());
            }
        });
    }



    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                jumpToPage(InviteLogActivity.class);
                break;
            case R.id.tv_share:
                if (invitePopup == null) {
                    initPopup();
                }
                invitePopup.showAtLocation(tvCode, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    private WechatShareManager WXmanager;
    private PopupWindow invitePopup;
    private BaseUiListener listener;

    private void initPopup() {
        registerToWX();
        View popView = View.inflate(this, R.layout.popup_invite, null);
        invitePopup =
                new PopupWindow(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        TextView tvClose;
        RecyclerView recyclerView;
        tvClose = (TextView) popView.findViewById(R.id.tvClose);
        recyclerView = (RecyclerView) popView.findViewById(R.id.recyclerView);
        //recyclerView的初始化
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //adapter
        List<InviteImageBean> list = new ArrayList<>();
        InviteImageAdapter adapter = new InviteImageAdapter(R.layout.item_invite_image, list, this);
        list.add(new InviteImageBean("微信好友", R.mipmap.icon_weixin1));
        list.add(new InviteImageBean("微信朋友圈", R.mipmap.icon_pengyouquan));
        list.add(new InviteImageBean("QQ", R.mipmap.icon_qq));
        list.add(new InviteImageBean("QQ空间", R.mipmap.icon_qqspace));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vew) {
                invitePopup.dismiss();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                invitePopup.dismiss();
                if (position == 0) {
                    share(0);
                } else if (position == 1) {
                    share(1);
                } else if (position == 2) {
                    share(2);
                } else if (position == 3) {
                    share(3);
                }
            }
        });
        invitePopup.setFocusable(true);
        invitePopup.setContentView(popView);
        invitePopup.setAnimationStyle(R.style.popwin_anim_style1);
    }

    private void registerToWX() {
//第二个参数是指你应用在微信开放平台上的AppID
        IWXAPI mWxApi = WXAPIFactory.createWXAPI(this, CMLSConstant.WX_APP_ID, false);
// 将该app注册到微信
        mWxApi.registerApp(CMLSConstant.WX_APP_ID);
    }

    String url = "www.baidu.com";

    private void share(int i) {
        if (url == null) {
            showToast("获取分享数据错误");
            return;
        }
        if (i == 0) {
            WechatShareManager.ShareContentWebpage shareContentWebpage =
                    (WechatShareManager.ShareContentWebpage) WXmanager.getShareContentWebpag(
                            "财盟联商！",
                            "财盟联商",
                            url, R.mipmap.ic_launcher);
            WXmanager.shareByWebchat(shareContentWebpage, WechatShareManager.WECHAT_SHARE_TYPE_TALK);
        } else if (i == 1) {
            WechatShareManager.ShareContentWebpage shareContentWebpage =
                    (WechatShareManager.ShareContentWebpage) WXmanager.getShareContentWebpag(
                            "财盟联商！",
                            "",
                            url, R.mipmap.ic_launcher);
            WXmanager.shareByWebchat(shareContentWebpage, WechatShareManager.WECHAT_SHARE_TYPE_FRENDS);
        } else if (i == 2) {
            Tencent mTencent = Tencent.createInstance("1107870825", this.getApplicationContext());
            Bundle params = new Bundle();
            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
            params.putString(QQShare.SHARE_TO_QQ_TITLE, "财盟联商！");
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY,
                    "财盟联商");
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, CMLSConstant.PICTURE_URL);
            listener = new BaseUiListener();
            if (mTencent!=null) {
                mTencent.shareToQQ(this, params, listener);
            }

        }
    }


    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            showToast("分享成功");
        }

        @Override
        public void onError(UiError e) {
            showToast("分享失败");
            /*showResult("onError:", "code:" + e.errorCode + ", msg:" + e.errorMessage + ", detail:" + e
                    .errorDetail);*/
        }

        @Override
        public void onCancel() {
            /*showResult("onCancel", "");*/
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, listener);
    }



}
