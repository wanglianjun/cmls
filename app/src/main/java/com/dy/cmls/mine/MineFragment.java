package com.dy.cmls.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.mall.multimall.MultiHomeActivity;
import com.dy.cmls.mine.activity.BuyUsufructActivity;
import com.dy.cmls.mine.activity.MemberManageActivity;
import com.dy.cmls.mine.activity.MyInviteActivity;
import com.dy.cmls.mine.activity.MyScoreActivity;
import com.dy.cmls.mine.activity.MyWalletActivity;
import com.dy.cmls.mine.activity.NoticeListActivity;
import com.dy.cmls.mine.activity.SafetyCenterActivity;
import com.dy.cmls.mine.activity.SettingActivity;
import com.dy.cmls.mine.activity.UserInfoActivity;
import com.dy.cmls.mall.selmall.SelHomeActivity;
import com.dy.cmls.utils.GlideUtils;
import com.dy.cmls.utils.SPUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by lcjing on 2018/12/7.
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.roundedImageView)
    RoundedImageView roundedImageView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_qiandao)
    TextView tvQiandao;
    @BindView(R.id.tv_yue)
    TextView tvYue;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_tjr)
    TextView tvTjr;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {

    }


    @Override
    public void onResume() {
        super.onResume();
        getIndex();
    }

    private void getIndex(){
        showProgressDialog();
        PersonLoader.getInstance().getUserIndex("获取用户信息-我的", SPUtils.getUserId()).subscribe(new Action1<UserInfoBean>() {
            @Override
            public void call(UserInfoBean userInfoBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(userInfoBean.getStatus())) {
                    UserInfoBean.InfoBean info=userInfoBean.getInfo();
                    SPUtils.setUserInfo(userInfoBean.getInfo().getPhone(),userInfoBean.getInfo().getNick_name());
                    SPUtils.setRealNameAndId(info.getIdcard(),info.getRealname());
                    tvName.setText(info.getNick_name());
                    tvJifen.setText(info.getAccount_money());
                    tvYue.setText(info.getAccount_money());
                    tvType.setText(info.getLevel());
                    if (StringUtils.isEmpty( info.getMsg())||info.getMsg().equals("0")) {
                        ivMessage.setImageResource(R.mipmap.nav_message);
                    }

                    if (!StringUtils.isEmpty(info.getAvatar())) {
//                        Glide.with(getContext()).load(info.getAvatar()).into(roundedImageView);
                        GlideUtils.setImage(roundedImageView,R.mipmap.ic_default_small,info.getAvatar());
                    }

                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("获取用户信息-我的:报异常2:", throwable.toString());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_setting, R.id.iv_message, R.id.roundedImageView, R.id.ll_info, R.id.ll_yue, R.id.ll_jifen,
            R.id.ll_ziy, R.id.ll_duoy, R.id.fl_wallet, R.id.fl_zjls, R.id.fl_shiyq, R.id.fl_safety, R.id.fl_hygl,
            R.id.fl_tjr, R.id.fl_share, R.id.fl_gonggao, R.id.fl_qiaodao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                jumpToPage(SettingActivity.class);
                break;
            case R.id.iv_message:
                Bundle msg=new Bundle();
                msg.putString("title","消息");
                jumpToPage(NoticeListActivity.class,msg);
                break;
            case R.id.ll_info:
            case R.id.roundedImageView:
                jumpToPage(UserInfoActivity.class);
                break;
            case R.id.fl_qiaodao:
                break;
            case R.id.ll_yue:
                break;
            case R.id.ll_jifen:
                jumpToPage(MyScoreActivity.class);
                break;
            case R.id.ll_ziy:
                jumpToPage(SelHomeActivity.class);
                break;
            case R.id.ll_duoy:
                jumpToPage(MultiHomeActivity.class);
                break;
            case R.id.fl_wallet:
                jumpToPage(MyWalletActivity.class);
                break;
            case R.id.fl_zjls:
                break;
            case R.id.fl_shiyq:

                jumpToPage(BuyUsufructActivity.class);
                break;
            case R.id.fl_safety:
                jumpToPage(SafetyCenterActivity.class);
                break;
            case R.id.fl_hygl:
                jumpToPage(MemberManageActivity.class);
                break;
            case R.id.fl_tjr:
                break;
            case R.id.fl_share:
                jumpToPage(MyInviteActivity.class);
                break;
            case R.id.fl_gonggao:
                Bundle notice=new Bundle();
                notice.putString("title","平台公告");
                jumpToPage(NoticeListActivity.class,notice);
                break;
        }
    }
}
