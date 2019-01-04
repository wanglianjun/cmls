package com.dy.cmls.mall.selmall.fragment;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.mall.selmall.adapter.SearchAdapter;
import com.dy.cmls.mall.selmall.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/24.
 */

public class GoodsListFragment extends BaseListFragment {
    List<GoodsBean> list;

    @Override
    protected void initAdapter() {
        list = new ArrayList<>();
        adapter = new SearchAdapter(R.layout.item_sel_goods, list, getContext());
        requestList(true, true, true);
    }

    private String key = "";
    private String shortType="";

    public void setKey(String key) {
        this.key = key;
        requestList(false, true, true);
    }

    public void setShort(String shortType){
        this.shortType = shortType;
        requestList(false, true, true);
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
            list.add(new GoodsBean(shortType+"农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装" + key, "600.00"
                    , "12", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"));
            list.add(new GoodsBean("瓷罐金骏眉茶叶礼盒装武夷山金俊眉正宗红茶" + key, "199.99",
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
