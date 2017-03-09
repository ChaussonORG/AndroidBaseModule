package com.sudao.basemodule.component.file;

import java.util.List;

/**
 * Created by Samuel on 5/28/16 13:23
 * Email:xuzhou40@gmail.com
 * desc:
 */
public interface OnUploadListener {
    void onUploadComplete(List<String> urlList);

    void onUploadFailure(String code, String message);
}
