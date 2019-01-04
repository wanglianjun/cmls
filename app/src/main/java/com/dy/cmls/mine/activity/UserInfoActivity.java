package com.dy.cmls.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.loader.bean.UserInfoBean;
import com.dy.cmls.mine.resBean.PersonInfo;
import com.dy.cmls.mine.resBean.SettingBean;
import com.dy.cmls.utils.GlideUtils;
import com.dy.cmls.utils.PhoneCameraUtil;
import com.dy.cmls.utils.SPUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.functions.Action1;

public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.iv_head)
    RoundedImageView ivHead;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.ll_set)
    LinearLayout llSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("个人中心");
        getUserIndex();
    }


    private void getUserIndex() {
        showProgressDialog();
        PersonLoader.getInstance().getUserIndex("获取用户信息-个人中心", SPUtils.getUserId()).subscribe(new Action1<UserInfoBean>() {
            @Override
            public void call(UserInfoBean userInfoBean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(userInfoBean.getStatus())) {
                    UserInfoBean.InfoBean info = userInfoBean.getInfo();
                    if (StringUtils.isEmpty(info.getAvatar())) {
                        GlideUtils.setImage(ivHead,R.mipmap.ic_default_small,info.getAvatar());
//                        Glide.with(UserInfoActivity.this).load(info.getAvatar()).into(ivHead);
                    }
                    tvName.setText(info.getRealname());
                    tvNick.setText(info.getNick_name());
                    tvPhone.setText(info.getPhone());
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("获取用户信息-个人中心:报异常2:", throwable.toString());
            }
        });
    }

    private void getUserInfo() {
        showProgressDialog();
        PersonLoader.getInstance().getUserInfo("个人中心—个人信息", SPUtils.getUserId()).
                subscribe(new Action1<PersonInfo>() {
                    @Override
                    public void call(PersonInfo info) {
                        dismissProgressDialog();
                        if (CMLSConstant.REQUEST_SUCCESS.equals(info.getStatus())) {
                            tvName.setText(info.getInfo().getNick_name());
//                    tvNick.setText(info.getInfo().get);

                        } else {
                            showToast(info.getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        dismissProgressDialog();
                        showToastFailure();
                        showLog("个人中心—个人信息:报异常2:", throwable.toString());
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
//                Glide.with(UserInfoActivity.this).load(filePath).into(ivHead);
                GlideUtils.setImage(ivHead,R.mipmap.ic_default_small,filePath);
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
        if(requestCode==1&&resultCode==1){
            getUserIndex();
        }
    }

    //保存裁剪之后的图片数据
    private void setPicToView(String bitmap) {
        if (bitmap == null) {
            return;
        }
        File file = new File(bitmap);
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("userid", SPUtils.getUserId())
                .addFormDataPart("avatar", file.getName(), RequestBody.create(MediaType.parse("image"), file))
                .build();
//        showLoadingDialog();
        PersonLoader.getInstance().updateHeadImage("更新头像", requestBody).subscribe(new Action1<SimpleResBean>() {
            @Override public void call(SimpleResBean bean) {
                dismissProgressDialog();
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    showToast("修改成功");
                    getUserIndex();
                } else if (bean.getMessage() != null) {
                    showToastLong(bean.getMessage());
                }
            }
        }, new Action1<Throwable>() {
            @Override public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("更新头像:报异常2:", throwable.getMessage());
            }
        });
    }


    @OnClick({R.id.ivLeft, R.id.iv_head, R.id.ll_location, R.id.ll_set, R.id.ll_head, R.id.ll_nick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.iv_head:
            case R.id.ll_head:
                PhoneCameraUtil.getInstance().showSelectDialog(this, true, "");
                break;
            case R.id.ll_nick:
                jumpToPage(EditInfoActivity.class,true,1);
                break;
            case R.id.ll_location:
                jumpToPage(MyLocationListActivity.class);
                break;
            case R.id.ll_set:
                jumpToPage(SettingActivity.class);
                break;
        }
    }


}
