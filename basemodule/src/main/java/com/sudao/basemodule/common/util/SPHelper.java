package com.sudao.basemodule.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.sudao.basemodule.base.BaseApplication;
import com.sudao.basemodule.base.BundleConst;

import java.util.Set;

/**
 * @Description: (SharedPreferences工具类)
 **/
public class SPHelper {

    public static final String KEY_USER = "KEY_USER";
    public static final String KEY_COOKIE = "KEY_COOKIE";
    private static String FILE_NAME = BundleConst.APP_NAME + "_data";

    /**
     * 获取SharedPreferences实例对象
     *
     * @return
     */
    public static SharedPreferences getSharedPreference() {
//        return BaseApplication.instance.getSharedPreferences(FILE_NAME,
        return BaseApplication.getContext().getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
    }

    /**
     * 保存一个Boolean类型的值！
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean putBoolean(String key, Boolean value) {
        SharedPreferences sharedPreference = getSharedPreference();
        Editor editor = sharedPreference.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 保存一个int类型的值！
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean putInt(String key, int value) {
        SharedPreferences sharedPreference = getSharedPreference();
        Editor editor = sharedPreference.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 保存一个float类型的值！
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean putFloat(String key, float value) {
        SharedPreferences sharedPreference = getSharedPreference();
        Editor editor = sharedPreference.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 保存一个long类型的值！
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean putLong(String key, long value) {
        SharedPreferences sharedPreference = getSharedPreference();
        Editor editor = sharedPreference.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 保存一个String类型的值！
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean putString(String key, String value) {
        SharedPreferences sharedPreference = getSharedPreference();
        Editor editor = sharedPreference.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean putObject(String key, Object value) {
        SharedPreferences sharedPreference = getSharedPreference();
        Editor editor = sharedPreference.edit();
        try {
            if (value != null) {
                editor.putString(key, GsonHelper.toJson(value));
            } else {
                editor.putString(key, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return editor.commit();
    }

    /**
     * 获取String的value
     *
     * @param key      名字
     * @param defValue 默认值
     * @return
     */
    public static String getString(String key, String defValue) {
        SharedPreferences sharedPreference = getSharedPreference();
        return sharedPreference.getString(key, defValue);
    }

    /**
     * 获取int的value
     *
     * @param key      名字
     * @param defValue 默认值
     * @return
     */
    public static int getInt(String key, int defValue) {
        SharedPreferences sharedPreference = getSharedPreference();
        return sharedPreference.getInt(key, defValue);
    }

    /**
     * 获取float的value
     *
     * @param key      名字
     * @param defValue 默认值
     * @return
     */
    public static float getFloat(String key, Float defValue) {
        SharedPreferences sharedPreference = getSharedPreference();
        return sharedPreference.getFloat(key, defValue);
    }

    /**
     * 获取boolean的value
     *
     * @param key      名字
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(String key, Boolean defValue) {
        SharedPreferences sharedPreference = getSharedPreference();
        return sharedPreference.getBoolean(key, defValue);
    }

    /**
     * 获取long的value
     *
     * @param key      名字
     * @param defValue 默认值
     * @return
     */
    public static long getLong(String key, long defValue) {
        SharedPreferences sharedPreference = getSharedPreference();
        return sharedPreference.getLong(key, defValue);
    }

    public static <T> T getObject(String key, Class<T> clazz) {
        SharedPreferences sharedPreference = getSharedPreference();
        String value = sharedPreference.getString(key, "");
        try {
            if (TextUtils.isEmpty(value)) {
                return null;
            } else {
                return GsonHelper.fromJson(value, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Set<String> getStringSet(Context context, String key, Set<String> def) {
        SharedPreferences sharedPreference = getSharedPreference();
//        SharedPreferences sharedPreference = getSharedPreference();
        return sharedPreference.getStringSet(key, def);
    }

    public static boolean putStringSet(Context context, String key, Set<String> stringSet) {
        SharedPreferences sharedPreference = getSharedPreference();
//        SharedPreferences sharedPreference = getSharedPreference();
        Editor editor = sharedPreference.edit();
        editor.putStringSet(key, stringSet);
        return editor.commit();
    }

}