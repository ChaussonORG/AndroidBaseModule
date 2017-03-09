package com.sudao.basemodule.component.previewfile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.sudao.basemodule.R;
import com.sudao.basemodule.common.util.LogUtil;
import com.sudao.basemodule.common.util.StringUtil;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.common.util.ToolbarUtil;
import com.sudao.basemodule.component.file.FileDownloader;
import com.sudao.basemodule.component.file.OnDownloadListener;

import java.io.File;
import java.io.IOException;

public class OpenOtherFileActivity extends AppCompatActivity implements View.OnClickListener, OnDownloadListener {

    private Toolbar mToolbar;
    private TextView mTvHint;
    private NumberProgressBar mNumberProgressBar;
    private Button mBtnOpenWithOther;
    private TextView mTvToolbarTitle;
    private String mFileUrl;
    private String mFileName;
    private Context mContext;
    private File mFile;
    private String mSavePath;
    private FileDownloader mFileDownloader;

    private void assignViews() {
        mTvHint = (TextView) findViewById(R.id.tv_hint);
        mNumberProgressBar = (NumberProgressBar) findViewById(R.id.number_progress_bar);
        mBtnOpenWithOther = (Button) findViewById(R.id.btn_open_with_other);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        mBtnOpenWithOther.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_other_file);
        assignViews();
        mContext = this;
        init();
    }

    private void init() {
        mFileUrl = getIntent().getStringExtra("file_url");
        mFileName = getIntent().getStringExtra("file_name");

        ToolbarUtil.setupToolbar(mContext, mToolbar);
        mTvToolbarTitle.setText(mFileName);

        mFileDownloader = new FileDownloader(this);
        mFileDownloader.setOnDownloadListener(this);
//        mSavePath = StringUtil.SD_PATH + "/" + "shipmanage" + "/" + "download_file" + "/";
        mSavePath = StringUtil.SD_DOWNLOAD_PATH + "/";

        mFile = new File(mSavePath + mFileName);
        //判断文件是否已经下载
        if (mFile.exists()) {
            mTvHint.setText(R.string.file_unsupport);
            mBtnOpenWithOther.setVisibility(View.VISIBLE);
            mNumberProgressBar.setVisibility(View.INVISIBLE);
            LogUtil.d("文件已存在");
        } else {
            startDownloadFile();
        }
    }

    private void openWithOtherApp(File downloadedFile) {
        try {
            OpenFile.openFile(mContext, downloadedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startDownloadFile() {
        mFileDownloader.onStartDownload(mFileUrl, mFileName, mSavePath);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFileDownloader.onStop();
    }

    @Override
    public void onClick(View v) {
        openWithOtherApp(mFile);
    }

    @Override
    public void onDownloadComplete(String file) {
        mBtnOpenWithOther.setVisibility(View.VISIBLE);
        mNumberProgressBar.setVisibility(View.INVISIBLE);
        mTvHint.setText(R.string.file_unsupport);
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
