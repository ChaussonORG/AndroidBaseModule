package com.sudao.basemodule.basicapi;

import android.content.Context;

import com.sudao.basemodule.basicapi.listener.OnBaseListener;
import com.sudao.basemodule.basicapi.request.AdviceRequest;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.http.BaseResponse;
import com.sudao.basemodule.http.CommonCallback;
import com.sudao.basemodule.http.HTTPHelper;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Samuel on 6/3/16 10:00
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class SendAdvice {
    public OnBaseListener mOnBaseListener;
    private Context mContext;

    public SendAdvice(Context context) {
        mContext = context;
    }

    public void setOnBaseListener(OnBaseListener onBaseListener) {
        mOnBaseListener = onBaseListener;
    }

    public void doSend(String advice) {
        if (checkAdviceInput(advice) == 1) {
            AdviceRequest adviceRequest = new AdviceRequest("意见反馈", advice);
            Call<BaseResponse> call = HTTPHelper.getBaseService().sendAdvice(adviceRequest);
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

    private int checkAdviceInput(String advice) {
        if (advice.isEmpty()) {
            ToastHelper.showToast(mContext, "输入内容不能为空");
            return 0;
        } else {
            return 1;
        }
    }
}
