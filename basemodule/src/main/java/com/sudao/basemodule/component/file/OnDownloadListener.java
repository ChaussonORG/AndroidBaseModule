package com.sudao.basemodule.component.file;

/**
 * Created by Samuel on 5/28/16 13:23
 * Email:xuzhou40@gmail.com
 * desc:
 */
public interface OnDownloadListener {
    void onDownloadComplete(String file);

    void onUpdateProgress(int progress);

    void onDownloadFailure();
}
