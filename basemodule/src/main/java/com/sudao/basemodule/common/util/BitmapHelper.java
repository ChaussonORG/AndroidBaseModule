package com.sudao.basemodule.common.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图像处理帮助类
 *
 * @author xiaolong
 */
public class BitmapHelper {

    /**
     * @param sourceImg
     * @param number    number的范围是0-100，0表示完全透明即完全看不到
     * @return
     */
    public static Bitmap getTransparentBitmap(Bitmap sourceImg, int number) {
        int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];
        sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0,
                sourceImg.getWidth(), sourceImg.getHeight());// 获得图片的ARGB值
        number = number * 255 / 100;

        for (int i = 0; i < argb.length; i++) {
            argb[i] = (number << 24) | (argb[i] & 0x00FFFFFF);
        }
        sourceImg = Bitmap.createBitmap(argb, sourceImg.getWidth(),
                sourceImg.getHeight(), Config.ARGB_8888);
        return sourceImg;
    }

    /**
     * 把图片转化为文件
     *
     * @param context
     * @param sourceImg
     * @return
     */
    public static File getFile(Context context, Bitmap sourceImg) {
        try {
            File f = new File(context.getCacheDir(), System.currentTimeMillis()
                    + "temp.jpg");
            f.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int options = 100;
            sourceImg.compress(Bitmap.CompressFormat.JPEG, options, bos);
            while (bos.toByteArray().length / 1024 > 100) {
                bos.reset();
                options -= 10;
                sourceImg.compress(Bitmap.CompressFormat.JPEG, options, bos);
            }
            byte[] bitmapdata = bos.toByteArray();
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return f;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //解决部分机型，从图库中选择照片旋转90度的问题
    //选择图片90度
    public static Bitmap rotate(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree); /*翻转90度*/
        int width = img.getWidth();
        int height = img.getHeight();
        img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
        return img;
    }

    /**
     * 读取照片exif信息中的旋转角度
     *
     * @param path 照片路径
     * @return角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;

        File imageFile = new File(path);

        try {
//            ExifInterface exifInterface = new ExifInterface(path);
            ExifInterface exifInterface = new ExifInterface(imageFile.getAbsolutePath());
            LogUtil.d("exif = " + exifInterface.toString());

            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            LogUtil.d("orientation = " + orientation);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.d("degree = " + degree);
        return degree;
    }

    //压缩图片
    public static Bitmap CompressBitmap(Bitmap image) {
        int width = image.getWidth();
        int height = image.getHeight();
        float scale = 500.0f / height;
        if (width > 500 || height > 500) {
            width = Math.round(width * scale);
            height = Math.round(height * scale);
        }
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        return scaledBitmap;
    }

    //从数据库中读取照片方向
    public static int getOrientation(Context context, Uri photoUri) {
        int orientation = 0;
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[]{MediaStore.Images.ImageColumns.ORIENTATION}, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() != 1) {
                return -1;
            }
            cursor.moveToFirst();
            orientation = cursor.getInt(0);
            cursor.close();
        }
        return orientation;
    }


}
