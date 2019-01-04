package com.dy.cmls.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.loader.PersonLoader;
import com.dy.cmls.loader.bean.SimpleResBean;
import com.dy.cmls.mine.resBean.PersonInfo;
import com.dy.cmls.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class EditInfoActivity extends BaseActivity {

    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;
    @BindView(R.id.et_nick)
    EditText etNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitleTitle.setText("修改昵称");

    }

    private void modifyNick(String nick) {
        showProgressDialog();
        PersonLoader.getInstance().modifyNick("修改昵称", SPUtils.getUserId(),nick).
                subscribe(new Action1<SimpleResBean>() {
                    @Override
                    public void call(SimpleResBean info) {
                        dismissProgressDialog();
                        if (CMLSConstant.REQUEST_SUCCESS.equals(info.getStatus())) {
                            showToast(info.getMessage());
                            setResult(1);
                            finish();
                        } else {
                            showToast(info.getMessage());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        dismissProgressDialog();
                        showToastFailure();
                        showLog("修改昵称:报异常2:", throwable.toString());
                    }
                });
    }

    @OnClick({R.id.ivLeft, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tv_confirm:
                String nick=etNick.getText().toString();
                if (StringUtils.isEmpty(nick)) {
                    ToastUtils.showShort("昵称不能为空！");
                    return;
                }
            modifyNick(nick);
                break;
        }
    }
}
