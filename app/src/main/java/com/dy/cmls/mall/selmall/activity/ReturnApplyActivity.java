package com.dy.cmls.mall.selmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.mall.selmall.adapter.EvaImgAdapter;
import com.dy.cmls.utils.PhoneCameraUtil;
import com.dy.cmls.view.interfaces.AttrBack;
import com.dy.cmls.view.pop.AttrSelectView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

//申请 退货退款
public class ReturnApplyActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.iv_content)
    ImageView ivContent;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_goods_status)
    TextView tvGoodsStatus;
    @BindView(R.id.tv_reason_name)
    TextView tvReasonName;
    @BindView(R.id.tv_reason)
    TextView tvReason;
    @BindView(R.id.rc_return_money)
    TextView rcReturnMoney;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.rv_cer)
    RecyclerView rvCer;
    @BindView(R.id.ll_goods_status)
    LinearLayout llGoodsStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrun_apply);
        ButterKnife.bind(this);
        initView();
    }

    private String type = "th";
    List<String> imgPathList = new ArrayList<>();
    private int imgTag;

    EvaImgAdapter adapter;

    @Override
    protected void initView() {
        rcReturnMoney.requestFocus();
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "th":
                tvTitleTitle.setText("申请退货退款");
                llGoodsStatus.setVisibility(View.GONE);
                break;
            case "tk":
                tvTitleTitle.setText("申请退款");
                break;
        }
        imgPathList.add("");
        adapter = new EvaImgAdapter(R.layout.item_sel_return_cer, imgPathList, this);
        adapter.setDefaultImageResource(R.mipmap.btn_tj);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_content:
                        imgTag = position;
                        PhoneCameraUtil.getInstance().showSelectDialog(ReturnApplyActivity.this, false, "");
                        break;
                    case R.id.iv_c:
                        imgPathList.remove(position);
                        if (imgPathList.size() == 0 ||
                                !StringUtils.isEmpty(imgPathList.get(imgPathList.size() - 1))) {
                            imgPathList.add("");
                        }
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
        rvCer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCer.setAdapter(adapter);

    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull final int[] grantResults) {
        PhoneCameraUtil.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhoneCameraUtil.getInstance().onActivityResult(this, requestCode, resultCode, data, new PhoneCameraUtil.OnBackListener() {
            @Override
            public void onCamera(String filePath) {
                setPicToView(filePath);
            }

            @Override
            public void onPhotoAlbum(String filePath) {
//                setPicToView(filePath);
            }

            @Override
            public void onError(String message) {
                showToast(message);
            }
        });
    }


    //保存裁剪之后的图片数据
    private void setPicToView(final String filePath) {
        if (filePath == null) {
            return;
        }

        imgPathList.set(imgTag, filePath);
        if (imgPathList.size() < 3) {
            imgPathList.add("");
        }
        adapter.notifyDataSetChanged();

        File file = new File(filePath);
        //构建body
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("token", "")
                .addFormDataPart("dir", "goods")
                .addFormDataPart("image", file.getName(), RequestBody.create(MediaType.parse("image"), file))
                .build();
//        showProgressDialog();

    }

    private void showStatus(List<String> attrs) {
        AttrSelectView attrStatus = new AttrSelectView(this, attrs, new AttrBack() {
            @Override
            public void back(String attr) {
                tvGoodsStatus.setText(attr);
            }
        });
        attrStatus.show(tvTitleTitle);
    }

    private void showReason(List<String> attrs) {
        AttrSelectView attrReason = new AttrSelectView(this, attrs, new AttrBack() {
            @Override
            public void back(String attr) {
                tvReason.setText(attr);
            }
        });
        attrReason.show(tvTitleTitle);
    }


    @OnClick({R.id.ivLeft, R.id.ll_goods_status, R.id.ll_reason, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ll_goods_status:
                List<String> attrStatus = new ArrayList<>();
                attrStatus.add("未收到货");
                attrStatus.add("已收到货");
                showStatus(attrStatus);
                break;
            case R.id.ll_reason:
                List<String> attrReason = new ArrayList<>();
                attrReason.add("质量不好");
                attrReason.add("拍错了");
                attrReason.add("我不想要了");
                showReason(attrReason);
                break;
            case R.id.tv_commit:
                break;
        }
    }
}
