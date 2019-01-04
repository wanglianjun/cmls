package com.dy.cmls.mall.selmall.fragment;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.mall.selmall.activity.EvaActivity;
import com.dy.cmls.mall.selmall.adapter.UnEavAdapter;
import com.dy.cmls.mall.selmall.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/26.
 */

public class UnEvaListFragment extends BaseListFragment {

    List<GoodsBean> list;
    public static UnEvaListFragment getInstance(boolean isMul){
        UnEvaListFragment fragment=new UnEvaListFragment();
        Bundle bundle=new Bundle();
        bundle.putBoolean("isMul",isMul);
        fragment.setArguments(bundle);
        return fragment;
    }

    private boolean isMul;
    @Override
    protected void initAdapter() {
        list = new ArrayList<>();
        isMul=getArguments().getBoolean("isMul",false);
        adapter=new UnEavAdapter(R.layout.item_sel_uneva,list,getContext());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_to:
                        Bundle bundle=new Bundle();
                        bundle.putBoolean("isMul",isMul);
                        jumpToPage(EvaActivity.class,bundle);
                        break;
                }
            }
        });

        requestList(true,true,true);
    }

    private int page = 0;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            page = 0;
            list.clear();
        }
        page++;
        showProgressDialog();
        for (int i = 0; i < 5; i++) {
            list.add(new GoodsBean("农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装", "600.00"
                    , "12", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"));
            list.add(new GoodsBean("瓷罐金骏眉茶叶礼盒装武夷山金俊眉正宗红茶" , "199.99",
                    "3321", "http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg"));

        }
        adapter.notifyDataSetChanged();
        setRefresh(false);
        dismissProgressDialog();
        if (page > 2) {
            adapter.loadMoreEnd(false);
        } else
            adapter.loadMoreComplete();
    }

}
