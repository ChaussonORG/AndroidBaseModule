package com.sudao.basemodule.component.previewfile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.joanzapata.pdfview.PDFView;
import com.squareup.picasso.Picasso;
import com.sudao.basemodule.R;
import com.sudao.basemodule.common.util.LogUtil;
import com.sudao.basemodule.common.util.StringUtil;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.common.util.ToolbarUtil;
import com.sudao.basemodule.component.file.FileDownloader;
import com.sudao.basemodule.component.file.OnDownloadListener;

import java.io.File;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PreviewFileActivity extends AppCompatActivity implements OnDownloadListener {
    private NumberProgressBar mNumberProgressBar;
    private PhotoView mIvPhoto;
    private LinearLayout mLlPreviewPicture;
    private PDFView mPdfview;
    private LinearLayout mLlPreviewPdf;
    private RelativeLayout mRlLoading;
    private Toolbar mToolbar;
    private TextView mTvToolbarTitle;
    private String mFileName;
    private String mFileUrl;
    private Context mContext;
    private FileDownloader mFileDownloader;
    private String mSavePath;
    private File mFile;

    private void assignViews() {
        mRlLoading = (RelativeLayout) findViewById(R.id.rl_loading);
        mNumberProgressBar = (NumberProgressBar) findViewById(R.id.number_progress_bar);
        mIvPhoto = (PhotoView) findViewById(R.id.iv_photo);
        mLlPreviewPicture = (LinearLayout) findViewById(R.id.ll_preview_picture);
        mLlPreviewPdf = (LinearLayout) findViewById(R.id.ll_preview_pdf);
        mPdfview = (PDFView) findViewById(R.id.pdfview);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_file);
        assignViews();
        mContext = this;
        init();


    }

    private void init() {
        mFileUrl = getIntent().getStringExtra("file_url");
        mFileName = getIntent().getStringExtra("file_name");
        if (mFileName.isEmpty()) {
            mFileName = "预览文件";
        }

        ToolbarUtil.setupToolbar(this, mToolbar);
        mTvToolbarTitle.setText(mFileName);

//        mSavePath = StringUtil.SD_PATH + "/" + "shipmanage" + "/" + "download_file" + "/";
        mSavePath = StringUtil.SD_DOWNLOAD_PATH + "/";
        mFileDownloader = new FileDownloader(this);
        mFileDownloader.setOnDownloadListener(this);

        mFile = new File(mSavePath + mFileName);
        //判断文件是否已经下载
        if (mFile.exists()) {
            previewFile(mFile.getAbsolutePath());
            LogUtil.d("文件已存在");
        } else {
            startDownloadFile();
        }
    }

    private void startDownloadFile() {
        mFileDownloader.onStartDownload(mFileUrl, mFileName, mSavePath);

    }

    private void previewFile(String fileAbsolutePath) {
        if (StringUtil.isPictureFile(fileAbsolutePath)) {
            previewPicture(fileAbsolutePath);
        } else if (StringUtil.isPdfFile(fileAbsolutePath)) {
            previewPDF(fileAbsolutePath);
        }
    }

    //预览PDF
    private void previewPDF(String fileAbsolutePath) {
        mRlLoading.removeAllViews();
        mLlPreviewPicture.removeAllViews();
        mLlPreviewPdf.setVisibility(View.VISIBLE);
        File file = new File(fileAbsolutePath);
        mPdfview.fromFile(file)
                .defaultPage(0)
                .showMinimap(false)
                .swipeVertical(true)
                .load();
    }

    //预览图片
    private void previewPicture(String fileAbsolutePath) {
        mRlLoading.removeAllViews();
        mLlPreviewPdf.removeAllViews();
        mLlPreviewPicture.setVisibility(View.VISIBLE);

        final PhotoViewAttacher attacher = new PhotoViewAttacher(mIvPhoto);
        File file = new File(fileAbsolutePath);

        Picasso.with(this)
                .load(file)
                .into(mIvPhoto, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        attacher.update();
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFileDownloader.onStop();
    }

    @Override
    public void onDownloadComplete(String file) {
        previewFile(mFile.getAbsolutePath());
    }

    @Override
    public void onUpdateProgress(int progress) {
        mNumberProgressBar.setProgress(progress);

    }

    @Override
    public void onDownloadFailure() {
        ToastHelper.showToast(mContext, R.string.hint_download_fail);
    }

    //点击左上角返回键，相当于按下 back 键
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
