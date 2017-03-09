package com.sudao.basemodule.basicapi.listener;

/**
 * Created by Samuel on 5/27/16 11:59
 * Email:xuzhou40@gmail.com
 * desc:
 */
public interface OnResetPasswordListener {
    void onResetResponse(String code, String message);

    void onResetFailure(String message);
}
