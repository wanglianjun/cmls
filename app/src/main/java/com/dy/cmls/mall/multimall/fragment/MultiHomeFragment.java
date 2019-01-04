package com.dy.cmls.mall.multimall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.mall.multimall.activity.SellerListActivity;
import com.dy.cmls.mall.multimall.adapter.MulBannerViewHolder;
import com.dy.cmls.mall.multimall.adapter.MulNearbyAdapter;
import com.dy.cmls.mall.multimall.adapter.MultiHomeCateAdapter;
import com.dy.cmls.mall.multimall.bean.MultiHomeCateBean;
import com.dy.cmls.mall.selmall.activity.SearchActivity;
import com.dy.cmls.mall.selmall.activity.SelGoodsInfoActivity;
import com.dy.cmls.mall.selmall.adapter.SelHomeLikeAdapter;
import com.dy.cmls.mall.selmall.bean.LikeBean;
import com.dy.cmls.mkabao.bean.KabaoBean;
import com.dy.cmls.view.CustomLoadMoreView;
import com.dy.cmls.view.dialog.RedPacketDialog;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lcjing on 2019/1/2.
 */

public class MultiHomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    MZBannerView banner;
    @BindView(R.id.rv_cate)
    RecyclerView rvCate;
    @BindView(R.id.rv_nearby)
    RecyclerView rvNearby;
    @BindView(R.id.rv_like)
    RecyclerView rvLike;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multi_home, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView(View view) {
        List<String> banners = new ArrayList<>();
        banners.add("http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg");
        banners.add("http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg");
        banner.setPages(banners, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new MulBannerViewHolder();
            }
        });
        initCate();
        initNearby();
        initLike();
//        SelHomeLikeFragment selHomeLikeFragment=new SelHomeLikeFragment();
//        getChildFragmentManager().beginTransaction().replace(R.id.fl_like,selHomeLikeFragment).commit();
    }

    private void initCate() {
        List<MultiHomeCateBean> list = new ArrayList<>();
        list.add(new MultiHomeCateBean( "母婴用品",R.mipmap.btn_myyp));
        list.add(new MultiHomeCateBean( "潮流服装",R.mipmap.btn_clfz));
        list.add(new MultiHomeCateBean( "护肤美妆",R.mipmap.btn_hfmz));
        list.add(new MultiHomeCateBean( "居家用品",R.mipmap.btn_jjyp));
        list.add(new MultiHomeCateBean( "食品饮料",R.mipmap.btn_spyl));
        list.add(new MultiHomeCateBean( "家用电器",R.mipmap.btn_jydq ));
        list.add(new MultiHomeCateBean( "电子科技",R.mipmap.btn_dzkj));
        list.add(new MultiHomeCateBean( "更多",R.mipmap.btn_more1));
        MultiHomeCateAdapter cateAdapter = new MultiHomeCateAdapter(R.layout.item_sel_home_cate, list, getContext());
        rvCate.setLayoutManager(new GridLayoutManager(getContext(),4));
        rvCate.setAdapter(cateAdapter);
    }

    private void initNearby() {
        List<KabaoBean> kabaoBeans = new ArrayList<>();
        kabaoBeans.add(new KabaoBean("http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg", "韩都衣舍服饰"));
        kabaoBeans.add(new KabaoBean("http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg", "韩都衣舍服饰"));
        kabaoBeans.add(new KabaoBean("http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg", "韩都衣舍服饰"));
        kabaoBeans.add(new KabaoBean("http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg", "韩都衣舍服饰"));
        kabaoBeans.add(new KabaoBean("http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg", "韩都衣舍服饰"));
        kabaoBeans.add(new KabaoBean("http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg", "韩都衣舍服饰"));
        MulNearbyAdapter nearbyAdapter = new MulNearbyAdapter(R.layout.item_mul_nearby, kabaoBeans, getContext());
        nearbyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                jumpToPage(SelGoodsInfoActivity.class);
            }
        });
        nearbyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RedPacketDialog dialog=new RedPacketDialog();
                dialog.show(getChildFragmentManager(),"");
//                getChildFragmentManager().beginTransaction().show(dialog).commit();
            }
        });
        rvNearby.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rvNearby.setAdapter(nearbyAdapter);
    }

    private int pageLike=0;
    private  List<LikeBean> list;
    private SelHomeLikeAdapter adapter;
    private void initLike(){
        list = new ArrayList<>();
        adapter = new SelHomeLikeAdapter(R.layout.item_sel_home_like, list, getContext());
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//加载动画
        adapter.isFirstOnly(true);//是否只显示一次动画
        adapter.setNotDoAnimationCount(1);//没有动画的view个数
        adapter.setLoadMoreView(new CustomLoadMoreView());//设置上拉view
        adapter.loadMoreEnd(true);//隐藏上拉view
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                requestList(false);
//                waveSwipeRefreshLayout.setEnabled(false);
            }
        }, rvLike);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                jumpToPage(SelGoodsInfoActivity.class);
            }
        });
        rvLike.setLayoutManager(new GridLayoutManager(getContext(),2));
        rvLike.setAdapter(adapter);
        requestList(true);
    }

    private void requestList( boolean isRefresh) {
        if (isRefresh) {
            pageLike=0;
            list.clear();
        }
        pageLike++;
        showProgressDialog();
        list.add(new LikeBean("农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装","￥600.00"
                ,"12","http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"));
        list.add(new LikeBean("瓷罐金骏眉茶叶礼盒装武夷山金俊眉正宗红茶","￥199.99",
                "3321","http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg"));
        list.add(new LikeBean("瓷罐金骏眉茶叶礼盒装武夷山金俊眉正宗红茶","￥199.99",
                "3321","http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg"));

        list.add(new LikeBean("农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装","￥600.00"
                ,"12","http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"));

        adapter.notifyDataSetChanged();
//        setRefresh(false);
        dismissProgressDialog();
        if (pageLike > 2) {
            adapter.loadMoreEnd(false);
        } else
            adapter.loadMoreComplete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_left, R.id.ll_search,R.id.ll_nearby})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                if (getActivity() != null) {
                    getActivity().finish();
                }
                break;
            case R.id.ll_nearby:
                jumpToPage(SellerListActivity.class);
                break;
            case R.id.ll_search:
                jumpToPage(SearchActivity.class);
                break;
        }
    }
}
