package com.dy.cmls.view.pop;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.mine.adapter.InviteImageAdapter;
import com.dy.cmls.mine.bean.InviteImageBean;
import com.dy.cmls.utils.WechatShareManager;
import com.dy.cmls.view.PopupWindow7;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/26.
 */

public class InviteView {


    private Context context;
    private PopupWindow7 invitePopup;

    public InviteView() {
    }

    public InviteView(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }



    public void show(View view) {
        if (invitePopup == null) {
            init();
        }
        invitePopup.showAsDropDown(view);
    }

    public void dismiss() {
        if (invitePopup != null) {
            invitePopup.dismiss();
        }
    }





    private void init() {
        invitePopup =
                new PopupWindow7(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View popView = View.inflate(context, R.layout.popup_invite, null);
        TextView tvClose;
        RecyclerView recyclerView;
        tvClose =  popView.findViewById(R.id.tvClose);
        popView.findViewById(R.id.v_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        recyclerView =  popView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(context,4));
        //adapter
        List<InviteImageBean> list = new ArrayList<>();
        InviteImageAdapter adapter = new InviteImageAdapter(R.layout.item_invite_image, list, context);
        list.add(new InviteImageBean("微信", R.mipmap.btn_wexin_h));
        list.add(new InviteImageBean("朋友圈", R.mipmap.btn_pyq_h));
        list.add(new InviteImageBean("QQ", R.mipmap.btn_qq_h));
        list.add(new InviteImageBean("复制链接", R.mipmap.btn_lian_h));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vew) {
                invitePopup.dismiss();
            }
        });
        WXmanager=WechatShareManager.getInstance(context);
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
        IWXAPI mWxApi = WXAPIFactory.createWXAPI(context, CMLSConstant.WX_APP_ID, false);
// 将该app注册到微信
        mWxApi.registerApp(CMLSConstant.WX_APP_ID);
    }

    String url = "www.baidu.com";

    private WechatShareManager WXmanager;

    private void share(int i) {
        if (url == null) {
            ToastUtils.showShort("获取分享数据错误");
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
            ToastUtils.showShort("尽请期待！");
            return;


        }else if (i == 3){

        }
    }


}
