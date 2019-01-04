package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.selmall.bean.SearchIndexBean;
import java.util.List;

/**
 * Created by lcjing on 2018/12/24.
 */

public class SearchIndexAdapter extends BaseQuickAdapter<SearchIndexBean, BaseViewHolder> {
    private Context context;

    public SearchIndexAdapter(int layoutResId, List<SearchIndexBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SearchIndexBean item) {
        helper.setText(R.id.tv_name,item.getName());
    }
}
