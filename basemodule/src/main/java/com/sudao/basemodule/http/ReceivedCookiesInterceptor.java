package com.sudao.basemodule.http;


import com.sudao.basemodule.base.BaseApplication;
import com.sudao.basemodule.common.util.SPHelper;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Samuel on 5/3/16 15:34
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class ReceivedCookiesInterceptor implements Interceptor {
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


        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            SPHelper.putStringSet(BaseApplication.instance, "cookie", cookies);
        }

        return originalResponse;
    }
}
