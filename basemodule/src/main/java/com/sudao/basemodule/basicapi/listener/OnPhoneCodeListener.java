package com.sudao.basemodule.basicapi.listener;

/**
 * Created by Samuel on 5/27/16 14:17
 * Email:xuzhou40@gmail.com
 * desc:
 */
public interface OnPhoneCodeListener {
    void onPhoneCodeResponse(String code, String message);

    void onPhoneCodeFailure(String message);
}
