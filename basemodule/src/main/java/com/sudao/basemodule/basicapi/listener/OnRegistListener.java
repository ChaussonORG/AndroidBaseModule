package com.sudao.basemodule.basicapi.listener;

/**
 * Created by Samuel on 5/27/16 15:11
 * Email:xuzhou40@gmail.com
 * desc:
 */
public interface OnRegistListener {
    void onRegistResponse(String code, String message);

    void onRegistFailure(String message);
}
