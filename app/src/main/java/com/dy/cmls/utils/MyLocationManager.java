package com.dy.cmls.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;


import java.io.IOException;
import java.util.List;

import static com.dy.cmls.utils.PermissionUtils.TAG_GPS;

/**
 * Created by lcjing on 2019/1/2.
 */
//        ---------------------
//                作者：心-晴
//                来源：CSDN
//                原文：https://blog.csdn.net/u012285177/article/details/69267535
//                版权声明：本文为博主原创文章，转载请附上博文链接！
public class MyLocationManager {

    private LocationManager locationManager;
    private Activity activity;

    public MyLocationManager(Activity activity) {
        this.activity = activity;
        init();
    }

    private void init() {
        PermissionUtils.getInstance()
                .requestPermission(activity, PermissionUtils.TAG_GPS, new PermissionUtils.OnRequestPermissionResult() {
                    @Override
                    public void onSucceed() {
                        getLocation();
                    }

                    @Override
                    public void onRequestSucceedTag(int tag, String other) {

                    }

                    @Override
                    public void onError(int tag, String error) {

                    }
                });



    }

    public void getLocation(){
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        String locationProvider;
//        if (providers.contains(LocationManager.GPS_PROVIDER)) {
//            locationProvider = LocationManager.GPS_PROVIDER;
//        } else
            if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            return;
        }
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) !=  PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) !=  PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {
            LogUtil.d("MyLocationManager",provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
            LogUtil.d("MyLocationManager",provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            LogUtil.d("MyLocationManager",provider);
        }

        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            String addString = null;
            List<Address> addList = null;
            Geocoder ge = new Geocoder(activity);
            try {
                addList = ge.getFromLocation(latitude, longitude, 3);
            } catch (IOException e) {

                e.printStackTrace();
            }
            if (addList != null && addList.size() > 0) {
                for (int i = 0; i < addList.size(); i++) {
                    Address ad = addList.get(i);
                    addString = ad.getLocality();//拿到城市
                }
            }
            String locationStr = "纬度：" + location.getLatitude()
                    + "经度：" + location.getLongitude()+ addString;
            LogUtil.d("MyLocationManager",locationStr);
        }
    };





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
                                getLocation();
                            }

                            @Override
                            public void onRequestSucceedTag(int tag, String other) {
                                switch (tag) {
                                    case TAG_GPS:
                                        getLocation();
                                        break;
                                }
                            }

                            @Override
                            public void onError(int tag, String error) {

                            }
                        });
    }



    public void remove(){
        if (locationManager != null) {
            //移除监听器
            locationManager.removeUpdates(locationListener);
        }
    }

}
