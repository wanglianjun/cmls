package com.dy.cmls.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dy.cmls.R;
import com.dy.cmls.view.dialog.adapter.WuLiuAdapter;
import com.dy.cmls.view.interfaces.TJCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcjing on 2018/12/3.
 */

public class WuLiuDialog {
    private Dialog dialog;
    private TJCallBack tjCallBack;
    private Context context;
    private String id = "";

    public WuLiuDialog(Context context, String id, TJCallBack tjCallBack) {
        this.context = context;
        this.id = id;
        this.tjCallBack = tjCallBack;
    }

    private List<String> list;
    private TextView tvLoading;
    private WuLiuAdapter adapter;
    public void show() {
        if (dialog != null) {
            dialog.show();
            getData();
            return;
        }
        list=new ArrayList<>();
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_wuliu, null);
        ImageView ivX;
        RecyclerView recyclerView;
        tvLoading = dialogView.findViewById(R.id.tv_loading);
        recyclerView=dialogView.findViewById(R.id.recyclerView);
        adapter=new WuLiuAdapter(R.layout.dialog_item_wuliu,list,context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        ivX =  dialogView.findViewById(R.id.iv_x);
        getData();
        ivX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(context, R.style.dialog_center);
        dialog.setCancelable(true);
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        dialog.show();
    }

    private void getData(){
        list.clear();
        if (tvLoading!=null) {
            tvLoading.setVisibility(View.VISIBLE);
        }
        list.add("中通快递 3221239293928");
        list.add("04:39:28 快件到达 【湖州吴兴集散中心】 ");
        list.add("00:34:37 快件已发车 18:33:04 快件在【宁波余姚集散中心】已装车,准备发往 【湖州吴兴集散中心】");
        list.add("18:08:19 快件到达 【宁波余姚集散中心】 16:38:25 快件已发车");
        list.add("14:53:35 快件在【宁波市慈溪市滨海三路营业点营业点】已装车,准备发往下一站");
        list.add("14:53:35 快件在【宁波市慈溪市滨海三路营业点营业点】已装车,准备发往下一站");
        list.add("14:53:35 快件在【宁波市慈溪市滨海三路营业点营业点】已装车,准备发往下一站");
        list.add("14:53:25 顺丰速运 已收取快件");
        list.add("14:53:25  您的快递已发货");
        adapter.notifyDataSetChanged();
        if (tvLoading!=null) {
            tvLoading.setVisibility(View.GONE);
        }
    }
}
