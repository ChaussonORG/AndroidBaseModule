package com.sudao.basemodule.component.file;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import com.sudao.basemodule.R;
import com.sudao.basemodule.common.util.LogUtil;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.http.HTTPHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Samuel on 5/28/16 13:29
 * Email:xuzhou40@gmail.com
 * desc:文件下载
 */
public class FileDownloader {
    public OnDownloadListener mOnDownloadListener;
    private Context mContext;
    private String mFileUrl;
    private String mFileName;
    private String mSavePath;
    private Call<ResponseBody> mCall;
    private DownloadAsyncTask mDownloadAsyncTask;

    public FileDownloader(Context context) {
        mContext = context;
    }

    public void setOnDownloadListener(OnDownloadListener onDownloadListener) {
        mOnDownloadListener = onDownloadListener;
    }

    public void onStartDownload(String fileUrl, String fileName, String savePath) {
        mFileUrl = fileUrl;
        mFileName = fileName;
        mSavePath = formatSavePath(savePath);
//        mSavePath = Environment.getDownloadCacheDirectory().getAbsolutePath() + "/";

        if (!mFileUrl.isEmpty()) {

            mCall = HTTPHelper.getBaseService().downloadFile(mFileUrl);
            mCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                    LogUtil.d("响应成功");
                    if (response.isSuccessful()) {

                        ResponseBody body = response.body();
                        mDownloadAsyncTask = new DownloadAsyncTask();
                        mDownloadAsyncTask.execute(body);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    ToastHelper.showToast(mContext, R.string.hint_net_error);
                }
            });
        } else {
            ToastHelper.showToast(mContext, "网络错误，请稍候重试");
        }

    }

    public void onStop() {
        if (mCall != null) {
            mCall.cancel();
        }
        if (mDownloadAsyncTask != null) {
            mDownloadAsyncTask.cancel(true);
        }

    }

    private String formatSavePath(String path) {
        if (path.isEmpty()) {
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        } else {
            if (!path.endsWith("/")) {
                path = path + "/";
            }
        }
        return path;
    }


    private class DownloadAsyncTask extends AsyncTask<ResponseBody, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(ResponseBody... params) {
            ResponseBody body = params[0];
            try {
                File futureStudioIconFile = new File(mSavePath + "temp_" + mFileName);
                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    byte[] fileReader = new byte[4096];

                    long fileSize = body.contentLength();
                    long fileSizeDownloaded = 0;

                    inputStream = body.byteStream();
                    outputStream = new FileOutputStream(futureStudioIconFile);

                    while (true) {
                        int read = inputStream.read(fileReader);

                        if (read == -1) {
                            break;
                        }

                        outputStream.write(fileReader, 0, read);

                        fileSizeDownloaded += read;

                        int progress = (int) ((fileSizeDownloaded * 100) / fileSize);
                        publishProgress(progress);

                    }
                    outputStream.flush();

                } catch (IOException e) {
                    return false;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            } catch (IOException e) {
                return false;
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (mOnDownloadListener != null) {
                mOnDownloadListener.onUpdateProgress(values[0]);
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            //下载完毕后，重命名临时文件
            if (aBoolean) {
                File tempFile = new File(mSavePath + "temp_" + mFileName);
                File file = new File(mSavePath + mFileName);
                tempFile.renameTo(file);
                String fileAbsolutePath = file.getAbsolutePath();

                if (mOnDownloadListener != null) {
                    mOnDownloadListener.onDownloadComplete(fileAbsolutePath);
                }

            } else {
                if (mOnDownloadListener != null) {
                    mOnDownloadListener.onDownloadFailure();
                }
            }
        }
    }
}
