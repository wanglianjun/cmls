package com.dy.cmls.mall.multimall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.utils.PhoneCameraUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyEnterActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.et_seller_name)
    EditText etSellerName;
    @BindView(R.id.et_business)
    EditText etBusiness;
    @BindView(R.id.et_des)
    EditText etDes;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.iv_zm)
    ImageView ivZm;
    @BindView(R.id.iv_fm)
    ImageView ivFm;
    @BindView(R.id.cb_agreement)
    CheckBox cbAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_enter);
        ButterKnife.bind(this);
        initView();
    }

    private boolean agree=false;
    @Override
    protected void initView() {
        tvTitleTitle.setText("申请入驻");
        cbAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                agree=isChecked;
            }
        });

    }
    private String imgZm,imgFm;
    private int imgTemp;


    private void commit(){
        String sellerName=etSellerName.getText().toString();
        if (StringUtils.isEmpty(sellerName)) {
            ToastUtils.showShort("请输入商户名称");
            return;
        }
        String business=etBusiness.getText().toString();
        if (StringUtils.isEmpty(business)) {
            ToastUtils.showShort("请输入主营项目");
            return;
        }
        String des=etDes.getText().toString();
        String name=etName.getText().toString();
        if (StringUtils.isEmpty(name)) {
            ToastUtils.showShort("请输入联系人");
            return;
        }
        String phone=etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort("请输入手机号");
            return;
        }
        String account=etAccount.getText().toString();
        if (StringUtils.isEmpty(account)) {
            ToastUtils.showShort("请输入账号");
            return;
        }
        String pass=etPass.getText().toString();
        if (StringUtils.isEmpty(pass)) {
            ToastUtils.showShort("请输入密码");
            return;
        }
        if (StringUtils.isEmpty(imgZm)) {
            ToastUtils.showShort("请上传身份证正面照片");
            return;
        }
        if (StringUtils.isEmpty(imgFm)) {
            ToastUtils.showShort("请上传身份证反面照片");
            return;
        }
        if (!agree) {
            ToastUtils.showShort("请阅读【入驻申请协议】" );
            return;
        }
        Bundle bundle=new Bundle();
        bundle.putString("type","commit");
        jumpToPage(EnterStatusActivity.class,bundle);
        finish();

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
                //setPicToView(filePath);
            }

            @Override
            public void onError(String message) {
                showToast(message);
            }
        });
    }

    //保存裁剪之后的图片数据
    private void setPicToView(String bitmap) {
        if (bitmap == null) {
            return;
        }
        File file = new File(bitmap);
        switch (imgTemp){
            case 0:
                imgZm="1";
                Glide.with(this).load(bitmap).into(ivZm);
                break;
            case 1:
                imgFm="1";
                Glide.with(this).load(bitmap).into(ivFm);
                break;
        }

        //构建body
//        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("dir", "users")
//                .addFormDataPart("token", getToken())
//                .addFormDataPart("avatar", file.getName(), RequestBody.create(MediaType.parse("image"), file))
//                .build();
////        showLoadingDialog();
//        ProfileLoader.getInstance().updateHeadImage("更新头像", requestBody).subscribe(new Action1<SimpleBean>() {
//            @Override public void call(SimpleBean bean) {
//                dismissProgressDialog();
//                if (ConstantValues.REQUEST_SUCCESS.equals(bean.getStatus())) {
//                    showToast("修改成功");
//                    getUserInfo();
//                } else if (bean.getMessage() != null) {
//                    showToastLong(bean.getMessage());
//                }
//            }
//        }, new Action1<Throwable>() {
//            @Override public void call(Throwable throwable) {
//                dismissProgressDialog();
//                showToastFailure();
//                showLog("更新头像:报异常2:", throwable.getMessage());
//            }
//        });
    }

    @OnClick({R.id.ivLeft, R.id.iv_up_zm, R.id.iv_up_fm, R.id.tv_agreement, R.id.ll_agreement, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.iv_up_zm:
                imgTemp=0;
                PhoneCameraUtil.getInstance().showSelectDialog(this, false, "");
                break;
            case R.id.iv_up_fm:
                imgTemp=1;
                PhoneCameraUtil.getInstance().showSelectDialog(this, false, "");
                break;
            case R.id.tv_agreement:
                cbAgreement.setChecked(true);
                break;
            case R.id.ll_agreement:

                break;
            case R.id.tv_confirm:
                commit();
                break;
        }
    }
}
