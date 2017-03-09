package com.sudao.basemodule.basicapi;

import android.content.Context;

import com.sudao.basemodule.R;
import com.sudao.basemodule.basicapi.listener.OnBaseListener;
import com.sudao.basemodule.basicapi.request.UpdatePasswordRequest;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.http.BaseResponse;
import com.sudao.basemodule.http.CommonCallback;
import com.sudao.basemodule.http.HTTPHelper;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Samuel on 5/26/16 15:44
 * Email:xuzhou40@gmail.com
 * desc:更改密码
 */
public class UpdatePassword {
    public OnBaseListener mOnBaseListener;
    private Context mContext;

    public UpdatePassword(Context context) {
        mContext = context;
    }

    public void setOnBaseListener(OnBaseListener onBaseListener) {
        mOnBaseListener = onBaseListener;
    }

    public void doUpdate(String oldPassword, String newPassword, String confirmPassword) {
        int validity = checkInputValidity(oldPassword, newPassword, confirmPassword);
        if (validity == 1) {
            UpdatePasswordRequest request = new UpdatePasswordRequest(oldPassword, newPassword);
            BaseService baseService = HTTPHelper.getBaseService();
            Call<BaseResponse<Object>> call = baseService.updatePassword(request);
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

    //检查输入
    private int checkInputValidity(String oldPassword, String newPassword, String confimPassword) {
        if (oldPassword.isEmpty()) {//输入内容为空
            ToastHelper.showToast(mContext, R.string.hint_input_empty);
            return 0;
        }
        if (newPassword.isEmpty()) {
            ToastHelper.showToast(mContext, R.string.hint_input_empty);
            return 0;
        }
        if (confimPassword.isEmpty()) {
            ToastHelper.showToast(mContext, R.string.hint_input_empty);
            return 0;
        }

        if (newPassword.length() < 6) {//密码长度不能小于6位
            ToastHelper.showToast(mContext, R.string.hint_passowrd_too_short);
            return 0;
        }
        if (!newPassword.equals(confimPassword)) {
            ToastHelper.showToast(mContext, R.string.hint_input_different);
            return 0;//手机号不正确
        }

        return 1;//输入正确
    }
}
