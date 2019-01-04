package com.dy.cmls.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.mine.resBean.SettingBean;
import com.dy.cmls.utils.SPUtils;
import com.dy.cmls.view.interfaces.TJCallBack;
import com.dy.cmls.view.pop.CityPickerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class AddLocationActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_address)
    EditText tvAddress;
    @BindView(R.id.iv_default)
    ImageView ivDefault;
    private boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        ButterKnife.bind(this);
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        initView();
    }


    private String addressId;

    @Override
    protected void initView() {
        tvTitleTitle.setText("收货地址");
        if (isEdit) {
            addressId=getIntent().getStringExtra("id");
            etName.setText(getIntent().getStringExtra("name"));
            tvLocation.setText(getIntent().getStringExtra("location"));
            tvAddress.setText(getIntent().getStringExtra("address"));
            etPhone.setText(getIntent().getStringExtra("phone"));
            pId=getIntent().getStringExtra("pId");
            cId=getIntent().getStringExtra("cId");
            aId=getIntent().getStringExtra("aId");
            isDefault=getIntent().getBooleanExtra("isDef",false);
            ivDefault.setImageResource(isDefault?R.mipmap.btn_gx_p:R.mipmap.btn_goux);
        }
    }



    private void addAddress(){
        String name=etName.getText().toString();
        if (StringUtils.isEmpty(name)) {
            ToastUtils.showShort("请输入收货人姓名");
            return;
        }
        String phone=etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort("请输入电话");
            return;
        }

        if (StringUtils.isEmpty(aId)) {
            ToastUtils.showShort("请选择省市区");
            return;
        }
        String address=tvAddress.getText().toString();
        if (StringUtils.isEmpty(address)) {
            ToastUtils.showShort("详细地址");
            return;
        }
        if (address.length()<5) {
            ToastUtils.showShort("详细地址不能少于5个字");
            return;
        }
        String def=isDefault?"1":"0";

        showProgressDialog();
        PersonLoader.getInstance().addAddress("添加收货地址",name, SPUtils.getUserId(),phone
        ,pId,cId,aId,def,address).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                ToastUtils.showShort(bean.getMessage());
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    finish();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("添加收货地址:报异常2:", throwable.toString());
            }
        });
    }


    private void editAddress(){
        String name=etName.getText().toString();
        if (StringUtils.isEmpty(name)) {
            ToastUtils.showShort("请输入收货人姓名");
            return;
        }
        String phone=etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastUtils.showShort("请输入电话");
            return;
        }

        if (StringUtils.isEmpty(aId)) {
            ToastUtils.showShort("请选择省市区");
            return;
        }
        String address=tvAddress.getText().toString();
        if (StringUtils.isEmpty(address)) {
            ToastUtils.showShort("详细地址");
            return;
        }
        if (address.length()<5) {
            ToastUtils.showShort("详细地址不能少于5个字");
            return;
        }
        String def=isDefault?"1":"0";

        showProgressDialog();
        PersonLoader.getInstance().editAddress("修改收货地址",name, SPUtils.getUserId(),phone
                ,pId,cId,aId,def,address,addressId).subscribe(new Action1<SimpleResBean>() {
            @Override
            public void call(SimpleResBean bean) {
                dismissProgressDialog();
                ToastUtils.showShort(bean.getMessage());
                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
                    finish();
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                dismissProgressDialog();
                showToastFailure();
                showLog("修改收货地址:报异常2:", throwable.toString());
            }
        });
    }


    private  boolean isDefault=false;
    private String aId,cId,pId;
    @OnClick({R.id.ivLeft, R.id.ll_location, R.id.ll_default, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ll_location:
                CityPickerView cityPickerView=new CityPickerView(AddLocationActivity.this, new TJCallBack() {
                    @Override
                    public void callBack(Intent intent) {
                        tvLocation.setText(intent.getStringExtra("name"));
                        pId=intent.getStringExtra("pId");
                        cId=intent.getStringExtra("cId");
                        aId=intent.getStringExtra("id");
                    }
                });
                cityPickerView.show(tvTitleTitle);
                break;
            case R.id.ll_default:
                isDefault=!isDefault;
                ivDefault.setImageResource(isDefault?R.mipmap.btn_gx_p:R.mipmap.btn_goux);
                break;
            case R.id.tv_confirm:

                if (isEdit) {
                    editAddress();
                }else
                addAddress();
                break;
        }
    }
}
