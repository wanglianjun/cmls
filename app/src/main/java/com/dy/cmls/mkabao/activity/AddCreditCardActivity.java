package com.dy.cmls.mkabao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.utils.CharCheckUtil;
import com.dy.cmls.utils.PhoneCameraUtil;
import com.dy.cmls.view.WheelView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by tj on 2017/10/9.
 */

public class AddCreditCardActivity extends BaseActivity {
    @BindView(R.id.tvRealName)
    TextView tvRealName;
    @BindView(R.id.tvIDCrad)
    TextView tvIDCrad;
    @BindView(R.id.etBanksNumber)
    EditText etBanksNumber;
    @BindView(R.id.etCVN2)
    EditText etCVN2;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.llTime)
    LinearLayout llTime;
    @BindView(R.id.tvBankName)
    TextView tvBankName;
    @BindView(R.id.llBankName)
    LinearLayout llBankName;
    @BindView(R.id.tvZhangDanRi)
    TextView tvZhangDanRi;
    @BindView(R.id.llZhangDanRi)
    LinearLayout llZhangDanRi;
    @BindView(R.id.tvHuanKuanRi)
    TextView tvHuanKuanRi;
    @BindView(R.id.llHuanKuanRi)
    LinearLayout llHuanKuanRi;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    @BindView(R.id.idebankcard_ib)
    ImageButton idebankcardIb;
    @BindView(R.id.tvTitleTitle)
    TextView tvTitleTitle;


    private PopupWindow datePopupWindow;
    // 倒数秒数
    private int countSeconds = 60;
    private String yanzhengma;
    private String showTime;
    private String youxiaoqiYear;
    private String youxiaoqiMonth;
    private String bankId;
    private String checkType = "";
    private String bindId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_xinyongka);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (datePopupWindow != null) {
            datePopupWindow.dismiss();
        }
    }

    @Override
    protected void initView() {
        //checkRenZhengType();
        tvTitleTitle.setText("添加信用卡");
        getCardList();
    }

    @OnClick({
            R.id.llTime, R.id.llBankName, R.id.llZhangDanRi, R.id.llHuanKuanRi, R.id.tvGetCode,
            R.id.tvConfirm, R.id.idebankcard_ib,R.id.ivLeft
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.llBankName:
                getBankName();
                break;
            case R.id.llTime:
                youxiaoqiMonth = "01";
                youxiaoqiYear = "18";
                tvTime.setText(youxiaoqiMonth + "/" + youxiaoqiYear);
                initPopupDate2();
                break;
            case R.id.llZhangDanRi:
                showTime = "1";
                tvZhangDanRi.setText("1");
                initPopupDate();
                break;
            case R.id.llHuanKuanRi:
                showTime = "2";
                tvHuanKuanRi.setText("1");
                initPopupDate();
                break;
            case R.id.tvGetCode:
                getCode();
                break;
            case R.id.tvConfirm:
                String bankNumber = etBanksNumber.getText().toString();
                if (bankNumber == null || bankNumber.isEmpty()) {
                    showToast("请输入信用卡号");
                    return;
                }
                String cvn2 = etCVN2.getText().toString();
                if (cvn2 == null || cvn2.isEmpty()) {
                    showToast("请输入信用卡背面后三位cvn2");
                    return;
                }
                //String time = youxiaoqiYear + youxiaoqiMonth;
                String time = youxiaoqiMonth + youxiaoqiYear;
                if (time == null || time.isEmpty() || time.length() != 4) {
                    showToast("请选择信用卡有效期");
                    return;
                }
                String bankName = tvBankName.getText().toString();
                if (bankName == null || bankName.isEmpty()) {
                    showToast("请输入发卡银行");
                    return;
                }
                String zhangDanRi = tvZhangDanRi.getText().toString();
                if (zhangDanRi == null || zhangDanRi.isEmpty()) {
                    showToast("请选择账单日");
                    return;
                }
                String huanKuanRi = tvHuanKuanRi.getText().toString();
                if (huanKuanRi == null || huanKuanRi.isEmpty()) {
                    showToast("请选择还款日");
                    return;
                }
                String phone = etPhone.getText().toString();
                if (phone == null || phone.isEmpty()) {
                    showToast("请输入银行预留手机号");
                    return;
                }
                if (yanzhengma == null || yanzhengma.isEmpty()) {
                    showToast("请先获取验证码");
                    return;
                }
                String code = etCode.getText().toString();
                if (code == null || code.isEmpty()) {
                    showToast("请输入验证码");
                    return;
                }
                if (!yanzhengma.equals(code)) {
                    showToast("验证码不正确");
                    return;
                }
                addXinYongKa(bankNumber, bankName, cvn2, time, phone, zhangDanRi, huanKuanRi);
                break;
            case R.id.idebankcard_ib:
                PhoneCameraUtil.getInstance().showSelectDialog(this, false, "");
                break;
            default:
                break;
        }
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
                getBankNo(filePath);
            }

            @Override
            public void onPhotoAlbum(String filePath) {

            }

            @Override
            public void onError(String message) {
                showToast(message);
            }
        });
    }




    public void getBankNo(final String outPath) {
        Map<String, Object> map = new ArrayMap<>();
        map.put("enctype", "application/x-www-form-urlencoded");
        map.put("showapi_appid", "80302");
        map.put("showapi_sign", "136a95625ef34d868b2a7142cb905de1");
//        String base64 = doCode(outPath);
//        map.put("imgData", base64);
        showProgressDialog();

    }


    private void addXinYongKa(String bankNumber, String bankName, String cvn2, String time, String phone,
                              String zhangDanRi, String huanKuanRi) {

        showProgressDialog();
        Map<String, String> map = new ArrayMap<>();
        map.put("userid", "");
        map.put("bank_num", bankNumber);
        map.put("bank_name", bankName);
        map.put("bank_number", bankId);
        map.put("cvn2", cvn2);
        map.put("date", time);
        map.put("phone", phone);
        map.put("statement", zhangDanRi);
        map.put("repayment", huanKuanRi);

    }


    private void clearInfo() {
        stopCount();
        bindId = null;
        etCode.setText("");
    }

    private void getInfo() {

        Map<String, String> map = new ArrayMap<>();
        map.put("userid", "");

    }

    private void getCode() {
        String phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            showToast("请输入手机号");
            return;
        }
//        if (!CharCheckUtil.isPhoneNum(phone)) {
//            showToast("手机号格式不正确");
//            return;
//        }
        showProgressDialog();
        Map<String, String> map = new ArrayMap<>();
        map.put("phone", phone);
        startCountBack();

    }


    private void getBankName() {
        showProgressDialog();
        Map<String, String> map = new ArrayMap<>();

    }

    private void checkShenFen() {
        Map<String, String> map = new ArrayMap<>();
        map.put("userid", "");

    }

    private void getCardList() {
//        showProgressDialog();
        Map<String, String> map = new ArrayMap<>();
        map.put("userid", "");

    }

    private void initPopupDate() {
        View popView = View.inflate(this, R.layout.popup_select, null);
        datePopupWindow =
                new PopupWindow(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        WheelView wheelView;
        ImageView ivClose;
        TextView tvConfirm;
        wheelView =  popView.findViewById(R.id.wheelView);
        ivClose =  popView.findViewById(R.id.ivClose);
        tvConfirm =  popView.findViewById(R.id.tvConfirm);

        List<String> list = new ArrayList<>();
        list.add("1号");
        list.add("2号");
        list.add("3号");
        list.add("4号");
        list.add("5号");
        list.add("6号");
        list.add("7号");
        list.add("8号");
        list.add("9号");
        list.add("10号");
        list.add("11号");
        list.add("12号");
        list.add("13号");
        list.add("14号");
        list.add("15号");
        list.add("16号");
        list.add("17号");
        list.add("18号");
        list.add("19号");
        list.add("20号");
        list.add("21号");
        list.add("22号");
        list.add("23号");
        list.add("24号");
        list.add("25号");
        list.add("26号");
        list.add("27号");
        list.add("28号");
        list.add("29号");
        list.add("30号");
        list.add("31号");
        wheelView.setOffset(2);
        wheelView.setItems(list);
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                String[] strs = item.split("号");
                if ("1".equals(showTime)) {
                    tvZhangDanRi.setText(strs[0]);
                } else {
                    tvHuanKuanRi.setText(strs[0]);
                }
            }
        });
        ivClose.setOnClickListener(v -> datePopupWindow.dismiss());
        tvConfirm.setOnClickListener(view -> datePopupWindow.dismiss());
        datePopupWindow.setFocusable(true);
        datePopupWindow.setContentView(popView);
        datePopupWindow.setAnimationStyle(R.style.popwin_anim_style1);
        datePopupWindow.showAtLocation(tvRealName, Gravity.BOTTOM, 0, 0);
    }

    private void initPopupDate2() {
        View popView = View.inflate(this, R.layout.popup_select_date, null);
        final PopupWindow datePopupWindow2 =
                new PopupWindow(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        WheelView wheelView1;
        WheelView wheelView2;
        TextView tvClose;
        TextView tvConfirm;
        wheelView1 =  popView.findViewById(R.id.wheelView1);
        wheelView2 =  popView.findViewById(R.id.wheelView2);
        tvClose =  popView.findViewById(R.id.tvClose);
        tvConfirm =  popView.findViewById(R.id.tvConfirm);

        List<String> list = new ArrayList<>();
        list.add("01月");
        list.add("02月");
        list.add("03月");
        list.add("04月");
        list.add("05月");
        list.add("06月");
        list.add("07月");
        list.add("08月");
        list.add("09月");
        list.add("10月");
        list.add("11月");
        list.add("12月");
        wheelView1.setOffset(2);
        wheelView1.setItems(list);
        wheelView1.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                youxiaoqiMonth = "";
                String[] strs = item.split("月");
                youxiaoqiMonth += strs[0];
            }
        });

        List<String> list2 = new ArrayList<>();
        list2.add("2018年");
        list2.add("2019年");
        list2.add("2020年");
        list2.add("2021年");
        list2.add("2022年");
        list2.add("2023年");
        list2.add("2024年");
        list2.add("2025年");
        list2.add("2026年");
        list2.add("2027年");
        list2.add("2028年");
        list2.add("2029年");
        list2.add("2030年");
        list2.add("2031年");
        list2.add("2032年");
        list2.add("2033年");
        list2.add("2034年");
        list2.add("2035年");
        list2.add("2036年");
        list2.add("2037年");
        list2.add("2038年");
        list2.add("2039年");
        list2.add("2040年");
        list2.add("2041年");
        list2.add("2042年");
        list2.add("2043年");
        list2.add("2044年");
        list2.add("2045年");
        list2.add("2046年");
        list2.add("2047年");
        list2.add("2048年");
        list2.add("2049年");
        list2.add("2050年");
        list2.add("2051年");
        list2.add("2052年");
        list2.add("2053年");
        list2.add("2054年");
        list2.add("2055年");
        list2.add("2056年");
        list2.add("2057年");
        list2.add("2058年");
        list2.add("2059年");
        list2.add("2060年");
        wheelView2.setOffset(2);
        wheelView2.setItems(list2);
        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                youxiaoqiYear = "";
                String[] strs = item.split("年");
                youxiaoqiYear += strs[0].substring(2);
            }
        });
        tvClose.setOnClickListener(v -> datePopupWindow2.dismiss());
        tvConfirm.setOnClickListener(view -> datePopupWindow2.dismiss());
        datePopupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                tvTime.setText(youxiaoqiMonth + "/" + youxiaoqiYear);
            }
        });
        datePopupWindow2.setFocusable(true);
        datePopupWindow2.setContentView(popView);
        datePopupWindow2.setAnimationStyle(R.style.popwin_anim_style1);
        datePopupWindow2.showAtLocation(tvRealName, Gravity.BOTTOM, 0, 0);
    }


    // 开始倒计时
    private void startCountBack() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvGetCode.setText(countSeconds + "s");
                hanlder.sendEmptyMessage(0);
                tvGetCode.setClickable(false);
            }
        });
    }

    private void stopCount() {
        hanlder.removeMessages(0);
        countSeconds = 60;
        tvGetCode.setText("重新获取");
        tvGetCode.setClickable(true);
    }

    private Handler hanlder = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (countSeconds > 0) {
                        --countSeconds;
                        tvGetCode.setText(countSeconds + "s");
                        hanlder.sendEmptyMessageDelayed(0, 1000);
                    } else {
                        countSeconds = 60;
                        tvGetCode.setText("重新获取");
                        tvGetCode.setClickable(true);
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
