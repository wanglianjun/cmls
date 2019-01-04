package com.dy.cmls.mstart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextSwitcher;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.loader.IndexLoader;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.IndexBannerBean;
import com.dy.cmls.loader.bean.NewsListBean;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.mall.multimall.MultiHomeActivity;
import com.dy.cmls.mall.selmall.adapter.BannerViewHolder;
import com.dy.cmls.mhome.BuyVipActivity;
import com.dy.cmls.mhome.QiandaoActivity;
import com.dy.cmls.mine.activity.NoticeListActivity;
import com.dy.cmls.mkabao.activity.ShouKuanActivity;
import com.dy.cmls.mall.selmall.SelHomeActivity;
import com.dy.cmls.mstart.LoginActivity;
import com.dy.cmls.utils.MyTextSwitcherUtil;
import com.dy.cmls.utils.SPUtils;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.functions.Action1;

/**
 * Created by lcjing on 2018/12/5.
 */

public class HomeFragment extends BaseFragment {
    List<String> mWarningTextList;
    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.ts_banner)
    TextSwitcher tsBanner;
    @BindView(R.id.vp)
    MZBannerView vp;
    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this, view);
        getBanner();
        getNews();
        return view;
    }

    MyTextSwitcherUtil myTextSwitcher;

    @Override
    protected void initView(View view) {
        mWarningTextList = new ArrayList<>();
//        mWarningTextList.add("1财盟联商APP正式上线了！");
//        mWarningTextList.add("2财盟联商APP正式上线了！");
//        mWarningTextList.add("3财盟联商APP正式上线了！");
//        myTextSwitcher = new MyTextSwitcherUtil(tsBanner, getContext(), mWarningTextList);
//        myTextSwitcher.startFlipping();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (myTextSwitcher!=null) {
            myTextSwitcher.stopFlipping();
            myTextSwitcher.startFlipping();
        }

        if (vp != null) {
            vp.pause();
            vp.start();//开始轮播
        }
        if (!StringUtils.isEmpty(SPUtils.getUserId())) {
            getUserInfo();
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        if (vp != null) {
            vp.pause();//暂停轮播
        }

    }



    private void getUserInfo(){
        showProgressDialog();
        PersonLoader.getInstance().getUserIndex("获取用户信息-我的", SPUtils.getUserId()).subscribe(new Action1<UserInfoBean>() {
            @Override
            public void call(UserInfoBean userInfoBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(userInfoBean.getStatus())) {
                    UserInfoBean.InfoBean info=userInfoBean.getInfo();
                    if (StringUtils.isEmpty( info.getMsg())||info.getMsg().equals("0")) {
                        ivMessage.setImageResource(R.mipmap.nav_message);
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

    private void getBanner(){
        showProgressDialog();
        IndexLoader.getInstance().getIndexBanner("首页-获取banner信息").subscribe(new Action1<IndexBannerBean>() {
            @Override
            public void call(IndexBannerBean indexBannerBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(indexBannerBean.getStatus())) {
                    List<String> banners = new ArrayList<>();
//                    tvVp.setText("1/" + imgBeans.size());
                    for (IndexBannerBean.BannerInfo imgBean : indexBannerBean.getInfo()) {
                        banners.add(imgBean.getImage());
                    }
                    vp.setIndicatorRes(R.drawable.d_null, R.drawable.d_null);
                    vp.setPages(banners, new MZHolderCreator() {
                        @Override
                        public MZViewHolder createViewHolder() {
                            return new BannerViewHolder();
                        }
                    });
                    vp.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int i, float v, int i1) {

                        }

                        @Override
                        public void onPageSelected(int i) {
//                            int b = i + 1;
//                            if (i > imgBeans.size() - 1) {
//                                b = i % imgBeans.size() + 1;
//                            }
//                            tvVp.setText(b + "/" + imgBeans.size());
                        }

                        @Override
                        public void onPageScrollStateChanged(int i) {

                        }
                    });
                }else {
                    ToastUtils.showShort(indexBannerBean.getMessage());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("首页-获取banner信息:报异常2:", throwable.toString());
            }
        });
    }

    private void getNews(){
        showProgressDialog();
        IndexLoader.getInstance().getNews("首页-获取通知公告").subscribe(new Action1<NewsListBean>() {
            @Override
            public void call(NewsListBean indexBannerBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(indexBannerBean.getStatus())) {
                    for (int i = 0; i < indexBannerBean.getInfo().getArticle().size(); i++) {
                        mWarningTextList.add(indexBannerBean.getInfo().getArticle().get(i).getTitle());
                    }
                    myTextSwitcher = new MyTextSwitcherUtil(tsBanner, getContext(), mWarningTextList);
                    myTextSwitcher.startFlipping();
                }else {
                    ToastUtils.showShort(indexBannerBean.getMessage());
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("首页-获取通知公告:报异常2:", throwable.toString());
            }
        });
    }



    @Override
    public void onStop() {
        super.onStop();
        if (myTextSwitcher!=null) {
            myTextSwitcher.stopFlipping();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({ R.id.iv_message,R.id.ll_dh, R.id.ll_sk, R.id.ts_banner, R.id.ll_1, R.id.ll_2, R.id.ll_3, R.id.ll_4, R.id.ll_5, R.id.ll_6, R.id.ll_7, R.id.ll_8, R.id.ll_9})
    public void onViewClicked(View view) {
        if (StringUtils.isEmpty(SPUtils.getUserId())) {
            ToastUtils.showShort("请先登录！");
            jumpToPage(LoginActivity.class);
            return;
        }
        switch (view.getId()) {
            case R.id.iv_message:
                Bundle msg=new Bundle();
                msg.putString("title","消息");
                jumpToPage(NoticeListActivity.class,msg);
                break;
            case R.id.ll_dh://代还
                jumpToPage(LoginActivity.class);
                break;
            case R.id.ll_sk://收款
                jumpToPage(ShouKuanActivity.class);
                break;
            case R.id.ts_banner:
                ToastUtils.showShort(myTextSwitcher.getText());
                break;
            case R.id.ll_1://升级vip
                jumpToPage(BuyVipActivity.class);
                break;
            case R.id.ll_2://好友推广
                break;
            case R.id.ll_3://会员管理
                break;
            case R.id.ll_4://每日签到
                jumpToPage(QiandaoActivity.class);
                break;
            case R.id.ll_5://自营商城
                jumpToPage(SelHomeActivity.class);
                break;
            case R.id.ll_6://多营商城
                jumpToPage(MultiHomeActivity.class);
                break;
            case R.id.ll_7://理财
                break;
            case R.id.ll_8://申请信用卡
                break;
            case R.id.ll_9://贷款
                break;
        }
    }
}
