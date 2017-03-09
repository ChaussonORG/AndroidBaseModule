package com.sudao.basemodule.common.util;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;

/**
 * 友盟分享工具类
 */
public class UMShareHelper {

    /*
    图片(url)
    UMImage image = new UMImage(ShareActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");

    图片(本地资源引用)
    UMImage image = new UMImage(ShareActivity.this,
            BitmapFactory.decodeResource(getResources(), R.drawable.image));

    图片(本地绝对路径)
    UMImage image = new UMImage(ShareActivity.this,
            BitmapFactory.decodeFile("/mnt/sdcard/icon.png")));

    URL音频及图片
    UMusic music = new UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
    music.setTitle("sdasdasd");
    music.setThumb(new UMImage(ShareActivity.this,"http://www.umeng.com/images/pic/social/chart_1.png"));

    url视频
    UMVideo video = new UMVideo("http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html");*/


    //设置需要分享的平台
    public static SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]{
            SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.SINA
    };

    /**
     * 分享图片
     *
     * @param activity
     * @param umShareListener
     * @param image
     * @param text
     * @param title
     * @param targetUrl
     */
    public static void shareImage(Activity activity, UMShareListener umShareListener, UMImage image, String text, String title, String targetUrl) {

        new ShareAction(activity).setDisplayList(displaylist)
                .setCallback(umShareListener)
                .withMedia(image)
                .withText(text)
                .withTitle(title)
                .withTargetUrl(targetUrl)
                .open();
    }

    /**
     * 分享音乐
     *
     * @param activity
     * @param umShareListener
     * @param music
     * @param text
     * @param title
     * @param targetUrl
     */
    public static void shareMusic(Activity activity, UMShareListener umShareListener, UMusic music, String text, String title, String targetUrl) {

        new ShareAction(activity).setDisplayList(displaylist)
                .setCallback(umShareListener)
                .withMedia(music)
                .withText(text)
                .withTitle(title)
                .withTargetUrl(targetUrl)
                .open();
    }

    /**
     * 分享视频
     *
     * @param activity
     * @param umShareListener
     * @param video
     * @param text
     * @param title
     * @param targetUrl
     */
    public static void shareVideo(Activity activity, UMShareListener umShareListener, UMVideo video, String text, String title, String targetUrl) {

        new ShareAction(activity).setDisplayList(displaylist)
                .setCallback(umShareListener)
                .withMedia(video)
                .withText(text)
                .withTitle(title)
                .withTargetUrl(targetUrl)
                .open();
    }

    /**
     * 自定义分享
     *
     * @param context
     * @param type            分享平台的类型。微信 0，朋友圈 1，QQ 2，微博 3
     * @param umShareListener
     * @param title
     * @param text
     * @param targetUrl
     */
    public static void customShare(AppCompatActivity context, int type, UMShareListener umShareListener, String title, String text, String targetUrl, String imageurl) {

        UMImage image = new UMImage(context, imageurl);

        SHARE_MEDIA platform = SHARE_MEDIA.WEIXIN;
        if (type == 0) {
            platform = SHARE_MEDIA.WEIXIN;
        } else if (type == 1) {
            platform = SHARE_MEDIA.WEIXIN_CIRCLE;
        } else if (type == 2) {
            platform = SHARE_MEDIA.QQ;
        } else if (type == 3) {
            platform = SHARE_MEDIA.SINA;
        }
        new ShareAction(context)
                .setPlatform(platform)
                .setCallback(umShareListener)
                .withMedia(image)
                .withTitle(title)
                .withText(text)
                .withTargetUrl(targetUrl)
                .share();
    }

}
