package com.sudao.basemodule.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.sudao.basemodule.R;

import java.io.File;

/**
 * Created by Samuel on 6/8/16 13:37
 * Email:xuzhou40@gmail.com
 * desc:封装Glide
 */
public class ImageHelper {
    /**
     * 加载网络普通图片
     *
     * @param context
     * @param imageUrl  图片链接
     * @param imageView
     */
    public static void load(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.ic_default_ecoc)
                .error(R.drawable.ic_default_ecoc)
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 加载网络普通图片,设置占位图
     *
     * @param context
     * @param imageUrl
     * @param placeHolderId
     * @param imageView
     */
    public static void load(Context context, String imageUrl, int placeHolderId, ImageView imageView) {
        Glide.with(context).load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(placeHolderId)
                .error(placeHolderId)
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 加载网络图片，设置加载完毕的监听
     *
     * @param context
     * @param imageUrl
     * @param glideDrawableImageViewTarget
     */
    public static void load(Context context, String imageUrl, GlideDrawableImageViewTarget glideDrawableImageViewTarget) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.ic_default_ecoc)
                .error(R.drawable.ic_default_ecoc)
                .into(glideDrawableImageViewTarget);
//                .into(new GlideDrawableImageViewTarget(imageView) {
//                    @Override
//                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                        super.onResourceReady(drawable, anim);
//                        //在这里添加一些图片加载完成的操作
//
//
//                    }
//
//
//                });
    }

    /**
     * 加载本地图片，设置加载完毕的监听
     *
     * @param context
     * @param file
     * @param glideDrawableImageViewTarget
     */
    public static void load(Context context, File file, GlideDrawableImageViewTarget glideDrawableImageViewTarget) {
        Glide.with(context)
                .load(file)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.ic_default_ecoc)
                .error(R.drawable.ic_default_ecoc)
                .into(glideDrawableImageViewTarget);
//                .into(new GlideDrawableImageViewTarget(imageView) {
//                    @Override
//                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
//                        super.onResourceReady(drawable, anim);
//                        //在这里添加一些图片加载完成的操作
//
//
//                    }
//
//
//                });
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param file      本地图片
     * @param imageView
     */
    public static void load(Context context, File file, ImageView imageView) {
        Glide.with(context).load(file)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.ic_default_ecoc)
                .error(R.drawable.ic_default_ecoc)
                .into(imageView);
    }

    /**
     * 加载圆角矩形图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     * @param radius
     */
    public static void loadRound(Context context, String imageUrl, ImageView imageView,
                                 int radius) {
        Glide.with(context).load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.ic_default_ecoc)
                .error(R.drawable.ic_default_ecoc)
                .centerCrop()
                .dontAnimate()
                .transform(new GlideRoundTransform(context, radius))
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadCircle(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.ic_default_ecoc)
                .error(R.drawable.ic_default_ecoc)
                .centerCrop()
                .dontAnimate()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

}
