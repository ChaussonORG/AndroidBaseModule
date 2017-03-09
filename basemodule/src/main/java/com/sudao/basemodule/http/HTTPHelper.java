package com.sudao.basemodule.http;

import com.sudao.basemodule.basicapi.BaseService;

/**
 * Created by Samuel on 5/25/16 14:55
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class HTTPHelper {
    private static BaseService baseService;

    private HTTPHelper() {
    }

    public static BaseService getBaseService() {
        if (baseService == null) {
            baseService = ServiceGenerator.createService(BaseService.class);
        }
        return baseService;
    }
}
