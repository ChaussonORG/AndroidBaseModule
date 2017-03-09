package com.sudao.basemodule.basicapi.listener;

/**
 * Created by Samuel on 5/27/16 11:22
 * Email:xuzhou40@gmail.com
 * desc:通用的监听器(有data)
 */
public interface OnBaseDataListener {
    void onBaseResponse(String code, String message, Object data);

    void onBaseFailure(String message);
}
