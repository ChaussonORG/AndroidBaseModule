package com.sudao.basemodule.http;

import android.util.Log;

import com.sudao.basemodule.base.BaseApplication;
import com.sudao.basemodule.common.util.SPHelper;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Samuel on 5/3/16 15:07
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        /*Request request = chain.request();
        long t1 = System.nanoTime();
        LogUtil.d("info", String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        Response s = chain.proceed(request);
//                    s.header("Content-Type","application/json");
        long t2 = System.nanoTime();
        LogUtil.d("info", String.format("Received response for %s in %.1fms%n%s%n ",
                s.request().url(), (t2 - t1) / 1e6d, s.headers()));*/


        Request.Builder builder = chain.request().newBuilder();

        HashSet<String> preferences = (HashSet) SPHelper.getStringSet(BaseApplication.instance, "cookie", new HashSet<String>());

        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
            Log.d("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
        }

        return chain.proceed(builder.build());
    }
}
