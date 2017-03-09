package com.sudao.basemodule.basicapi.listener;

/**
 * Created by Samuel on 5/26/16 14:23
 * Email:xuzhou40@gmail.com
 * desc: 通用的监听器(没有data)
 */
public interface OnBaseListener {
    void onBaseResponse(String code, String message);

    void onBaseFailure(String message);
}
