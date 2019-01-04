package com.dy.cmls.mall.selmall.mainfragment;

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
import com.dy.cmls.mkabao.bean.KabaoBean;
import com.dy.cmls.mall.selmall.activity.SearchActivity;
import com.dy.cmls.mall.selmall.activity.SelGoodsInfoActivity;
import com.dy.cmls.mall.selmall.adapter.BannerViewHolder;
import com.dy.cmls.mall.selmall.adapter.SelHomeCateAdapter;
import com.dy.cmls.mall.selmall.adapter.SelHomeLikeAdapter;
import com.dy.cmls.mall.selmall.adapter.SelHomeNewAdapter;
import com.dy.cmls.mall.selmall.bean.LikeBean;
import com.dy.cmls.view.CustomLoadMoreView;
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
 * Created by lcjing on 2018/12/24.
 */

public class SelHomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    MZBannerView banner;
    @BindView(R.id.rv_cate)
    RecyclerView rvCate;
    @BindView(R.id.rv_new)
    RecyclerView rvNew;
    @BindView(R.id.rv_like)
    RecyclerView rvLike;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sel_home, null);
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
                return new BannerViewHolder();
            }
        });
        initCate();
        initNew();
        initLike();
//        SelHomeLikeFragment selHomeLikeFragment=new SelHomeLikeFragment();
//        getChildFragmentManager().beginTransaction().replace(R.id.fl_like,selHomeLikeFragment).commit();
    }

    private void initCate() {
        List<KabaoBean> kabaoBeans = new ArrayList<>();
        kabaoBeans.add(new KabaoBean("http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg", "美食专区"));
        kabaoBeans.add(new KabaoBean("http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg", "服装配饰"));
        kabaoBeans.add(new KabaoBean("http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg", "家居生活"));
        kabaoBeans.add(new KabaoBean("http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg", "电子科技"));
        kabaoBeans.add(new KabaoBean("http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg", "家居生活"));
        kabaoBeans.add(new KabaoBean("http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg", "电子科技"));
        SelHomeCateAdapter cateAdapter = new SelHomeCateAdapter(R.layout.item_sel_home_cate, kabaoBeans, getContext());
        rvCate.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvCate.setAdapter(cateAdapter);
    }

    private void initNew() {
        List<KabaoBean> kabaoBeans = new ArrayList<>();
        kabaoBeans.add(new KabaoBean("http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg", "美食专区"));
        kabaoBeans.add(new KabaoBean("http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg", "服装配饰"));
        kabaoBeans.add(new KabaoBean("http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg", "家居生活"));
        kabaoBeans.add(new KabaoBean("http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg", "电子科技"));
        kabaoBeans.add(new KabaoBean("http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg", "家居生活"));
        kabaoBeans.add(new KabaoBean("http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg", "电子科技"));
        SelHomeNewAdapter newAdapter = new SelHomeNewAdapter(R.layout.item_sel_home_new, kabaoBeans, getContext());
        newAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                jumpToPage(SelGoodsInfoActivity.class);
            }
        });
        rvNew.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvNew.setAdapter(newAdapter);
    }

    private int pageLike=0;
    private  List<LikeBean> list;
  private   SelHomeLikeAdapter adapter;
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

    @OnClick({R.id.iv_left, R.id.ll_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                if (getActivity() != null) {
                    getActivity().finish();
                }
                break;
            case R.id.ll_search:
                jumpToPage(SearchActivity.class);
                break;
        }
    }
}
