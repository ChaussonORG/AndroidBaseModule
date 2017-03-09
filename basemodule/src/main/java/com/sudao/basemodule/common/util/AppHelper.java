package com.sudao.basemodule.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.umeng.analytics.MobclickAgent;


public class AppHelper {

    public static String DEVICE_ID;
    public static long exitTime = 0;
    public static long beepTime = 0;
    public static String VERSION_NAME;

    /**
     * 获取设备ID
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        if (TextUtils.isEmpty(DEVICE_ID)) {
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            DEVICE_ID = tm.getDeviceId();
        }
        return DEVICE_ID;
    }

    /**
     * 获取App版本名
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        if (TextUtils.isEmpty(VERSION_NAME)) {
            PackageManager manager = context.getPackageManager();
            try {
                PackageInfo info = manager.getPackageInfo(
                        context.getPackageName(), 0);
                VERSION_NAME = info.versionName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return VERSION_NAME;
    }

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 调用系统提示音
     *
     * @param context
     */
    public static void ring(Context context) {
        if ((System.currentTimeMillis() - beepTime) > 6 * 1000) {
            Uri notification = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(
                    context.getApplicationContext(), notification);
            r.play();
            beepTime = System.currentTimeMillis();
        } else {
            beepTime = 0;
        }

    }

    /**
     * 调用系统提示音
     *
     * @param context
     */
    public static void vibration(Context context) {
        Vibrator vibrator = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }

    /**
     * 双击退出
     */
    public static void exitBy2Click(Context context) {

        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastHelper.showToast(context, "再按一次退出应用");
            exitTime = System.currentTimeMillis();
        } else {
            MobclickAgent.onKillProcess(context);
            AppManager.getAppManager().AppExit(context);
        }

    }

}
