package com.sudao.basemodule.component.file;

import android.content.Context;

import com.sudao.basemodule.R;
import com.sudao.basemodule.basicapi.BaseService;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.http.CommonCallback;
import com.sudao.basemodule.http.HTTPHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Samuel on 16/6/21 16:26
 * Email:xuzhou40@gmail.com
 * desc:图片上传(支持多图片)
 */
public class FileUploader {
    private Context mContext;
    private OnUploadListener mOnUploadListener;

    public FileUploader(Context context) {
        mContext = context;
    }

    public void setOnUploadListener(OnUploadListener onUploadListener) {
        mOnUploadListener = onUploadListener;
    }

    public void startUploadImages(List<File> fileList) {
        BaseService service = HTTPHelper.getBaseService();
        List<MultipartBody.Part> partList = new ArrayList<>();


        for (File file : fileList) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("filename", file.getName(), requestFile);
            partList.add(part);
        }

        Call<UploadResponse> call = service.uploadImages(partList);
        call.enqueue(new CommonCallback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                super.onResponse(call, response);
                UploadResponse body = response.body();
                if (body != null) {
                    if (body.getCode().equals("200")) {
                        mOnUploadListener.onUploadComplete(body.getData());
                    } else {
                        mOnUploadListener.onUploadFailure(body.getCode(), body.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                super.onFailure(call, t);
                ToastHelper.showToast(mContext, R.string.hint_net_error);
            }
        });

    }

    public void startUploadFiles(List<File> fileList) {
        BaseService service = HTTPHelper.getBaseService();
        List<MultipartBody.Part> partList = new ArrayList<>();


        for (File file : fileList) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("filename", file.getName(), requestFile);
            partList.add(part);
        }

        Call<UploadResponse> call = service.uploadFiles(partList);
        call.enqueue(new CommonCallback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                super.onResponse(call, response);
                UploadResponse body = response.body();
                if (body != null) {
                    if (body.getCode().equals("200")) {
                        mOnUploadListener.onUploadComplete(body.getData());
                    } else {
                        mOnUploadListener.onUploadFailure(body.getCode(), body.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                super.onFailure(call, t);
                ToastHelper.showToast(mContext, R.string.hint_net_error);
            }
        });

    }
}
