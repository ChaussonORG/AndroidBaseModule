package com.sudao.basemodule.basicapi.listener;

/**
 * Created by Samuel on 6/7/16 17:04
 * Email:xuzhou40@gmail.com
 * desc:
 */
public interface OnBindPhoneListener {
    void onBindPhoneResponse(String code, String message);

    void onBindPhoneFailure(String message);
}
