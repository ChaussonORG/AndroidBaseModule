package com.sudao.basemodule.component.pickimage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sudao.basemodule.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.sudao.basemodule.component.pickimage.PickImage.CROP_REQUEST;
import static com.sudao.basemodule.component.pickimage.PickImage.PICK_IMAGE_REQUEST;
import static com.sudao.basemodule.component.pickimage.PickImage.REQUEST_IMAGE_CAPTURE;


public class SelectDialogActivity extends AppCompatActivity implements View.OnClickListener {
    private PickImage mSelectPhoto;
    private Button mBtnFromGallery;
    private Button mBtnFromCamera;
    private int mFlag = 0;
    private Uri mPhotoUri;

    /****
     * 通过目录和文件名来获取Uri
     *
     * @param strFileDir  目录
     * @param strFileName 文件名
     * @return Uri
     * @throws IOException IO异常
     */
    public static Uri getUriByFileDirAndFileName(String strFileDir, String strFileName) throws IOException {
        Uri uri = null;
        File fileDir = new File(Environment.getExternalStorageDirectory(), strFileDir);  //定义目录
        if (!fileDir.exists()) {   //判断目录是否存在
            fileDir.mkdirs();      //如果不存在则先创建目录
        }
        File file = new File(fileDir, strFileName);   //定义文件
        if (!file.exists()) {  //判断文件是否存在
            file.createNewFile();    //如果不存在则先创建文件
        }
        uri = Uri.fromFile(file);  //获取Uri
        return uri;
    }

    public static void startPhotoCrop(Activity context, Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);//设置为不返回数据

        context.startActivityForResult(intent, CROP_REQUEST);
    }

    private void assignViews() {
        mBtnFromGallery = (Button) findViewById(R.id.btn_from_gallery);
        mBtnFromCamera = (Button) findViewById(R.id.btn_from_camera);
        mBtnFromGallery.setOnClickListener(this);
        mBtnFromCamera.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dialog);
        assignViews();

        mSelectPhoto = new PickImage(this);

        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String filename = timeStampFormat.format(new Date());

        try {
            mPhotoUri = getUriByFileDirAndFileName("/tempPhoto", filename + "_temp.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == REQUEST_IMAGE_CAPTURE || requestCode == PICK_IMAGE_REQUEST) && resultCode == RESULT_OK) {
            mFlag = requestCode;

            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                startPhotoCrop(this, mPhotoUri, 600);
            } else {
                if (data != null) {
                    mPhotoUri = data.getData();
                    startPhotoCrop(this, data.getData(), 600);
                }
            }

        } else if (requestCode == CROP_REQUEST && resultCode == RESULT_OK) {
//            Bitmap bitmap = mSelectPhoto.handleBitmap(mFlag, resultCode, data);
            Bitmap bitmap = getBitmapFromUri(this, mPhotoUri);

            if (bitmap != null) {
                PickImage.mOnPickImageListener.onSuccess(bitmap, getIntent().getIntExtra("flag", 0));
            }
            finish();
        }

    }

    public Bitmap getBitmapFromUri(Context context, Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //拍照选择照片
    public void selectPhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//        intent.putExtra("camerasensortype", 2);// 调用前置摄像头
        intent.putExtra("autofocus", true);// 自动对焦
        intent.putExtra("fullScreen", true);// 全屏
        intent.putExtra("showActionIcons", false);
        // 指定调用相机拍照后照片的储存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //从图库中选择照片
    public void selectPhotoFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_from_camera) {
            selectPhotoFromCamera();
        } else if (id == R.id.btn_from_gallery) {
            selectPhotoFromGallery();
        }

    }
}
