package com.sudao.basemodule.component.pickimage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Samuel on 5/23/16 15:34
 * Email:xuzhou40@gmail.com
 * desc:选择照片类
 */
public class PickImage {
    /**
     * Standard activity result: operation canceled.
     */
    public static final int RESULT_CANCELED = 0;
    /**
     * Standard activity result: operation succeeded.
     */
    public static final int REQUEST_IMAGE_CAPTURE = 1;//拍照选择照片
    public static final int PICK_IMAGE_REQUEST = 2;//从图库中选择照片
    public static final int CROP_REQUEST = 3;//裁剪照片
    public static OnPickImageListener mOnPickImageListener;
    private Context mContext;
    private Bitmap mBitmap;
    private boolean mCrop = false;

    public PickImage(Context context) {
        mContext = context;
//        EventBus.getDefault().register(this);
    }

    //压缩图片
    private static Bitmap CompressBitmap(Bitmap image) {
        int width = image.getWidth();
        int height = image.getHeight();
        float scale = 500.0f / height;
        if (width > 500 || height > 500) {
            width = Math.round(width * scale);
            height = Math.round(height * scale);
        }
        return Bitmap.createScaledBitmap(image, width, height, false);
    }

    //从数据库中读取照片方向
    private static int getOrientation(Context context, Uri photoUri) {
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

    //解决部分机型，从图库中选择照片旋转90度的问题
    //选择图片90度
    private static Bitmap rotate(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree); /*翻转90度*/
        int width = img.getWidth();
        int height = img.getHeight();
        img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
        return img;
    }

    public void setOnPickImageListener(OnPickImageListener onPickImageListener) {
        mOnPickImageListener = onPickImageListener;
    }

    public void open() {
        mContext.startActivity(new Intent(mContext, SelectDialogActivity.class));
//        showChoosePhotoDialog();
    }

    /**
     * @param flagCode this code will be returned in onSuccess().
     */
    public void open(int flagCode) {
        Intent intent = new Intent(mContext, SelectDialogActivity.class);
        intent.putExtra("flag", flagCode);
        mContext.startActivity(intent);
//        showChoosePhotoDialog();
    }

    private void showChoosePhotoDialog() {
        String[] items = {"从系统相册中选择照片", "拍照"};
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
//                        selectPhotoFromGallery();
                        break;
                    case 1:
//                        selectPhotoFromCamera(null);
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }


        });
        builder.show();
    }


    public Bitmap handleBitmap(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = null;
        /*if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {//拍照
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            bitmap = CompressBitmap(bitmap);

//            mOnPickImageListener.onSuccess(bitmap);
        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {//从相册中选择照片
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
                int orientation = getOrientation(mContext, uri);
                bitmap = CompressBitmap(bitmap);
                bitmap = rotate(bitmap, orientation);

//                mOnPickImageListener.onSuccess(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        if (resultCode == RESULT_OK) {
//            Uri imageUri = data.getData();

            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            bitmap = CompressBitmap(bitmap);
//            bitmap = rotate(bitmap, orientation);
        }
        return bitmap;
    }

    /**
     * 把图片转化为文件
     *
     * @param context
     * @param sourceImg
     * @return
     */
    public File getFile(Context context, Bitmap sourceImg) {
        try {
            File file = new File(context.getCacheDir(), System.currentTimeMillis() + "_temp.jpg");
            Log.d("filePath = ", file.getAbsolutePath());
            file.createNewFile();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int options = 100;
            sourceImg.compress(Bitmap.CompressFormat.JPEG, options, bos);
            while (bos.toByteArray().length / 1024 > 100) {
                bos.reset();
                options -= 10;
                sourceImg.compress(Bitmap.CompressFormat.JPEG, options, bos);
            }
            byte[] bitmapdata = bos.toByteArray();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface OnPickImageListener {
        void onSuccess(Bitmap bitmap, int flagCode);

        void onFail();

        void onCancel();
    }


}
