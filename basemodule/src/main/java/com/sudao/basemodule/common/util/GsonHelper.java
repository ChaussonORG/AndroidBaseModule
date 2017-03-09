package com.sudao.basemodule.common.util;

import com.google.gson.Gson;

/**
 * @Description: （json工具类）
 **/
public class GsonHelper {

    private static Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

}
