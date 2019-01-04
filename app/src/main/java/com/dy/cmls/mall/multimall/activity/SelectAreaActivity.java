package com.dy.cmls.mall.multimall.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mall.multimall.adapter.AreaAdapter;
import com.dy.cmls.mall.multimall.bean.AreaBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectAreaActivity extends BaseActivity {

    @BindView(R.id.v_province)
    View vProvince;
    @BindView(R.id.v_city)
    View vCity;
    @BindView(R.id.v_area)
    View vArea;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fl_bottom)
    FrameLayout flBottom;


    private List<AreaBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);
        ButterKnife.bind(this);
        initView();
    }

    private AreaAdapter adapter;
    private int level = 0;

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new AreaAdapter(R.layout.dialog_item_area, list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(level<2){
                    level++;
                    getList();
                }

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getList();
    }

    private void getList() {
        String data = "";
        switch (level) {
            case 0:
                data = "山东省";
                vProvince.setVisibility(View.VISIBLE);
                vCity.setVisibility(View.GONE);
                vArea.setVisibility(View.GONE);
                flBottom.setVisibility(View.GONE);
                break;
            case 1:
                data = "济南市";
                vProvince.setVisibility(View.GONE);
                vCity.setVisibility(View.VISIBLE);
                vArea.setVisibility(View.GONE);
                flBottom.setVisibility(View.GONE);
                break;
            case 2:
                data = "历城区";
                vProvince.setVisibility(View.GONE);
                vCity.setVisibility(View.GONE);
                vArea.setVisibility(View.VISIBLE);
                flBottom.setVisibility(View.VISIBLE);
                break;
        }
        list.clear();
        for (int i = 0; i < 20; i++) {
            list.add(new AreaBean(data));
        }
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ivLeft, R.id.ll_location, R.id.ll_province, R.id.ll_city, R.id.ll_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ll_location:
                break;
            case R.id.ll_province:
                level = 0;
                getList();
                break;
            case R.id.ll_city:
                if (level < 1) {
                    return;
                }
                level = 1;
                getList();
                break;
            case R.id.ll_area:
                if (level < 2) {
                    return;
                }
                level = 2;
                getList();
                break;
        }
    }
}
