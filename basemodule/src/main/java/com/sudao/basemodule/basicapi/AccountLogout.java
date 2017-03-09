package com.sudao.basemodule.basicapi;

import android.content.Context;

import com.sudao.basemodule.basicapi.listener.OnBaseListener;
import com.sudao.basemodule.http.BaseResponse;
import com.sudao.basemodule.http.CommonCallback;
import com.sudao.basemodule.http.HTTPHelper;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Samuel on 6/3/16 09:27
 * Email:xuzhou40@gmail.com
 * desc:退出
 */
public class AccountLogout {
    public OnBaseListener mOnBaseListener;
    private Context mContext;

    public AccountLogout(Context context) {
        mContext = context;
    }

    public void setOnBaseListener(OnBaseListener onBaseListener) {
        mOnBaseListener = onBaseListener;
    }

    public void doLogout() {
        Call<BaseResponse> call = HTTPHelper.getBaseService().logout();
        call.enqueue(new CommonCallback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                super.onResponse(call, response);
                BaseResponse body = response.body();
                if (body != null) {
                    String code = body.getCode();
                    String message = body.getMessage();
                    mOnBaseListener.onBaseResponse(code, message);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                super.onFailure(call, t);
                mOnBaseListener.onBaseFailure("网络请求失败，请检查网络后重试");
            }
        });
    }
}
