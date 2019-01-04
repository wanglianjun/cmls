package com.dy.cmls.view.pop;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSConstant;
import com.dy.cmls.R;
import com.dy.cmls.base.BaseActivity;
import com.dy.cmls.view.PopupWindow7;
import com.dy.cmls.view.WheelView;
import com.dy.cmls.view.interfaces.TJCallBack;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by lcjing on 2018/12/11.
 */

public class CityPickerView implements View.OnClickListener{
    View vTran;
//    ImageView ivClose;
    TextView tvConfirm;
    private BaseActivity context;
    private PopupWindow7 popupWindow7;
    private TJCallBack callBack;

    public CityPickerView() {
    }

    public CityPickerView(BaseActivity context, TJCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    public TJCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(TJCallBack callBack) {
        this.callBack = callBack;
    }

    public void show(View view) {
        if (popupWindow7 == null) {
//            init();
        }
        popupWindow7.showAsDropDown(view);
    }

    public void dismiss() {
        if (popupWindow7 != null) {
            popupWindow7.dismiss();
        }
    }

    private List<String> list=new ArrayList<>();
    private List<String> list1=new ArrayList<>();
    private List<String> list2=new ArrayList<>();
    private String provinceName="",provinceId="1",cityName="",cityId="",areaName="",areaId="";
    WheelView wvP,wvC,wvA;
//    private void init() {
//        popupWindow7 =
//                new PopupWindow7(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//        View view = View.inflate(context, R.layout.pop_city_picker, null);
//        wvP=view.findViewById(R.id.wv_provice);
//        wvC=view.findViewById(R.id.wv_city);
//        wvA=view.findViewById(R.id.wv_area);
//        vTran = view.findViewById(R.id.v_tran);
//        tvConfirm = view.findViewById(R.id.tv_confirm);
//        vTran.setOnClickListener(this);
//        tvConfirm.setOnClickListener(this);
//
//       final int p=2;
//        wvP.setOffset(p);
//        wvP.setItems(list);
//        wvP.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//            @Override public void onSelected(int selectedIndex, String item) {
//                list1.clear();
//                list2.clear();
//                wvC.setItems(list1);
//                wvA.setItems(list2);
//                int index=0;
//                for (int i = 0; i < areaBeans.size(); i++) {
//                    if (areaBeans.get(i).getAreaName().equals(item)) {
//                        index=i;
//                        break;
//                    }
//                }
//                provinceId=areaBeans.get(index).getAreaId();
//                provinceName=areaBeans.get(index).getAreaName();
//                getArea(provinceId,1);
//            }
//        });
//        wvC.setOffset(p);
//        wvC.setItems(list1);
//        wvC.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//            @Override public void onSelected(int selectedIndex, String item) {
//
//                list2.clear();
//                wvA.setItems(list2);
//                int index=0;
//                for (int i = 0; i < areaBeans1.size(); i++) {
//                    if (areaBeans1.get(i).getAreaName().equals(item)) {
//                        index=i;
//                        break;
//                    }
//                }
//                cityId=areaBeans1.get(index).getAreaId();
//                cityName=areaBeans1.get(index).getAreaName();
//                getArea(cityId,2);
//            }
//        });
//        wvA.setOffset(p);
//        wvA.setItems(list2);
//        wvA.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//            @Override public void onSelected(int selectedIndex, String item) {
//                int index=0;
//                for (int i = 0; i < areaBeans2.size(); i++) {
//                    if (areaBeans2.get(i).getAreaName().equals(item)) {
//                        index=i;
//                        break;
//                    }
//                }
//                areaId=areaBeans2.get(index).getAreaId();
//                areaName=areaBeans2.get(index).getAreaName();
//                setBack();
//            }
//        });
//        getArea("",0);
//        popupWindow7.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                if (dismiss!=null) {
//                    dismiss.dismiss();
//                }
//            }
//        });
//        popupWindow7.setContentView(view);
//    }

    private OnDismiss dismiss;

    public void setDismiss(OnDismiss dismiss) {
        this.dismiss = dismiss;
    }

    public interface OnDismiss{
        void dismiss();
    }

    private void setBack(){
        Intent intent=new Intent();
        intent.putExtra("name",provinceName+" "+cityName+" "+areaName);
        intent.putExtra("pId",provinceId);
        intent.putExtra("cId",cityId);
        intent.putExtra("id",areaId);
        callBack.callBack(intent);
    }

//
//    private List<AreasBean.AreaBean> areaBeans=new ArrayList<>();
//    private List<AreasBean.AreaBean> areaBeans1=new ArrayList<>();
//    private List<AreasBean.AreaBean> areaBeans2=new ArrayList<>();
//
//    private void getArea(final String pid,final int level) {
//        context.showProgressDialog();
//        ProfileLoader.getInstance().getAreas("地区列表弹窗-获取地区列表信息",pid).subscribe(new Action1<AreasBean>() {
//            @Override
//            public void call(AreasBean bean) {
//
//                if (CMLSConstant.REQUEST_SUCCESS.equals(bean.getStatus())) {
//                    if(level==0){
//                        areaBeans.clear();
//                        areaBeans.addAll(bean.getInfo());
//                        list.clear();
//                        for (int i = 0; i < areaBeans.size(); i++) {
//                            list.add(areaBeans.get(i).getAreaName());
//                        }
//                        wvP.setItems(list);
//                        wvP.setSeletion(0);
//                        provinceId=areaBeans.get(0).getAreaId();
//                        provinceName=areaBeans.get(0).getAreaName();
//                        getArea(areaBeans.get(0).getAreaId(),1);
//                    }else if(level==1){
//                        areaBeans1.clear();
//                        areaBeans1.addAll(bean.getInfo());
//                        list1.clear();
//                        for (int i = 0; i < areaBeans1.size(); i++) {
//                            list1.add(areaBeans1.get(i).getAreaName());
//                        }
//                        wvC.setItems(list1);
//                        wvC.setSeletion(0);
//                        cityId=areaBeans1.get(0).getAreaId();
//                        cityName=areaBeans1.get(0).getAreaName();
//                        getArea(areaBeans1.get(0).getAreaId(),2);
//                    }else if(level==2){
//                        context. dismissProgressDialog();
//                        areaBeans2.clear();
//                        areaBeans2.addAll(bean.getInfo());
//                        list2.clear();
//                        for (int i = 0; i < areaBeans2.size(); i++) {
//                            list2.add(areaBeans2.get(i).getAreaName());
//                        }
//                        wvA.setItems(list2);
//                        wvA.setSeletion(0);
//                        areaId=areaBeans2.get(0).getAreaId();
//                        areaName=areaBeans2.get(0).getAreaName();
//                        Intent intent=new Intent();
//                        intent.putExtra("name",provinceName+" "+cityName+" "+areaName);
//                        intent.putExtra("id",areaId);
//                        callBack.callBack(intent);
//                    }
//                } else if (bean.getMessage() != null) {
//                    context. dismissProgressDialog();
//                    ToastUtils.showShort(bean.getMessage());
//                }else {
//                    context. dismissProgressDialog();
//                }
//            }
//        }, new Action1<Throwable>() {
//            @Override
//            public void call(Throwable throwable) {
//                context. dismissProgressDialog();
//                context.showProgressDialog();
//                context.showLog("地区列表弹窗-获取地区列表信息:报异常2:", throwable.getMessage());
//            }
//        });
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
            case R.id.v_tran:
                setBack();
                dismiss();
                break;
        }
    }
}
