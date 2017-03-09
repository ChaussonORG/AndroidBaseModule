package com.sudao.basemodule.http;

import android.os.Environment;

import com.sudao.basemodule.base.BundleConst;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Samuel on 5/23/16 19:03
 * Email:xuzhou40@gmail.com
 * desc: service生成类
 */
public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BundleConst.getUrl() + "platform/")
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        httpClient.addInterceptor(new AddCookiesInterceptor());
        httpClient.addInterceptor(new ReceivedCookiesInterceptor());
        httpClient.addInterceptor(new LoggingInterceptor());
//        httpClient.addInterceptor(new CacheControlInterceptor());
        File cacheFile = new File(Environment.getExternalStorageDirectory() + File.separator + BundleConst.APP_NAME, "net_cache");
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        Cache cache = new Cache(cacheFile, 10 * 100 * 100);

        httpClient.cache(cache);
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
