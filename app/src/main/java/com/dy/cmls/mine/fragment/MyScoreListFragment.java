package com.dy.cmls.mine.fragment;

import android.content.Context;
import android.content.Intent;

import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListFragment;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.mine.activity.MyScoreActivity;
import com.dy.cmls.mine.adapter.ScoreAdapter;
import com.dy.cmls.mine.bean.MemberBean;
import com.dy.cmls.mine.bean.ScoreBean;
import com.dy.cmls.mine.bean.UserPointBean;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.view.interfaces.TJCallBack;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by lcjing on 2018/12/20.
 */

public class MyScoreListFragment extends BaseListFragment {
    private List<ScoreBean> scores;
    private TJCallBack tjCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        tjCallBack = (MyScoreActivity) getActivity();
    }

    @Override
    protected void initAdapter() {
        scores = new ArrayList<>();
        adapter = new ScoreAdapter(R.layout.item_score, scores, getContext());
        requestList(true, true, true);

    }

    private int page;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            scores.clear();
            page = 0;
        }
        page++;
//        scores.add(new ScoreBean("签到", "2018-12-20 12:40", "10.00", "+"));
        PersonLoader.getInstance().getPoints("我的积分", SPUtils.getUserId()).subscribe(new Action1<UserPointBean>() {
            @Override
            public void call(UserPointBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    if (tjCallBack!=null) {
                        Intent intent=new Intent();
                        intent.putExtra("score",bean.getInfo().getMember_points());
                        tjCallBack.callBack(intent);
                    }
                    List<UserPointBean.PointBean> pointList=bean.getInfo().getList();
                    for (int i = 0; i < pointList.size(); i++) {
                        scores.add(new ScoreBean(pointList.get(i)));
                    }
                } else if (bean.getMessage() != null) {
                    showToastLong(bean.getMessage());
                }
                adapter.notifyDataSetChanged();
                setRefresh(false);
                adapter.loadMoreEnd(false);

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                adapter.notifyDataSetChanged();
                setRefresh(false);
                adapter.loadMoreEnd(false);
                dismissProgressDialog();
                showToastFailure();
                showLog("我的积分:报异常2:", throwable.getMessage());
            }
        });
//        adapter.notifyDataSetChanged();
//        setRefresh(false);
//        if (Integer.parseInt("2") <= page) {
//            adapter.loadMoreEnd(false);
//        } else
//            adapter.loadMoreComplete();
    }


}
