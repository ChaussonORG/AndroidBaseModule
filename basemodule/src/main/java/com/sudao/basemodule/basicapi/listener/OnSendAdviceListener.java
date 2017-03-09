package com.sudao.basemodule.basicapi.listener;

/**
 * Created by Samuel on 6/3/16 09:34
 * Email:xuzhou40@gmail.com
 * desc:
 */
public interface OnSendAdviceListener {
    void onSendAdviceResponse(String code, String message);

    void onSendAdviceFailure(String message);
}
