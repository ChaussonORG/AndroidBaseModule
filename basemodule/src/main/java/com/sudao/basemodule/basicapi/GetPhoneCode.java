package com.sudao.basemodule.basicapi;

import android.content.Context;

import com.sudao.basemodule.R;
import com.sudao.basemodule.basicapi.listener.OnBaseListener;
import com.sudao.basemodule.basicapi.request.PhoneCodeRequest;
import com.sudao.basemodule.common.util.StringUtil;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.http.BaseResponse;
import com.sudao.basemodule.http.CommonCallback;
import com.sudao.basemodule.http.HTTPHelper;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Samuel on 5/27/16 14:20
 * Email:xuzhou40@gmail.com
 * desc:获取手机验证码
 */
public class GetPhoneCode {
    public OnBaseListener mOnBaseListener;
    private Context mContext;

    public GetPhoneCode(Context context) {
        mContext = context;
    }

    public void setOnBaseListener(OnBaseListener onBaseListener) {
        mOnBaseListener = onBaseListener;
    }

    public void doGet(String phone) {
        if (checkPhoneNumber(phone) == 1) {
            PhoneCodeRequest phoneCodeRequest = new PhoneCodeRequest(phone);
            BaseService baseService = HTTPHelper.getBaseService();
            Call<BaseResponse<Object>> call = baseService.getPhoneCode(phoneCodeRequest);
            call.enqueue(new CommonCallback<BaseResponse<Object>>() {
                @Override
                public void onResponse(Call<BaseResponse<Object>> call, Response<BaseResponse<Object>> response) {
                    super.onResponse(call, response);
                    BaseResponse body = response.body();
                    if (body != null) {
                        String code = body.getCode();
                        String message = body.getMessage();

                        mOnBaseListener.onBaseResponse(code, message);
                    }


                }

                @Override
                public void onFailure(Call<BaseResponse<Object>> call, Throwable t) {
                    super.onFailure(call, t);
                    mOnBaseListener.onBaseFailure("网络请求失败，请检查网络后重试");

                }
            });
        }

    }

    private int checkPhoneNumber(String phone) {
        if (phone.isEmpty()) {
            ToastHelper.showToast(mContext, R.string.hint_phone_input_empty);
            return 0;
        }
        if (!StringUtil.checkPhoneNumber(phone)) {
            ToastHelper.showToast(mContext, R.string.hint_input_phone_error);
            return 0;
        }
        return 1;
    }

}
