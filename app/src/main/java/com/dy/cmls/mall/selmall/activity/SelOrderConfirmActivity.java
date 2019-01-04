package com.dy.cmls.mall.selmall.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mall.selmall.adapter.CommitGoodsAdapter;
import com.dy.cmls.mall.selmall.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelOrderConfirmActivity extends BaseActivity implements View.OnClickListener {


    TextView tvName, tvPhone, tvAddress;
    View llHeader;


    TextView tvCount, tvPeisong, tvTotalCount, tvScore;
    EditText etMsg;
    CheckBox cbScore;
    TextView tvAllMoney;
    View llFooter;


    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;

    private CommitGoodsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_order_confirm);
        ButterKnife.bind(this);
        initView();
    }

    List<GoodsBean> goodsList;

    @Override
    protected void initView() {
        tvTitleTitle.setText("订单确认");
        goodsList = new ArrayList<>();
        goodsList.add(new GoodsBean("艾菲勒法式马卡龙甜点24枚西式糕点心小蛋糕甜品零食品",
                "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg",
                "1", "500.99", "口味:马卡龙24枚礼盒装"));
        goodsList.add(new GoodsBean("艾菲勒法式马卡龙甜点12枚西式糕点心小蛋糕甜品零食品",
                "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg",
                "2", "500.99", "口味:马卡龙12枚礼盒装"));
        adapter = new CommitGoodsAdapter(R.layout.item_sel_commit_goods, goodsList, this);


        //Header
        llHeader = LayoutInflater.from(this).inflate(R.layout.header_sel_order_confirm, null);
        tvName = llHeader.findViewById(R.id.tv_name);
        tvPhone = llHeader.findViewById(R.id.tv_phone);
        tvAddress = llHeader.findViewById(R.id.tv_address);
        llHeader.findViewById(R.id.ll_header).setOnClickListener(this);
        adapter.addHeaderView(llHeader);
        //llFooter
        llFooter = LayoutInflater.from(this).inflate(R.layout.footer_sel_order_confirm, null);
        tvCount = llHeader.findViewById(R.id.tv_count);
        tvPeisong = llHeader.findViewById(R.id.tv_peisong);
        etMsg = llHeader.findViewById(R.id.et_msg);
        tvTotalCount = llHeader.findViewById(R.id.tv_total_count);
        tvScore = llHeader.findViewById(R.id.tv_score);
        cbScore = llHeader.findViewById(R.id.cb_score);
        tvAllMoney = llHeader.findViewById(R.id.tv_all_money);
        llFooter.findViewById(R.id.iv_jian).setOnClickListener(this);
        llFooter.findViewById(R.id.iv_jia).setOnClickListener(this);
        adapter.addFooterView(llFooter);

        rvGoods.setLayoutManager(new LinearLayoutManager(this));
        rvGoods.setAdapter(adapter);
    }


    @OnClick({R.id.ivLeft, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_commit:
                jumpToPage(PayActivity.class);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_header:
                break;
            case R.id.iv_jian:
                break;
            case R.id.iv_jia:

                break;
        }
    }
}
