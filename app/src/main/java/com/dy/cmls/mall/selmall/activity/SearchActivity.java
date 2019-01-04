package com.dy.cmls.mall.selmall.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mall.selmall.adapter.SearchIndexAdapter;
import com.dy.cmls.mall.selmall.adapter.helper.GridAutofitLayoutManager;
import com.dy.cmls.mall.selmall.bean.SearchIndexBean;
import com.dy.cmls.mall.selmall.fragment.SelSearchGoodsFragment;
import com.dy.cmls.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.v_left)
    View vLeft;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rv_hot)
    RecyclerView rvHot;
    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @BindView(R.id.ll_index)
    LinearLayout llIndex;
    @BindView(R.id.fl_list)
    FrameLayout flList;
    @BindView(R.id.et_key)
    EditText etKey;


    private SelSearchGoodsFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        initHot();
        initHistory();
        fragment = new SelSearchGoodsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_list,fragment).commit();
        flList.setVisibility(View.GONE);
        etKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String key = etKey.getText().toString();
                if (StringUtils.isEmpty(key)) {
                    ivBack.setVisibility(View.GONE);
                    llIndex.setVisibility(View.VISIBLE);
                    flList.setVisibility(View.GONE);
                    tvCancel.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initHot() {
        List<SearchIndexBean> hots = new ArrayList<>();
        hots.add(new SearchIndexBean("1", "mate20pro"));
        hots.add(new SearchIndexBean("2", "荣耀"));
        hots.add(new SearchIndexBean("3", "mac"));
        hots.add(new SearchIndexBean("4", "小米"));
        hots.add(new SearchIndexBean("5", "笔记本电脑"));
        hots.add(new SearchIndexBean("6", "联想"));
        hots.add(new SearchIndexBean("7", "sk2"));
        hots.add(new SearchIndexBean("8", "圣诞节礼物"));
        hots.add(new SearchIndexBean("9", "乐高"));
        SearchIndexAdapter hotAdapter = new SearchIndexAdapter(R.layout.item_search_index, hots, this);
        hotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                search(hots.get(position).getName());
                etKey.setText(hots.get(position).getName());
                etKey.setSelection(hots.get(position).getName().length());
            }
        });
        rvHot.setLayoutManager(new GridLayoutManager(this, 5));
        rvHot.setAdapter(hotAdapter);
    }

    private void initHistory() {
        List<SearchIndexBean> his = new ArrayList<>();
        his.add(new SearchIndexBean("1", "mate20pro"));
        his.add(new SearchIndexBean("2", "荣耀"));
        his.add(new SearchIndexBean("3", "mac"));
        his.add(new SearchIndexBean("4", "小米"));
        his.add(new SearchIndexBean("5", "笔记本电脑"));
        his.add(new SearchIndexBean("6", "联想"));
        his.add(new SearchIndexBean("7", "sk2"));
        his.add(new SearchIndexBean("8", "圣诞节礼物"));
        his.add(new SearchIndexBean("9", "乐高"));
        SearchIndexAdapter hisAdapter = new SearchIndexAdapter(R.layout.item_search_index, his, this);
//        rvHistory.setLayoutManager(new GridLayoutManager(this,5));
        rvHistory.setLayoutManager(new GridAutofitLayoutManager(this, CommonUtil.dip2px(this, 65), GridLayoutManager.VERTICAL, false));
//        rvHistory.addItemDecoration(new GridSpacesItemDecoration(CommonUtil.dip2px(this,0),true));
        rvHistory.setAdapter(hisAdapter);
    }

    private void search(String key) {
        ivBack.setVisibility(View.VISIBLE);
        llIndex.setVisibility(View.GONE);
        flList.setVisibility(View.VISIBLE);
        tvCancel.setVisibility(View.GONE);
        fragment.setKey(key);
    }

    @OnClick({R.id.iv_back, R.id.iv_search, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                search(etKey.getText().toString());
                break;
            case R.id.tv_cancel:
                finish();
                break;
        }
    }
}
