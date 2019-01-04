package com.dy.cmls.view.pop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.common.PhotoViewActivity;
import com.dy.cmls.view.PopupWindow7;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.dy.cmls.view.pop.adapter.GoodsSelectAdapter;
import com.dy.cmls.view.pop.bean.AttrBean;
import com.dy.cmls.view.pop.bean.SelectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/26.
 */

public class GoodsSelectView implements View.OnClickListener{


    private TextView tvCount;
    private Context context;
    private PopupWindow7 popupWindow7;
    private TJCallBack callBack;


    public GoodsSelectView() {
    }

    public GoodsSelectView(Context context, TJCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TJCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(TJCallBack callBack) {
        this.callBack = callBack;
    }

    public void show(View view) {
        if (popupWindow7 == null) {
            init();
        }
        popupWindow7.showAsDropDown(view);
    }

    public void dismiss() {
        if (popupWindow7 != null) {
            popupWindow7.dismiss();
        }
    }

    private List<SelectBean> typeBeans = new ArrayList<>();




    public void setTypeBeans(List<SelectBean> typeBeans) {

        if (typeBeans.size() < 1) {
            SelectBean attrBean = new SelectBean();
            attrBean.setSelectName("颜色");
            attrBean.setSelectVal(new String[]{"马卡龙12枚礼盒装", "马卡龙12枚礼盒装", "马卡龙12枚礼盒装", "马卡龙12枚礼盒装", "马卡龙12枚礼盒装"});
            typeBeans.add(attrBean);
            attrBean = new SelectBean();
            attrBean.setSelectName("大小");
            attrBean.setSelectVal(new String[]{"大", "中"});
            typeBeans.add(attrBean);
            typeBeans.add(attrBean);
            typeBeans.add(attrBean);
            typeBeans.add(attrBean);
            typeBeans.add(attrBean);
            typeBeans.add(attrBean);
            typeBeans.add(attrBean);
            typeBeans.add(attrBean);
        }

        this.typeBeans = typeBeans;

    }

    private String imgUrl = "";
    private String price = "";
    private String totalCount = "";

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        if (StringUtils.isEmpty(totalCount)||"null".equals(totalCount)) {
            totalCount="10";
        }
        this.totalCount = "库存：仅剩" + totalCount + "件";
        try {
            maxCount = Integer.parseInt(totalCount);
        }catch (Exception e){}

    }


    private void init() {
        popupWindow7 =
                new PopupWindow7(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        View view = View.inflate(context, R.layout.pop_goods_select, null);
        ImageView ivContent;
        TextView tvPrice;
        TextView tvKucun;
        RecyclerView recyclerView;
        ivContent = view.findViewById(R.id.iv_content);
        tvPrice = view.findViewById(R.id.tv_price);
        tvKucun = view.findViewById(R.id.tv_kucun);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvCount = view.findViewById(R.id.tv_count);
        if (!StringUtils.isEmpty(imgUrl)) {
            Glide.with(context).load(imgUrl).into(ivContent);
        }
        if(!StringUtils.isEmpty(totalCount)) {
            tvKucun.setText(totalCount);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        tvPrice.setText("￥"+price);

        view.findViewById(R.id.v_tran).setOnClickListener(this);
        view.findViewById(R.id.v_tran0).setOnClickListener(this);
        view.findViewById(R.id.iv_close).setOnClickListener(this);
        view.findViewById(R.id.iv_jian).setOnClickListener(this);
        view.findViewById(R.id.iv_jia).setOnClickListener(this);
        view.findViewById(R.id.tv_car).setOnClickListener(this);
        view.findViewById(R.id.tv_buy).setOnClickListener(this);
        GoodsSelectAdapter typeAdapter = new GoodsSelectAdapter(R.layout.pop_item_goods_select, typeBeans, context);
        typeAdapter.setCall(new GoodsSelectAdapter.SelectCall() {
            @Override
            public void select(long position, int selectPosition) {
                typeBeans.get(Integer.parseInt(String.valueOf(position)))
                        .setSelectAttr(typeBeans.get(Integer.parseInt(String.valueOf(position))).getSelectVal()[selectPosition]);
            }
        });
        recyclerView.setAdapter(typeAdapter);
        if (typeBeans.size()<1) {
            recyclerView.setVisibility(View.GONE);
        }
        popupWindow7.setContentView(view);
    }

    private int count = 1;


    int maxCount = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.v_tran0:
            case R.id.v_tran://透明区域
                dismiss();
                break;
            case R.id.iv_close://关闭弹窗
                dismiss();
                break;
            case R.id.iv_jian://减号
                if (count > 1) {
                    tvCount.setText(--count + "");
                }
                break;
            case R.id.iv_jia://加号
                if (count < maxCount) {
                    tvCount.setText(++count + "");
                }
                break;
            case R.id.tv_car://购物车
                Intent intent = new Intent();
                intent.putExtra("action", "cart");
                intent.putExtra("num", count + "");
                intent.putExtra("attr", getAttr());
                callBack.callBack(intent);
                break;
            case R.id.tv_buy://立即购买
                Intent intent1 = new Intent();
                intent1.putExtra("action", "buy");
                intent1.putExtra("num", count + "");
                intent1.putExtra("attr", getAttr());
                callBack.callBack(intent1);
                break;

            case R.id.iv_content:
                Intent img = new Intent();
                img.putExtra("url", imgUrl);
                img.setClass(context, PhotoViewActivity.class);
                context.startActivity(img);

                break;
        }
    }

    private String getAttr() {
        if (typeBeans.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (SelectBean bean : typeBeans) {
            if (bean.getSelectVal() != null) {
                builder.append(bean.getSelectName()).append("-").append(bean.getSelectAttr());
                builder.append(",");
            } else {
                return "-1";
            }
        }
        return builder.substring(0, builder.length() - 1);
    }

    public static class TypeBean {
        String name;
        boolean isSelect;
        boolean isShow;

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }

        public TypeBean() {
        }

        public TypeBean(String name, boolean isSelect) {
            this.name = name;
            this.isSelect = isSelect;
            this.isShow = true;
        }

        public TypeBean(boolean isShow, String name) {
            this.name = name;
            this.isShow = isShow;
        }

        public TypeBean(String name) {
            this.name = name;
            this.isSelect = false;
            this.isShow = true;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    public static class TypeAdapter extends BaseQuickAdapter<TypeBean, BaseViewHolder> {
        private Context context;

        public TypeAdapter(int layoutResId, List<TypeBean> data, Context context) {
            super(layoutResId, data);
            this.context = context;
        }

        @Override
        protected void convert(BaseViewHolder helper, TypeBean item) {
            TextView tvName = helper.getView(R.id.tv_name);
            helper.setText(R.id.tv_name, item.getName());
            tvName.setTextColor(context.getResources().getColor(R.color.text_black));
            if (!item.isShow()) {
                tvName.setBackground(context.getResources().getDrawable(R.drawable.rect_goods_type2));
//                tvName.setTextColor(context.getResources().getColor(R.color.white));
            } else if (item.isSelect()) {
                tvName.setBackground(context.getResources().getDrawable(R.drawable.rect_goods_type1));
                tvName.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                tvName.setBackground(context.getResources().getDrawable(R.drawable.rect_goods_type));
            }
        }
    }

}
