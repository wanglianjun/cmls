package com.dy.cmls.mine.activity;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseListActivity;
import com.dy.cmls.loader.CrashLoader;
import com.dy.cmls.mine.adapter.ScoreAdapter;
import com.dy.cmls.mine.bean.CashLogBean;
import com.dy.cmls.mine.bean.CrashBean;
import com.dy.cmls.mine.bean.MoneyLogBean;
import com.dy.cmls.mine.bean.ScoreBean;
import com.dy.cmls.utils.GlideUtils;
import com.dy.cmls.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by lcjing on 2018/12/29.
 */

public class WalletListActivity extends BaseListActivity {

    private String title;

    private List<ScoreBean> list;

    @Override
    protected void initAdapter() {
        title = getIntent().getStringExtra("title");
        tvTitleTitle.setText(title);
        list = new ArrayList<>();
        adapter = new ScoreAdapter(R.layout.item_score, list, this);
        requestList(true, true, true);
    }

    private int page;

    @Override
    protected void requestList(boolean isFirst, boolean isRefresh, boolean isLoading) {
        if (isRefresh) {
            list.clear();
            page = 0;
        }
        page++;
        if (title.equals("明细")) {//请求明细要先获取 筛选条件
            if (typeList!=null) {
                getLogInfo(typeList);
            }else {
                getType();
            }
        } else {//提现记录
            getCrashLog();
        }


    }


    private  List<MoneyLogBean.CashBean> typeList;

    private void getType(){
        showProgressDialog();
        CrashLoader.getInstance().moneyIndex("交易明细--筛选条件").subscribe(new Action1<MoneyLogBean>() {
            @Override
            public void call(MoneyLogBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    typeList = bean.getInfo().getList();
                    getLogInfo(typeList);
                } else {
                    ToastUtils.showShort(bean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                adapter.notifyDataSetChanged();
                setRefresh(false);
                adapter.loadMoreEnd(false);
                dismissProgressDialog();
                showToastFailure();
                showLog("交易明细--筛选条件:报异常2:", throwable.toString());
            }
        });
    }
    private void getLogInfo(List<MoneyLogBean.CashBean> typeList) {
//            list.add(new ScoreBean("还款分润", "2018-12-20 12:40", "10.00", "+"));
        showProgressDialog();
        CrashLoader.getInstance().moneyLog("余额明细", SPUtils.getUserId(),page+"","").subscribe(new Action1<MoneyLogBean>() {
            @Override
            public void call(MoneyLogBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    List<MoneyLogBean.CashBean> clist = bean.getInfo().getList();
                    for (int i = 0; i < clist.size(); i++) {
                        list.add(new ScoreBean(clist.get(i),typeList));
                    }
                    adapter.notifyDataSetChanged();
                    setRefresh(false);
                    if (bean.getInfo().getAll_page().equals(bean.getInfo().getNow_page())) {
                        adapter.loadMoreEnd(false);
                    } else
                        adapter.loadMoreComplete();
                } else {
                    ToastUtils.showShort(bean.getMessage());
                    adapter.notifyDataSetChanged();
                    setRefresh(false);
                    adapter.loadMoreEnd(false);
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                adapter.notifyDataSetChanged();
                setRefresh(false);
                adapter.loadMoreEnd(false);
                dismissProgressDialog();
                showToastFailure();
                showLog("余额明细:报异常2:", throwable.toString());
            }
        });
    }


    private void getCrashLog() {
        showProgressDialog();
        CrashLoader.getInstance().cashLog("提现记录", SPUtils.getUserId(),page+"","").subscribe(new Action1<CashLogBean>() {
            @Override
            public void call(CashLogBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    List<CashLogBean.CashBean> clist = bean.getInfo().getList();
                    for (int i = 0; i < clist.size(); i++) {
                        list.add(new ScoreBean(clist.get(i),"提现"));
                    }
                    adapter.notifyDataSetChanged();
                    setRefresh(false);
                    if (bean.getInfo().getAll_page().equals(bean.getInfo().getNow_page())) {
                        adapter.loadMoreEnd(false);
                    } else
                        adapter.loadMoreComplete();
                } else {
                    ToastUtils.showShort(bean.getMessage());
                    adapter.notifyDataSetChanged();
                    setRefresh(false);
                    adapter.loadMoreEnd(false);
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                adapter.notifyDataSetChanged();
                setRefresh(false);
                adapter.loadMoreEnd(false);
                dismissProgressDialog();
                showToastFailure();
                showLog("提现记录:报异常2:", throwable.toString());
            }
        });
//        for (int i = 0; i < 3; i++) {
//            list.add(new ScoreBean("提现100.00", "2018-12-20 12:40", "成功", ""));
//        }
//        for (int i = 0; i < 3; i++) {
//            list.add(new ScoreBean("提现100.00", "2018-12-20 12:40", "审核中", ""));
//        }
    }

}
