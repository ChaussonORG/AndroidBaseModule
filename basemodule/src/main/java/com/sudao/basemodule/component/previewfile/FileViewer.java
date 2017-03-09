package com.sudao.basemodule.component.previewfile;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Samuel on 5/13/16 09:38
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class FileViewer {
    private Context mContext;

    public FileViewer(Context context) {
        this.mContext = context;
    }

    private static boolean isPictureFile(String string) {
        String regex = "^.*?\\.(png|jpg)$";
        if (string.matches(regex)) {
            return true;
        } else
            return false;
    }

    private static boolean isPdfFile(String string) {
        String regex = "^.*?\\.(pdf)$";
        if (string.matches(regex)) {
            return true;
        } else
            return false;
    }

    public boolean isSupportPreview(String format) {
        if (isPictureFile(format)) {
            return true;
        } else if (isPdfFile(format)) {
            return true;
        } else {
            return false;
        }
    }

    public void previewFile(String url, String fileName) {
        Intent intent = new Intent(mContext, PreviewFileActivity.class);
        intent.putExtra("file_url", url);
        intent.putExtra("file_name", fileName);
        mContext.startActivity(intent);
    }

    public void openOtherFile(String url, String fileName) {
        Intent intent = new Intent(mContext, OpenOtherFileActivity.class);
        intent.putExtra("file_url", url);
        intent.putExtra("file_name", fileName);
        mContext.startActivity(intent);
    }
}
