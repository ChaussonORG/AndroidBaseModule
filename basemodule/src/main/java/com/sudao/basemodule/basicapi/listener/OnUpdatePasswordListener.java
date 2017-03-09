package com.sudao.basemodule.basicapi.listener;

/**
 * Created by Samuel on 5/27/16 11:49
 * Email:xuzhou40@gmail.com
 * desc:
 */
public interface OnUpdatePasswordListener {
    void onUpdateResponse(String code, String message);

    void onUpdateFailure(String message);
}
