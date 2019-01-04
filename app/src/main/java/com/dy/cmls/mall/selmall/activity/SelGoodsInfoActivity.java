package com.dy.cmls.mall.selmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mall.selmall.adapter.BannerViewHolder;
import com.dy.cmls.mall.selmall.fragment.GoodsEvaFragment;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.dy.cmls.view.pop.GoodsInfoView;
import com.dy.cmls.view.pop.GoodsSelectView;
import com.dy.cmls.view.pop.GoodsServiceView;
import com.dy.cmls.view.pop.InviteView;
import com.dy.cmls.view.pop.bean.AttrBean;
import com.dy.cmls.view.pop.bean.SelectBean;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lcjing on 2018/12/26.
 */

public class SelGoodsInfoActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.vp)
    com.zhouwei.mzbanner.MZBannerView vp;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.tv_kuaidi)
    TextView tvKuaidi;
    @BindView(R.id.tv_xiaol)
    TextView tvXiaol;
    @BindView(R.id.tv_kucun)
    TextView tvKucun;
    @BindView(R.id.tv_attr)
    TextView tvAttr;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.tv_eva_name)
    TextView tvEvaName;
    @BindView(R.id.tv_eva_des)
    TextView tvEvaDes;
    @BindView(R.id.fl_info)
    FrameLayout flInfo;
    @BindView(R.id.tv_cart_num)
    TextView tvCartNum;
    @BindView(R.id.fl_cart_num)
    FrameLayout flCartNum;
    @BindView(R.id.fl_cart)
    FrameLayout flCart;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.tv_add_cart)
    TextView tvAddCart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_goods_info);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("商品详情");
        List<String> list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545817473486&di=3efca41cbc0ef75727f90e24caaba858&imgtype=0&src=http%3A%2F%2Fimg.sccnn.com%2Fbimg%2F339%2F01210.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1545817501681&di=307237db866c94852e03612e005e9fa0&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F14%2F05%2F72%2F29I58PICSfn_1024.jpg");
        initBanner(list);
    }

    private void initBanner(final List<String> banners) {
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

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private GoodsInfoView goodsInfoView;

    private void showInfoView(List<AttrBean> attrBeans) {
        if (goodsInfoView == null) {
            goodsInfoView = new GoodsInfoView(this, new TJCallBack() {
                @Override
                public void callBack(Intent intent) {

                }
            });
        }
        goodsInfoView.setAttrBeans(attrBeans);
        goodsInfoView.show(tvTitleTitle);
    }

    private GoodsSelectView goodsSelectView;

    private void showSelectView(List<SelectBean> selectBeans) {
        if (goodsSelectView == null) {
            goodsSelectView = new GoodsSelectView(this, new TJCallBack() {
                @Override
                public void callBack(Intent intent) {

                }
            });

        }
        goodsSelectView.setImgUrl("http://pic24.nipic.com/20121025/5901663_210538053391_2.jpg");
        goodsSelectView.setPrice("200.00");
        goodsSelectView.setTotalCount("200");
        goodsSelectView.setTypeBeans(selectBeans);
        goodsSelectView.show(tvTitleTitle);
    }

    private GoodsServiceView goodsServiceView;

    private void showServiceView() {
        if (goodsServiceView == null) {
            goodsServiceView = new GoodsServiceView(this);
        }
        goodsServiceView.show(tvTitleTitle);
    }

    private InviteView inviteView;

    private void showInvite() {
        if (inviteView == null) {
            inviteView = new InviteView(this);
        }
        inviteView.show(tvTitleTitle);
    }

    private boolean inEva = false;//在评价页面
    private List<SelectBean> selectBeanList = new ArrayList<>();
    private GoodsEvaFragment goodsEvaFragment;

    @OnClick({R.id.ivLeft, R.id.ll_share, R.id.ll_service, R.id.ll_attr, R.id.ll_select, R.id.ll_eva, R.id.fl_kf,
            R.id.fl_cart, R.id.iv_collect, R.id.fl_collect, R.id.tv_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                if (inEva) {
                    changeEva();
                } else {
                    finish();
                }

                break;
            case R.id.ll_share://分享
                showInvite();
                break;
            case R.id.ll_service://服务
                showServiceView();
                break;
            case R.id.ll_attr://参数
                List<AttrBean> list = new ArrayList<>();
                list.add(new AttrBean("品牌名称", "艾菲勒"));
                list.add(new AttrBean("厂家", "济南百得加工厂"));
                list.add(new AttrBean("品牌名称", "艾菲勒"));
                list.add(new AttrBean("品牌名称", "艾菲勒"));
                list.add(new AttrBean("品牌名称", "艾菲勒"));
                list.add(new AttrBean("品牌名称", "艾菲勒"));
                list.add(new AttrBean("品牌名称", "艾菲勒"));
                list.add(new AttrBean("品牌名称", "艾菲勒"));
                list.add(new AttrBean("品牌名称", "艾菲勒"));
                showInfoView(list);
                break;
            case R.id.ll_select://选择
                showSelectView(selectBeanList);
                break;
            case R.id.ll_eva://评价
                changeEva();
                break;
            case R.id.fl_kf://客服
                break;
            case R.id.fl_cart://购物车
                jumpToPage(SelLocationListActivity.class);
                break;
            case R.id.fl_collect://收藏
                ivCollect.setImageResource(R.mipmap.btn_collection);
                ivCollect.setImageResource(R.mipmap.btn_collection_sel);
                break;
            case R.id.tv_add_cart://加购物车
                showSelectView(selectBeanList);
                break;
            case R.id.tv_buy://购买
//                showSelectView(selectBeanList);
                jumpToPage(SelOrderConfirmActivity.class);
                break;
        }
    }

    private void changeEva() {
        if (inEva) {
            getSupportFragmentManager().beginTransaction().remove(goodsEvaFragment).commit();
            tvTitleTitle.setText("商品详情");
        } else {
            if (goodsEvaFragment == null) {
                goodsEvaFragment = new GoodsEvaFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, goodsEvaFragment).commit();
            tvTitleTitle.setText("商品评价");
        }
        inEva = !inEva;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if (inEva) {
                changeEva();
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
