package com.dy.cmls.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */


public class NetworkUtil {
    public static final int SIGNAL_STRENGTH_NONE_OR_UNKNOWN = 0;
    public static final int SIGNAL_STRENGTH_POOR = 1;
    public static final int SIGNAL_STRENGTH_MODERATE = 2;
    public static final int SIGNAL_STRENGTH_GOOD = 3;
    public static final int SIGNAL_STRENGTH_GREAT = 4;

    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetAvailable(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context
                    .CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取网络的信息
     *
     * @param context
     * @param signalStrength
     */
    public static void getNetWorkInfo(Context context, SignalStrength signalStrength) {
        int level = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            switch (info.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    //wifi
                    level = getLevel(context, signalStrength, ConnectivityManager.TYPE_WIFI);
                    //                    ToastUtil.showToast(context, "当前为wifi网络，信号强度=" + level);
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    level = getLevel(context, signalStrength, ConnectivityManager.TYPE_MOBILE);
                    //移动网络,可以通过TelephonyManager来获取具体细化的网络类型
                    //                    String netWorkStatus = getetNetworkType(context);
                    //                    ToastUtil.showToast(context, "当前为" + netWorkStatus + ",
                    // 信号level=" + level);
                    break;
                default:
                    break;
            }
            if (level <= 2 || "2G".equalsIgnoreCase(getetNetworkType(context))) {
                Toast.makeText(context, "当前为" + getetNetworkType(context) + "网络,信号较弱，建议更换网络！", Toast
                        .LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "没有可用网络", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Get signal level as an int from 0..4
     *
     * @hide
     */
    public static int getLevel(Context context, SignalStrength signalStrength, int netType) {
        int level = 0;
        if (netType == 1) {//wifi
            WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo connectionInfo = manager.getConnectionInfo();
            int rssi = connectionInfo.getRssi();
            if (rssi >= -50) {
                level = SIGNAL_STRENGTH_GREAT;
            } else if (rssi >= -70) {
                level = SIGNAL_STRENGTH_GOOD;
            } else if (rssi >= -70) {
                level = SIGNAL_STRENGTH_MODERATE;
            } else if (rssi >= -100) {
                level = SIGNAL_STRENGTH_POOR;
            } else {
                level = SIGNAL_STRENGTH_NONE_OR_UNKNOWN;
            }

        } else if (netType == 0) {//移动网络
            if (signalStrength == null) {
                return 0;
            }

            String signalInfo = signalStrength.toString();
            String[] params = signalInfo.split(" ");
            int dbm = 0;
            //4G网络 最佳范围   >-90dBm 越大越好
            if ("4G".equalsIgnoreCase(getetNetworkType(context))) {
                dbm = Integer.parseInt(params[9]);
            } else if ("3G".equalsIgnoreCase(getetNetworkType(context))) {
                //3G网络最佳范围  >-90dBm  越大越好  ps:中国移动3G获取不到  返回的无效dbm值是正数（85dbm）
                int cdmaLevel = getCdmaLevel(signalStrength);
                int evdoLevel = getEvdoLevel(signalStrength);
                if (evdoLevel == SIGNAL_STRENGTH_NONE_OR_UNKNOWN) {
                /* We don't know evdo, use cdma */
                    level = getCdmaLevel(signalStrength);
                } else if (cdmaLevel == SIGNAL_STRENGTH_NONE_OR_UNKNOWN) {
                /* We don't know cdma, use evdo */
                    level = getEvdoLevel(signalStrength);
                } else {
                /* We know both, use the lowest level */
                    level = cdmaLevel < evdoLevel ? cdmaLevel : evdoLevel;
                }
                return level;
            } else {
                //2G网络最佳范围>-90dBm 越大越好
                int asu = signalStrength.getGsmSignalStrength();
                dbm = -113 + 2 * asu;
            }

            // Ec/Io are in dB*10
            if (dbm >= -90) {
                level = SIGNAL_STRENGTH_GREAT;
            } else if (dbm >= -110) {
                level = SIGNAL_STRENGTH_GOOD;
            } else if (dbm >= -130) {
                level = SIGNAL_STRENGTH_MODERATE;
            } else if (dbm >= -150) {
                level = SIGNAL_STRENGTH_POOR;
            } else {
                level = SIGNAL_STRENGTH_NONE_OR_UNKNOWN;
            }
        }

        return level;
    }

    public static int getGsmLevel(SignalStrength signalStrength) {
        int level;
        // ASU ranges from 0 to 31 - TS 27.007 Sec 8.5
        // asu = 0 (-113dB or less) is very weak
        // signal, its better to show 0 bars to the user in such cases.
        // asu = 99 is a special case, where the signal strength is unknown.
        int asu = signalStrength.getGsmSignalStrength();
        if (asu <= 2 || asu == 99) {
            level = SIGNAL_STRENGTH_NONE_OR_UNKNOWN;
        } else if (asu >= 12) {
            level = SIGNAL_STRENGTH_GREAT;
        } else if (asu >= 8) {
            level = SIGNAL_STRENGTH_GOOD;
        } else if (asu >= 5) {
            level = SIGNAL_STRENGTH_MODERATE;
        } else {
            level = SIGNAL_STRENGTH_POOR;
        }
        return level;
    }

    /**
     * Get cdma as level 0..4
     */
    private static int getCdmaLevel(SignalStrength signalStrength) {
        final int cdmaDbm = signalStrength.getCdmaDbm();
        final int cdmaEcio = signalStrength.getCdmaEcio();
        int levelDbm;
        int levelEcio;

        if (cdmaDbm >= -75) {
            levelDbm = SIGNAL_STRENGTH_GREAT;
        } else if (cdmaDbm >= -85) {
            levelDbm = SIGNAL_STRENGTH_GOOD;
        } else if (cdmaDbm >= -95) {
            levelDbm = SIGNAL_STRENGTH_MODERATE;
        } else if (cdmaDbm >= -100) {
            levelDbm = SIGNAL_STRENGTH_POOR;
        } else {
            levelDbm = SIGNAL_STRENGTH_NONE_OR_UNKNOWN;
        }

        // Ec/Io are in dB*10
        if (cdmaEcio >= -90) {
            levelEcio = SIGNAL_STRENGTH_GREAT;
        } else if (cdmaEcio >= -110) {
            levelEcio = SIGNAL_STRENGTH_GOOD;
        } else if (cdmaEcio >= -130) {
            levelEcio = SIGNAL_STRENGTH_MODERATE;
        } else if (cdmaEcio >= -150) {
            levelEcio = SIGNAL_STRENGTH_POOR;
        } else {
            levelEcio = SIGNAL_STRENGTH_NONE_OR_UNKNOWN;
        }

        int level = (levelDbm < levelEcio) ? levelDbm : levelEcio;
        return level;
    }

    /**
     * Get Evdo as level 0..4
     */
    private static int getEvdoLevel(SignalStrength signalStrength) {
        int evdoDbm = signalStrength.getEvdoDbm();
        int evdoSnr = signalStrength.getEvdoSnr();
        int levelEvdoDbm;
        int levelEvdoSnr;

        if (evdoDbm >= -65) {
            levelEvdoDbm = SIGNAL_STRENGTH_GREAT;
        } else if (evdoDbm >= -75) {
            levelEvdoDbm = SIGNAL_STRENGTH_GOOD;
        } else if (evdoDbm >= -90) {
            levelEvdoDbm = SIGNAL_STRENGTH_MODERATE;
        } else if (evdoDbm >= -105) {
            levelEvdoDbm = SIGNAL_STRENGTH_POOR;
        } else {
            levelEvdoDbm = SIGNAL_STRENGTH_NONE_OR_UNKNOWN;
        }

        if (evdoSnr >= 7) {
            levelEvdoSnr = SIGNAL_STRENGTH_GREAT;
        } else if (evdoSnr >= 5) {
            levelEvdoSnr = SIGNAL_STRENGTH_GOOD;
        } else if (evdoSnr >= 3) {
            levelEvdoSnr = SIGNAL_STRENGTH_MODERATE;
        } else if (evdoSnr >= 1) {
            levelEvdoSnr = SIGNAL_STRENGTH_POOR;
        } else {
            levelEvdoSnr = SIGNAL_STRENGTH_NONE_OR_UNKNOWN;
        }

        int level = (levelEvdoDbm < levelEvdoSnr) ? levelEvdoDbm : levelEvdoSnr;
        return level;
    }

    public static String getetNetworkType(Context context) {
        String strNetworkType = "";

        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String strSubTypeName = networkInfo.getSubtypeName();

                Log.e("cocos2d-x", "Network getSubtypeName : " + strSubTypeName);

                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if ("TD-SCDMA".equalsIgnoreCase(strSubTypeName) || "WCDMA".equalsIgnoreCase
                                (strSubTypeName) || "CDMA2000".equalsIgnoreCase(strSubTypeName)) {
                            strNetworkType = "3G";
                        } else {
                            strNetworkType = strSubTypeName;
                        }
                        break;
                }
            }
        }
        return strNetworkType;
    }
}
