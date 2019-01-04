package com.dy.cmls.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.multimall.activity.SelectAreaActivity;
import com.dy.cmls.view.PopupWindow7;
import com.dy.cmls.view.interfaces.TJCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by lcjing on 2019/1/2.
 */

public class SelectAreaDialog implements View.OnClickListener {

    private PopupWindow7 popupWindow7;
    private TJCallBack tjCallBack;
    private Activity context;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    public SelectAreaDialog(TJCallBack tjCallBack, Activity context) {
        this.tjCallBack = tjCallBack;
        this.context = context;
    }

    public void show(View view) {
        if (popupWindow7 == null) {
            initView();
        }
        popupWindow7.showAsDropDown(view);
    }

    List<AreaBean> list;

    private void initView() {
        popupWindow7 =
                new PopupWindow7(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_area, null);
        tvLocation = dialogView.findViewById(R.id.tv_location);
        recyclerView = dialogView.findViewById(R.id.recyclerView);
        dialogView.findViewById(R.id.ll_swich).setOnClickListener(this);
        dialogView.findViewById(R.id.v_tran).setOnClickListener(this);
        list = new ArrayList<>();
        list.add(new AreaBean("历城区"));
        list.add(new AreaBean("历下区"));
        list.add(new AreaBean("市中区"));
        list.add(new AreaBean("槐荫区"));
        list.add(new AreaBean("长清区"));
        list.add(new AreaBean("天桥区"));
        list.add(new AreaBean("章丘区"));
        list.add(new AreaBean("市中区"));
        list.add(new AreaBean("槐荫区"));
        list.add(new AreaBean("长清区"));
        list.add(new AreaBean("章丘区"));
        list.add(new AreaBean("市中区"));
        list.add(new AreaBean("槐荫区"));
        list.add(new AreaBean("长清区"));
        list.get(3).setPosition(true);
        AreaAdapter areaAdapter = new AreaAdapter(R.layout.dialog_item_area, list);
        areaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setSelect(position == i);
                }
                areaAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(areaAdapter);
        popupWindow7.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow7.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (tjCallBack != null) {
                    Intent intent = new Intent();
                    intent.putExtra("callBack", "action");
                    intent.putExtra("action", "dismiss");
                    tjCallBack.callBack(intent);
                }
            }
        });
        popupWindow7.setContentView(dialogView);
    }

    public void dismiss(){
        if (popupWindow7 != null) {
            popupWindow7.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_swich:
                Intent intent=new Intent(context, SelectAreaActivity.class);
                context.startActivityForResult(intent,1);
                break;
            case R.id.v_tran:
             dismiss();
                break;
        }
    }


    public class AreaBean {
        private String id;
        private String name;
        private boolean isPosition;
        private boolean select;

        public AreaBean(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPosition() {
            return isPosition;
        }

        public void setPosition(boolean position) {
            isPosition = position;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
    }


    public class AreaAdapter extends BaseQuickAdapter<AreaBean, BaseViewHolder> {

        public AreaAdapter(int layoutResId, List<AreaBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final AreaBean item) {
            helper.setText(R.id.tv_name, item.getName());
            helper.setTextColor(R.id.tv_name, item.isSelect() ? Color.parseColor("#FF3570FE") : Color.parseColor("#ff666666"));
            helper.setVisible(R.id.tv_position, item.isPosition());
            helper.setVisible(R.id.iv_position, item.isPosition());
        }
    }
}
