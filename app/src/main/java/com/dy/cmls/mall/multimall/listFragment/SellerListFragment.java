package com.dy.cmls.mall.multimall.listFragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.mall.multimall.activity.SellerInfoActivity;
import com.dy.cmls.mall.multimall.adapter.SellerAdapter;
import com.dy.cmls.mall.multimall.bean.SellerBean;
import com.dy.cmls.mall.selmall.bean.GoodsBean;
import com.dy.cmls.view.dialog.RedPacketDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2019/1/2.
 */

public class SellerListFragment extends BaseListFragment {
    List<SellerBean> list;

    @Override
    protected void initAdapter() {
        list = new ArrayList<>();
        adapter = new SellerAdapter(R.layout.item_mul_seller, list, getContext());
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_go_shop:
                        jumpToPage(SellerInfoActivity.class);
                        break;
                    case R.id.iv_red_packet:
                        RedPacketDialog dialog = new RedPacketDialog();
                        dialog.show(getChildFragmentManager(), "dds");
                        break;
                }
            }
        });
        requestList(true, true, true);
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
            List<GoodsBean> goodsBeans = new ArrayList<>();
            goodsBeans.add(new GoodsBean("农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装", "600"
                    , "12", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"));
            goodsBeans.add(new GoodsBean("瓷罐金骏眉茶叶礼盒装武夷山金俊眉正宗红茶", "199",
                    "3321", "http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg"));
            goodsBeans.add(new GoodsBean("瓷罐金骏眉茶叶礼盒装武夷山金俊眉正宗红茶", "199",
                    "3321", "http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg"));
            list.add(new SellerBean("韩都衣舍", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg", true, goodsBeans));
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
