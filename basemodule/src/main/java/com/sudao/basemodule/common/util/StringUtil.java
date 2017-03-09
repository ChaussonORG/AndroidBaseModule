package com.sudao.basemodule.common.util;

import android.os.Environment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Samuel on 5/11/16 18:02
 * Email:xuzhou40@gmail.com
 * desc:字符串工具类
 */
public class StringUtil {
    public static final String SD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String SD_DOWNLOAD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();


    //校检手机号码
    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^1[0-9]{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }


    public static String getFileName(String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        return fileName;
    }

    public static boolean isPictureFile(String string) {
        String regex = "^.*?\\.(png|jpg)$";
        if (string.matches(regex)) {
            return true;
        } else
            return false;
    }

    public static boolean isPdfFile(String string) {
        String regex = "^.*?\\.(pdf)$";
        if (string.matches(regex)) {
            return true;
        } else
            return false;
    }

    public static boolean checkPassword(String password) {
        //6～16位数,只支持字母、数字和下划线
        String regex = "^[0-9a-zA-Z_]{6,16}$";
        if (password.matches(regex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (s == null || s.trim().length() == 0);
    }
}
