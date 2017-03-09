package com.sudao.basemodule.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


/**
 * Created by Samuel on 5/25/16 17:44
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class BaseApplication extends Application {
    public static BaseApplication instance;

    public static Context getContext() {
        return instance;
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
