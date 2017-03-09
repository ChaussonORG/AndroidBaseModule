package com.sudao.basemodule.common.util;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;
import com.sudao.basemodule.base.BundleConst;

import java.io.File;

/**
 * Created by Samuel on 6/8/16 16:13
 * Email:xuzhou40@gmail.com
 * desc:处理Glide缓存
 */
public class CustomGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                File cacheLocation = new File(Environment.getExternalStorageDirectory() + File.separator + BundleConst.APP_NAME, "image");
                if (!cacheLocation.exists()) {
                    cacheLocation.mkdirs();
                }
                return DiskLruCacheWrapper.get(cacheLocation, 50 * 1024 * 1024);
            }
        });

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
