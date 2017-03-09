package com.sudao.basemodule.base;

import android.os.Environment;

import com.sudao.basemodule.common.util.SPHelper;

import java.io.File;

/**
 * Created by Samuel on 5/11/16 18:49
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class BundleConst {
    public static final String APP_NAME = "SudaotechBasemodule";

    public static final String CHAT_BASE_URL_TEST = "";//聊天测试服务器
    public static final String BASE_URL_TEST = "";//测试环境

    public static final String CHAT_BASE_URL = "";//聊天服务器-正式环境
    public static final String BASE_URL = "";//正式环境

    public static final String BASE_URL_IMAGE = "platform/image";//图片地址
    public static final String BASE_URL_FILE = "platform/file";//文件地址

    public static final String APP_CACHE_NAME = APP_NAME + "_cache";
    public static final String APP_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + APP_NAME + File.separator + "download_file";
    // 第三方登录中accountTypeKey字段
    public static final String ACCOUNTTYPEKEY_QQ = "qq";
    public static final String ACCOUNTTYPEKEY_WEIXIN = "wx";
    public static final String ACCOUNTTYPEKEY_WEIBO = "wb";


    public static String BASE_ENVIRONMENT = "environment";
    public static String BASE_CHAT_ENVIRONMENT = "chatEnvironment";

    public BundleConst() {
    }

    public static String getUrl() {
        String url = SPHelper.getString(BASE_ENVIRONMENT, BASE_URL_TEST);
        return url;
    }

    public static String getChatUrl() {
        String url = SPHelper.getString(BASE_CHAT_ENVIRONMENT, CHAT_BASE_URL);
        return url;
    }

    public static String getImageUrl() {
        String url = SPHelper.getString(BASE_ENVIRONMENT, BASE_URL_TEST) + BASE_URL_IMAGE;
        return url;
    }

    public static String getFileUrl() {
        String url = SPHelper.getString(BASE_ENVIRONMENT, BASE_URL_TEST) + BASE_URL_FILE;
        return url;
    }

}
