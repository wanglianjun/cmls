package com.dy.cmls.utils;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.util.ToastUtils;
import com.dy.cmls.CMLSApp;

import java.util.ArrayList;
import java.util.List;

import static com.dy.cmls.utils.PermissionUtils.TAG_GPS;

/**
 * Created by lcjing on 2019/1/2.
 */

public class MyBDLocationManager {
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    static MyBDLocationManager INSTANCE;

    public static MyBDLocationManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MyBDLocationManager();
        }
        return INSTANCE;
    }

    public MyBDLocationManager() {
        onCreate();
    }

    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
//原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明
    public void onCreate() {
        mLocationClient = new LocationClient(CMLSApp.getInstance());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
//        mLocationClient.start();
    }

    public void start(){
        if (mLocationClient!=null) {
            mLocationClient.start();
        }
    }

    public void stop(){
        if (mLocationClient!=null) {
            locationBack=null;
            mLocationClient.stop();
        }
    }

    private  LocationBack locationBack;

    public LocationBack getLocationBack() {
        return locationBack;
    }

    public void setLocationBack(LocationBack locationBack) {
        this.locationBack = locationBack;
    }

    /**
     * 授权回调
     */
    public void onRequestPermissionsResult(@NonNull final Activity activity, int requestCode, String permissions[],
                                           int[] grantResults) {
        PermissionUtils.getInstance()
                .onRequestPermissionsResult(activity, requestCode, permissions, grantResults,
                        new PermissionUtils.OnRequestPermissionResult() {
                            @Override
                            public void onSucceed() {
                                start();
                            }

                            @Override
                            public void onRequestSucceedTag(int tag, String other) {
                                switch (tag) {
                                    case TAG_GPS:
                                        start();
                                        break;
                                }
                            }

                            @Override
                            public void onError(int tag, String error) {

                            }
                        });
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            double latitude=location.getLatitude();
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            String l=province+city+district;
            LogUtil.e("MyBDLocationManager",province+city+district);
//            ToastUtils.showShort(l);
            if (locationBack!=null) {
                List<String> list=new ArrayList<>();
                list.add(province);
                list.add(city);
                list.add(district);
                locationBack.success(list);
            }
        }
    }
}
