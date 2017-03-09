package com.sudao.basemodule.http;

import com.sudao.basemodule.base.BaseApplication;
import com.sudao.basemodule.common.util.AppHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Samuel on 6/8/16 17:06
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class CacheControlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (AppHelper.isNetworkConnected(BaseApplication.instance)) {
            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
        } else {
            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
        }
        return chain.proceed(request);
    }
}
