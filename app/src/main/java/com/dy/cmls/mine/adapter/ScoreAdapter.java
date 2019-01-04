package com.dy.cmls.mine.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mine.bean.MemberBean;
import com.dy.cmls.mine.bean.ScoreBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/20.
 */

public class ScoreAdapter   extends BaseQuickAdapter<ScoreBean, BaseViewHolder> {
    private Context context;

    public ScoreAdapter(int layoutResId, List<ScoreBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ScoreBean item) {
        helper.setText(R.id.tv_time, item.getTime());
        helper.setText(R.id.tv_score,item.getScoreType()+item.getScore());
        helper.setText(R.id.tv_type, item.getType());
    }
}
