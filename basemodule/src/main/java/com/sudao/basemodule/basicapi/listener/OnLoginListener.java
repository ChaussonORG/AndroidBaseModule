package com.sudao.basemodule.basicapi.listener;

/**
 * Created by Samuel on 5/27/16 10:15
 * Email:xuzhou40@gmail.com
 * desc:
 */
public interface OnLoginListener {
    void onLoginResponse(String code, String message, Object data);

    void onLoginFailure(String message);
}
