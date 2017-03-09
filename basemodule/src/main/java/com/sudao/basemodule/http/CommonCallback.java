package com.sudao.basemodule.http;


import com.sudao.basemodule.R;
import com.sudao.basemodule.base.BaseApplication;
import com.sudao.basemodule.common.util.LogUtil;
import com.sudao.basemodule.common.util.ToastHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.body() == null) {
            LogUtil.d(call.request().toString() + " ---响应--- " + response.raw().toString());
        } else {
            LogUtil.d(call.request().toString() + " ---响应--- " + response.body().toString());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        ToastHelper.showToast(BaseApplication.instance, R.string.hint_net_error);
    }
}
