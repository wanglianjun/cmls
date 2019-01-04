package com.dy.cmls.mall.selmall.mainfragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.mall.selmall.adapter.CartAdapter;
import com.dy.cmls.mall.selmall.adapter.helper.SelectListener;
import com.dy.cmls.mall.selmall.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/24.
 */

public class CartListFragment extends BaseListFragment {
    List<GoodsBean> list;

    public void setSelectListener(SelectListener selectListener) {
        this.selectListener = selectListener;
    }

    private SelectListener selectListener;


    @Override
    protected void initAdapter() {
        list = new ArrayList<>();
        adapter = new CartAdapter(R.layout.item_sel_cart, list, getContext());
        ((CartAdapter) adapter).setSelectListener(new SelectListener() {
            @Override
            public void select(int position, boolean checked) {
//                list.get(position).setSelect(checked);
//                adapter.notifyDataSetChanged();
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_add:
                        int c1 = list.get(position).getmCount();
                        list.get(position).setmCount(++c1);
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.iv_minus:
                        int c2 = list.get(position).getmCount();
                        if (c2 > 1) {
                            list.get(position).setmCount(--c2);
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.cb_cart:
                        list.get(position).setSelect(!list.get(position).isSelect());
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.ll_item:
                        break;

                }
                if (selectListener != null) {
                    selectListener.select(0, true);
                }
            }
        });
        requestList(true, true, true);
    }

    public int getCount() {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelect()) {
                count += list.get(i).getmCount();
            }
        }
        return count;
    }

    public boolean isSelectAll() {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelect()) {
                count++;
            }
        }
        return count == list.size();
    }

    public void selectAll(boolean select) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelect(select);
        }
        adapter.notifyDataSetChanged();
        if (selectListener != null) {
            selectListener.select(1, true);
        }
    }

    public void collect() {
        String ids = getIds();
        collect(ids);
    }


    public void delete() {
        String ids = getIds();
        delete(ids);
    }

    public void submit(){
        String ids = getIds();
        submit(ids);
    }

    private String getIds() {
        StringBuilder sb = new StringBuilder();
        if (list == null || list.size() < 1) {
            return "";
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelect()) {
                sb.append(list.get(i).getId()).append(",");
            }
        }

        if (sb.length() > 1) {
            String id = sb.substring(0, sb.length() - 1);
            return id;
//            collect(id);
        }
        return "";
    }



    private void collect(String id) {

    }

    private void delete(String id) {

    }

    public void submit(String id){

    }



    int page = 0;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            page = 0;
            list.clear();
        }
        page++;
        showProgressDialog();
        showProgressDialog();
        for (int i = 0; i < 5; i++) {
            list.add(new GoodsBean("农家新疆特产薄皮纸皮核桃薄壳批发脆皮5斤装", "600.00"
                    , "1", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"));
            list.add(new GoodsBean("瓷罐金骏眉茶叶礼盒装武夷山金俊眉正宗红茶", "199.99",
                    "2", "http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg"));

        }
        adapter.notifyDataSetChanged();
        setRefresh(false);
        dismissProgressDialog();
        if (page > 2) {
            adapter.loadMoreEnd(false);
        } else
            adapter.loadMoreComplete();
        dismissProgressDialog();
    }
}
