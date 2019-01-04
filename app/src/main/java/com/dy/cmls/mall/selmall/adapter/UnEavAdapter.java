package com.dy.cmls.mall.selmall.adapter;

import android.content.Context;
import android.os.Build;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dy.cmls.R;
import com.dy.cmls.mall.selmall.bean.GoodsBean;

import java.util.List;

/**
 * Created by lcjing on 2018/12/26.
 */

public class UnEavAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {
    private Context context;

    public UnEavAdapter(int layoutResId, List<GoodsBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean item) {
        helper.setText(R.id.tv_name, item.getName());
//        setMaxEcplise(helper.getView(R.id.tv_name),3, item.getName());
        Glide.with(context).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.iv_content));
        helper.addOnClickListener(R.id.tv_to);
    }


//      /**
//          * 参数：maxLines 要限制的最大行数
//          * 参数：content  指TextView中要显示的内容
//          */

    public void setMaxEcplise(final TextView textView, final int maxLines, final String content) {
        ViewTreeObserver observer = textView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {


            @Override
            public void onGlobalLayout() {
                textView.setText(content);
                if (textView.getLineCount() > maxLines) {
                    int lineEndIndex = textView.getLayout().getLineEnd(maxLines - 1);
//下面这句代码中：我在项目中用数字3发现效果不好，改成1了
                    String text = content.subSequence(0, lineEndIndex - 3) + "...";
                    textView.setText(text);
                } else {
                    removeGlobalOnLayoutListener(textView.getViewTreeObserver(), this);
                }
            }
        });
    }


    private void removeGlobalOnLayoutListener(ViewTreeObserver obs, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (obs == null) return;
        if (Build.VERSION.SDK_INT < 16) {
            obs.removeGlobalOnLayoutListener(listener);
        } else {
            obs.removeOnGlobalLayoutListener(listener);
        }
    }


}
