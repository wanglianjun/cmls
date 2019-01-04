package com.dy.cmls.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dy.cmls.R;

import static android.text.TextUtils.isEmpty;

/**
 * @author tangji
 * @date 2018/9/3 15:12
 */

public class PermissionUtils {
    private static PermissionUtils INSTANCE;
    public static final int TAG_GPS = 1;
    public static final int TAG_CAMERA = 2;
    public static final int TAG_CAMERA_RW_SDCARD = 3;
    public static final int TAG_RW_SDCARD = 4;
    public static final int TAG_READ_PHONE_STATE = 5;

    public static PermissionUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PermissionUtils();
        }
        return INSTANCE;
    }

    public interface OnRequestPermissionResult {
        //已经具有权限,直接进行下一步逻辑
        void onSucceed();

        //请求授权成功,进行下一步逻辑, 可能携带传递的值
        void onRequestSucceedTag(int tag, String other);

        //其他情况
        void onError(int tag, String error);
    }

    public void requestPermission(@NonNull Activity activity, int tag, @NonNull OnRequestPermissionResult result) {
        if (TAG_GPS == tag) {
            requestPermissionGPS(activity, result);
        } else if (TAG_CAMERA == tag) {
            requestPermissionCAMERA(activity, result);
        } else if (TAG_RW_SDCARD == tag) {
            requestPermissionRWSDCard(activity, result, TAG_RW_SDCARD, false);
        } else {
            result.onError(tag, "tag不存在");
        }
    }

    /**
     * 请求GPS定位权限
     */
    private void requestPermissionGPS(@NonNull Activity activity, @NonNull OnRequestPermissionResult result) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //低版本跳过
            result.onSucceed();
        } else if (selfPermissionGranted(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            //已经有了权限执行下一步逻辑
            result.onSucceed();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            //其他情况没有权限去设置界面申请
            showNotifyDialog(activity, "GPS", TAG_GPS, result);
        } else {
            //没有权限去申请
            ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION }, TAG_GPS);
        }
    }

    /**
     * 拍照往SD卡写图片
     */
    private void requestPermissionCAMERA(@NonNull Activity activity, @NonNull OnRequestPermissionResult result) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            result.onSucceed();
        } else if (selfPermissionGranted(activity, Manifest.permission.CAMERA)) {
            requestPermissionRWSDCard(activity, result, TAG_CAMERA_RW_SDCARD, false);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
            showNotifyDialog(activity, "拍照", TAG_CAMERA, result);
        } else {
            ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.CAMERA }, TAG_CAMERA);
        }
    }

    /**
     * 请求读写SDCard权限
     */
    private void requestPermissionRWSDCard(@NonNull Activity activity, @NonNull OnRequestPermissionResult result, int tag,
                                           boolean isRquestCameraBack) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            result.onSucceed();
        } else if (selfPermissionGranted(activity, Manifest.permission.READ_EXTERNAL_STORAGE) && selfPermissionGranted(activity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (isRquestCameraBack) {
                result.onRequestSucceedTag(TAG_CAMERA, "");
            } else {
                result.onSucceed();
            }
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
            || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (isRquestCameraBack) {
                showNotifyDialog(activity, "读写存储卡", TAG_CAMERA, result);
            } else {
                showNotifyDialog(activity, "读写存储卡", TAG_RW_SDCARD, result);
            }
        } else {
            ActivityCompat.requestPermissions(activity, new String[] {
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, tag);
        }
    }

    /**
     * 获取手机识别码
     */
    public void getIMEI(@NonNull Activity activity, @NonNull OnRequestPermissionResult result) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getTargetSdkVersion(activity) >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
                    == PackageManager.PERMISSION_GRANTED) {
                    TelephonyManager telephonyManager = (TelephonyManager) activity.getApplicationContext()
                        .getSystemService(activity.getApplicationContext().TELEPHONY_SERVICE);
                    if (!TextUtils.isEmpty(telephonyManager.getDeviceId())) {
                        result.onRequestSucceedTag(TAG_READ_PHONE_STATE, telephonyManager.getDeviceId());
                    } else {
                        result.onRequestSucceedTag(TAG_READ_PHONE_STATE, getDeviceId(activity));
                    }
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
                    showNotifyDialog(activity, "获取手机识别码", TAG_READ_PHONE_STATE, result);
                } else {
                    ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.READ_PHONE_STATE },
                        TAG_READ_PHONE_STATE);
                }
            } else {
                if (PermissionChecker.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
                    == PermissionChecker.PERMISSION_GRANTED) {
                    TelephonyManager telephonyManager = (TelephonyManager) activity.getApplicationContext()
                        .getSystemService(activity.getApplicationContext().TELEPHONY_SERVICE);
                    if (!TextUtils.isEmpty(telephonyManager.getDeviceId())) {
                        result.onRequestSucceedTag(TAG_READ_PHONE_STATE, telephonyManager.getDeviceId());
                    } else {
                        result.onRequestSucceedTag(TAG_READ_PHONE_STATE, getDeviceId(activity));
                    }
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)) {
                    showNotifyDialog(activity, "获取手机识别码", TAG_READ_PHONE_STATE, result);
                } else {
                    ActivityCompat.requestPermissions(activity, new String[] { Manifest.permission.READ_PHONE_STATE },
                        TAG_READ_PHONE_STATE);
                }
            }
        } else {
            TelephonyManager telephonyManager = (TelephonyManager) activity.getApplicationContext()
                .getSystemService(activity.getApplicationContext().TELEPHONY_SERVICE);
            if (!TextUtils.isEmpty(telephonyManager.getDeviceId())) {
                result.onRequestSucceedTag(TAG_READ_PHONE_STATE, telephonyManager.getDeviceId());
            } else {
                result.onRequestSucceedTag(TAG_READ_PHONE_STATE, getDeviceId(activity));
            }
        }
    }

    /**
     * 权限回调结果, 回调给Activity
     */
    public void onRequestPermissionsResult(@NonNull Activity activity, int requestCode, String permissions[], int[] grantResults,
                                           @NonNull OnRequestPermissionResult result) {
        switch (requestCode) {
            case TAG_GPS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    result.onRequestSucceedTag(TAG_GPS, "");
                } else {
                    result.onError(TAG_GPS, "您禁止了GPS权限");
                    Toast.makeText(activity, "您禁止了GPS权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case TAG_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestPermissionRWSDCard(activity, result, TAG_CAMERA_RW_SDCARD, true);
                } else {
                    result.onError(TAG_CAMERA, "您禁止了拍照权限");
                    Toast.makeText(activity, "您禁止了拍照权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case TAG_CAMERA_RW_SDCARD:
                if (grantResults.length > 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    result.onRequestSucceedTag(TAG_CAMERA, "");
                } else {
                    result.onError(TAG_CAMERA, "您禁止了读写存储卡权限");
                    Toast.makeText(activity, "您禁止了读写存储卡权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case TAG_RW_SDCARD:
                if (grantResults.length > 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    result.onRequestSucceedTag(TAG_RW_SDCARD, "");
                } else {
                    result.onError(TAG_RW_SDCARD, "您禁止了读写存储卡权限");
                    Toast.makeText(activity, "您禁止了读写存储卡权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case TAG_READ_PHONE_STATE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getIMEI(activity, result);
                } else {
                    result.onError(TAG_READ_PHONE_STATE, "您禁止了获取手机识别码权限");
                    Toast.makeText(activity, "您禁止了获取手机识别码权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void showNotifyDialog(@Nullable final Activity activity, @NonNull final String permission, final int tag,
                                  @NonNull final OnRequestPermissionResult result) {
        final Dialog dialog = new Dialog(activity);

        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_permission_notify, null);
        TextView tvTitle;
        TextView tvConfirm;
        TextView tvCancel;
        tvTitle = (TextView) dialogView.findViewById(R.id.tvTitle);
        tvConfirm = (TextView) dialogView.findViewById(R.id.tvConfirm);
        tvCancel = (TextView) dialogView.findViewById(R.id.tvCancel);

        tvTitle.setText("您没有开启" + permission + "权限,是否去开启?");

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                result.onError(tag, "您没有打开" + permission + "权限");
                openSettingActivity(activity);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                result.onError(tag, "您没有打开" + permission + "权限");
                Toast.makeText(activity, "您没有打开" + permission + "权限", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.setCancelable(true);
        dialog.setContentView(dialogView);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();
    }

    /**
     * 打开权限设置界面 主要针对6.0 以后手机
     */
    private static void openSettingActivity(@NonNull final Activity activity) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Log.d("PermissionUtils", "getPackageName(): " + activity.getPackageName());
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }

    public static boolean selfPermissionGranted(Context context, String permission) {
        // For Android < Android M, self permissions are always granted.
        boolean result = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getTargetSdkVersion(context) >= Build.VERSION_CODES.M) {
                // targetSdkVersion >= Android M, we can
                // use Context#checkSelfPermission
                if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                }
            } else {
                // targetSdkVersion < Android M, we have to use PermissionChecker
                if (PermissionChecker.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED) {
                    result = true;
                }
            }
        }
        return result;
    }

    private static int getTargetSdkVersion(Context context) {
        int version = 0;
        try {
            final PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    /*
     *渠道标志为：
     *1，andriod（a）识别符来源标志：
     *1，wifi mac地址（wifi）；
     *2，IMEI（imei）；
     *3，序列号（sn）；
     *4，id：随机码。若前面的都取不到时，则随机生成一个随机码，需要缓存。
     *@param context
     *@return
     */
    public String getDeviceId(Context context) {
        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        //deviceId.append("a");
        try {
            //wifi mac地址
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if (!isEmpty(wifiMac)) {
                //deviceId.append("wifi");
                deviceId.append(wifiMac);
                Log.e("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return getPesudoUniqueID();
            }
            String imei = tm.getDeviceId();
            if (!isEmpty(imei)) {
                //deviceId.append("imei");
                deviceId.append(imei);
                Log.e("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if (!isEmpty(sn)) {
                //deviceId.append("sn");
                deviceId.append(sn);
                Log.e("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
            //如果上面都没有， 则生成一个id：随机码
            return getPesudoUniqueID();
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append(getPesudoUniqueID());
        }
        return getPesudoUniqueID();
    }

    /**
     * Pseudo-Unique ID, 这个在任何Android手机中都有效
     * 有一些特殊的情况，一些如平板电脑的设置没有通话功能，或者你不愿加入READ_PHONE_STATE许可。而你仍然想获得唯
     * 一序列号之类的东西。这时你可以通过取出ROM版本、制造商、CPU型号、以及其他硬件信息来实现这一点。这样计算出
     * 来的ID不是唯一的（因为如果两个手机应用了同样的硬件以及Rom 镜像）。但应当明白的是，出现类似情况的可能性基
     * 本可以忽略。大多数的Build成员都是字符串形式的，我们只取他们的长度信息。我们取到13个数字，并在前面加上“35
     * ”。这样这个ID看起来就和15位IMEI一样了。
     *
     * @return PesudoUniqueID
     */
    public String getPesudoUniqueID() {
        String m_szDevIDShort = "35"
            +
            //we make this look like a valid IMEI
            Build.BOARD.length() % 10
            + Build.BRAND.length() % 10
            + Build.CPU_ABI.length() % 10
            + Build.DEVICE.length() % 10
            + Build.DISPLAY.length() % 10
            + Build.HOST.length() % 10
            + Build.ID.length() % 10
            + Build.MANUFACTURER.length() % 10
            + Build.MODEL.length() % 10
            + Build.PRODUCT.length() % 10
            + Build.TAGS.length() % 10
            + Build.TYPE.length() % 10
            + Build.USER.length() % 10; //13 digits
        return m_szDevIDShort;
    }
}
