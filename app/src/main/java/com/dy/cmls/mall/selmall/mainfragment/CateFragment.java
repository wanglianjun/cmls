package com.dy.cmls.mall.selmall.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseFragment;
import com.dy.cmls.mall.selmall.adapter.CateAdapter;
import com.dy.cmls.mall.selmall.adapter.CateTypeAdapter;
import com.dy.cmls.mall.selmall.bean.CateBean;
import com.dy.cmls.mall.selmall.bean.LikeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lcjing on 2018/12/24.
 */

public class CateFragment extends BaseFragment {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.rv_cate)
    RecyclerView rvCate;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.rv_type)
    RecyclerView rvType;
    Unbinder unbinder;
    @BindView(R.id.viewStatusBar)
    View viewStatusBar;

    public static CateFragment getInstance(boolean isMul) {
        CateFragment cateFragment = new CateFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isMul", isMul);
        cateFragment.setArguments(bundle);
        return cateFragment;
    }

    private boolean isMul;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sel_cate, null);
        unbinder = ButterKnife.bind(this, view);
        isMul = getArguments().getBoolean("isMul", false);
        if (isMul) {
            viewStatusBar.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    protected void initView(View view) {
        tvTitleTitle.setText("分类");
        likeBeans = new ArrayList<>();
        typeAdapter = new CateTypeAdapter(R.layout.item_sel_cate_type, likeBeans, getContext());
        rvType.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvType.setAdapter(typeAdapter);
        initCate();
    }

    private void initCate() {
        List<CateBean> list = new ArrayList<>();
        list.add(new CateBean("手机", "手机通讯"));
        list.add(new CateBean("电脑平板", "电脑平板"));
        list.add(new CateBean("智能数码", "智能数码"));
        list.add(new CateBean("家用电器", "家用电器"));
        list.add(new CateBean("手机", "手机通讯"));
        list.add(new CateBean("电脑平板", "电脑平板"));
        list.add(new CateBean("智能数码", "智能数码"));
        list.add(new CateBean("家用电器", "家用电器"));
        list.add(new CateBean("手机", "手机通讯"));
        list.add(new CateBean("电脑平板", "电脑平板"));
        list.add(new CateBean("智能数码", "智能数码"));
        list.add(new CateBean("家用电器", "家用电器"));
        list.add(new CateBean("手机", "手机通讯"));
        list.add(new CateBean("电脑平板", "电脑平板"));
        list.add(new CateBean("智能数码", "智能数码"));
        list.add(new CateBean("家用电器", "家用电器"));
        list.add(new CateBean("手机", "手机通讯"));
        list.add(new CateBean("电脑平板", "电脑平板"));
        list.add(new CateBean("智能数码", "智能数码"));
        list.add(new CateBean("家用电器", "家用电器"));
        list.get(0).setSelect(true);
        CateAdapter adapter = new CateAdapter(R.layout.item_sel_cate, list, getContext());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelect(i == position);
                }
                initType(list.get(position).getType());
                adapter.notifyDataSetChanged();
            }
        });
        rvCate.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCate.setAdapter(adapter);
        initType(list.get(0).getType());
    }

    CateTypeAdapter typeAdapter;
    List<LikeBean> likeBeans;

    private void initType(String type) {
        tvType.setText(type);
        likeBeans.clear();
        for (int i = 0; i < 10; i++) {
            likeBeans.add(new LikeBean("农家" + type, "￥600.00"
                    , "12", "http://img.article.pchome.net/00/50/59/93/pic_lib/wm/02.jpg"));
            likeBeans.add(new LikeBean("瓷罐" + type, "￥199.99",
                    "3321", "http://img0.imgtn.bdimg.com/it/u=2918799425,1304454190&fm=214&gp=0.jpg"));

        }
        typeAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.ivLeft)
    public void onViewClicked() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
