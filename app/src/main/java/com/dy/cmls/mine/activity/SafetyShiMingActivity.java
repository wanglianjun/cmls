package com.dy.cmls.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.ORCLoader;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.IDCardORCBean;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.utils.Base64CodeUtil;
import com.dy.cmls.utils.GlideUtils;
import com.dy.cmls.utils.PhoneCameraUtil;
import com.dy.cmls.utils.SPUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.functions.Action1;

public class SafetyShiMingActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.et_real_name)
    EditText etRealName;
    @BindView(R.id.tv_id_crad)
    EditText etIdCrad;
    @BindView(R.id.iv_zm)
    ImageView ivZm;
    @BindView(R.id.iv_up_zm)
    ImageView ivUpZm;
    @BindView(R.id.iv_fm)
    ImageView ivFm;
    @BindView(R.id.iv_up_fm)
    ImageView ivUpFm;
    @BindView(R.id.iv_sc)
    ImageView ivSc;
    @BindView(R.id.iv_up_sc)
    ImageView ivUpSc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_shi_ming);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("实名认证");
        getImage();
        getUserIndex();

    }

    private int imgTemp;

    @OnClick({R.id.ivLeft, R.id.iv_up_zm, R.id.iv_up_fm, R.id.iv_up_sc,R.id.tv_confirm})
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
            case R.id.iv_up_sc:
                imgTemp=2;
                PhoneCameraUtil.getInstance().showSelectDialog(this, false, "");
                break;
            case R.id.tv_confirm:
                String name=etRealName.getText().toString();
                if (StringUtils.isEmpty(name)) {
                    ToastUtils.showShort("请输入真实姓名");
                    return;
                }
                String id=etIdCrad.getText().toString();
                if (StringUtils.isEmpty(id)) {
                    ToastUtils.showShort("请输入身份证号");
                    return;
                }
                shiming(name,id);
                break;
        }
    }

    private void shiming(String name,String id){
        PersonLoader.getInstance().realNameSubmit("实名认证-提交", SPUtils.getUserId(),name,id).subscribe(new Action1<SimpleResBean>() {
            @Override public void call(SimpleResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    SPUtils.setRealNameAndId(id,name);
                    showToastLong(bean.getMessage());
                    finish();
                } else if (bean.getMessage() != null) {
                    showToastLong(bean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("实名认证-提交:报异常2:", throwable.getMessage());
            }
        });
    }

    private void getImage(){
        PersonLoader.getInstance().getRealImage("实名认证-获取图片", SPUtils.getUserId()).subscribe(new Action1<SimpleResBean>() {
            @Override public void call(SimpleResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    if (!StringUtils.isEmpty(bean.getInfo().getIdcard_image1())) {
//                        Glide.with(SafetyShiMingActivity.this).load(bean.getInfo().getIdcard_image1()).into(ivZm);
                        GlideUtils.setImage(ivZm,R.mipmap.ic_zm,bean.getInfo().getIdcard_image1());
                    }
                    if (!StringUtils.isEmpty(bean.getInfo().getIdcard_image2())) {
                        GlideUtils.setImage(ivFm,R.mipmap.ic_fm,bean.getInfo().getIdcard_image2());
//                        Glide.with(SafetyShiMingActivity.this).load(bean.getInfo().getIdcard_image2()).into(ivFm);
                    }

                } else if (bean.getMessage() != null) {
                    showToastLong(bean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("实名认证-获取图片:报异常2:", throwable.getMessage());
            }
        });

    }

    private void getUserIndex() {
        showProgressDialog();
        PersonLoader.getInstance().getUserIndex("获取用户信息-实名认证", SPUtils.getUserId()).subscribe(new Action1<UserInfoBean>() {
            @Override
            public void call(UserInfoBean userInfoBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(userInfoBean.getStatus())) {
                    UserInfoBean.InfoBean info = userInfoBean.getInfo();
                    etRealName.setText(info.getRealname());
                    etIdCrad.setText(info.getIdcard());
                    SPUtils.setRealNameAndId(info.getIdcard(),info.getRealname());


                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("获取用户信息-实名认证:报异常2:", throwable.toString());
            }
        });
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
        String type="";
        switch (imgTemp){
            case 0:
                type="idcard_image1";
                postIDCardORC(file,"1");
//                Glide.with(this).load(bitmap).into(ivZm);
                GlideUtils.setImage(ivZm,R.mipmap.ic_zm,bitmap);
                break;
            case 1:
                type="idcard_image2";
//                Glide.with(this).load(bitmap).into(ivFm);
                GlideUtils.setImage(ivFm,R.mipmap.ic_fm,bitmap);
                break;
            case 2:

                break;
        }

        showProgressDialog();
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("userid", SPUtils.getUserId())
                .addFormDataPart("type", type)
                .addFormDataPart("image", file.getName(), RequestBody.create(MediaType.parse("image"), file))
                .build();
        PersonLoader.getInstance().updateImage("实名认证-图片上传", requestBody).subscribe(new Action1<SimpleResBean>() {
            @Override public void call(SimpleResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    showToast("上传成功");

                } else if (bean.getMessage() != null) {
                    showToastLong(bean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("实名认证-图片上传:报异常2:", throwable.getMessage());
            }
        });
    }

    private void postIDCardORC(final File file, String type) {

        showProgressDialog();
        ORCLoader.getInstance()
                .postIDCardORC("ORC识别身份证", "application/x-www-form-urlencoded", CMLSConstant.ORC_APP_ID, CMLSConstant.ORC_SIGN,
                        Base64CodeUtil.doCode(file), type)
                .subscribe(new Action1<IDCardORCBean>() {
                    @Override public void call(IDCardORCBean bean) {
                        dismissProgressDialog();
                        if (bean.getShowapi_res_body() != null) {
                            if (bean.getShowapi_res_body().getIdNo() == null) {
                                ToastUtils.showShort("识别失败,请重试或手动输入");
                                return;
                            }
                            if (bean.getShowapi_res_body().getIdNo() != null) {
                                etIdCrad.setText(bean.getShowapi_res_body().getIdNo());
                            }
                            if (bean.getShowapi_res_body().getName() != null) {
                                etRealName.setText(bean.getShowapi_res_body().getName());
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        dismissProgressDialog();
                        showToastFailure();
                        showLog("实名认证-图片上传:报异常2:", throwable.getMessage());
                    }
                });
    }

}
