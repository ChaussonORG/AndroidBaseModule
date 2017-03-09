package com.sudao.basemodule.common.util;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Samuel on 5/15/16 19:09
 * Email:xuzhou40@gmail.com
 * desc:toolbar 工具类
 */
public class ToolbarUtil {
    /**
     * 没有返回箭头
     *
     * @param context
     * @param toolbar
     */
    public static void setupHomeToolbar(Context context, Toolbar toolbar) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
    }

    /**
     * 有返回箭头
     *
     * @param context
     * @param toolbar
     */
    public static void setupToolbar(Context context, Toolbar toolbar) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 自定义返回键
     *
     * @param context
     * @param toolbar
     * @param resId
     */
    public static void setupToolbar(Context context, Toolbar toolbar, int resId) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(resId);
        }
    }
}
