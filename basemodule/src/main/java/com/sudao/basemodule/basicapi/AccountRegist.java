package com.sudao.basemodule.basicapi;

import android.content.Context;

import com.sudao.basemodule.R;
import com.sudao.basemodule.basicapi.listener.OnBaseListener;
import com.sudao.basemodule.basicapi.request.RegistRequest;
import com.sudao.basemodule.common.util.StringUtil;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.http.BaseResponse;
import com.sudao.basemodule.http.CommonCallback;
import com.sudao.basemodule.http.HTTPHelper;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Samuel on 5/27/16 15:13
 * Email:xuzhou40@gmail.com
 * desc:用户注册
 */
public class AccountRegist {
    public OnBaseListener mOnBaseListener;
    private Context mContext;

    public AccountRegist(Context context) {
        mContext = context;
    }

    public void setOnBaseListener(OnBaseListener onBaseListener) {
        mOnBaseListener = onBaseListener;
    }

    public void doRegist(String phone, String password, String code) {
        if (checkInputValidity(phone, password, code) == 1) {
            RegistRequest registRequest = new RegistRequest(phone, password, code);
            BaseService baseService = HTTPHelper.getBaseService();
            Call<BaseResponse<Object>> call = baseService.regist(registRequest);
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
    private int checkInputValidity(String phone, String password, String code) {
        if (phone.isEmpty()) {
            ToastHelper.showToast(mContext, R.string.hint_phone_input_empty);//手机号码为空
            return 0;
        }
        if (code.isEmpty()) {
            ToastHelper.showToast(mContext, R.string.hint_code_input_empty);//手机验证码为空
            return 0;
        }
        if (password.isEmpty()) {
            ToastHelper.showToast(mContext, R.string.hint_password_input_empty);//密码为空
            return 0;
        }
        if (password.length() < 6) {
            ToastHelper.showToast(mContext, R.string.hint_passowrd_too_short);//密码太短
            return 0;
        }
        if (!StringUtil.checkPhoneNumber(phone)) {
            ToastHelper.showToast(mContext, R.string.hint_input_phone_error);
            return 0;//手机号不正确
        }
        return 1;//输入正确
    }


}
