package com.dy.cmls.mall.selmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.dy.cmls.mall.selmall.adapter.EvaImgAdapter;
import com.dy.cmls.utils.PhoneCameraUtil;
import com.dy.cmls.utils.TextUtils;
import com.dy.cmls.view.MRatingBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EvaActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.iv_goods)
    ImageView ivGoods;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_type)
    TextView tvGoodsType;
    @BindView(R.id.tv_goods_count)
    TextView tvGoodsCount;
    @BindView(R.id.et_content)
    EditText etContent;

    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.star_goods)
    MRatingBar starGoods;
    @BindView(R.id.star_logistics)
    MRatingBar starLogistics;
    @BindView(R.id.star_service)
    MRatingBar starService;
    @BindView(R.id.ll_star)
    LinearLayout llStar;
    @BindView(R.id.iv_1)
    ImageView iv1;
    @BindView(R.id.iv_c1)
    ImageView ivC1;
    @BindView(R.id.fl_1)
    FrameLayout fl1;
    @BindView(R.id.iv_2)
    ImageView iv2;
    @BindView(R.id.iv_c2)
    ImageView ivC2;
    @BindView(R.id.fl_2)
    FrameLayout fl2;
    @BindView(R.id.iv_3)
    ImageView iv3;
    @BindView(R.id.iv_c3)
    ImageView ivC3;
    @BindView(R.id.fl_3)
    FrameLayout fl3;

    private boolean isMul = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eva);
        ButterKnife.bind(this);
        isMul = getIntent().getBooleanExtra("isMul", false);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("评价");
        if (isMul) {
            etContent.setTextSize(11);
            etContent.setHint("我还有话想说...");
            llStar.setVisibility(View.VISIBLE);
        }
        TextUtils.setMaxEcplise(tvGoodsName, 2,
                "艾菲勒法式马卡龙甜点24枚西式糕点心小蛋糕甜品零艾菲勒法式马卡龙甜点24枚西式糕点心小蛋糕甜品零食品食品");

        initImg();
    }

    List<String> imgPathList = new ArrayList<>();

    EvaImgAdapter adapter;

    private void initImg() {
        imgPathList.add("");
        adapter = new EvaImgAdapter(R.layout.item_sel_eva_img, imgPathList, this);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_content:
                        imgTag = position;
                        PhoneCameraUtil.getInstance().showSelectDialog(EvaActivity.this, false, "");
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
        rvImg.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvImg.setAdapter(adapter);
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


    private int imgTag = 0;

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

    private void commit() {

    }


    @OnClick({R.id.ivLeft, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_commit:
                commit();
                break;
        }
    }
}
