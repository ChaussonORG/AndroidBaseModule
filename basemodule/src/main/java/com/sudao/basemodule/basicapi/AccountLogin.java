package com.sudao.basemodule.basicapi;

import android.content.Context;

import com.sudao.basemodule.R;
import com.sudao.basemodule.basicapi.listener.OnBaseDataListener;
import com.sudao.basemodule.basicapi.request.LoginRequest;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.http.BaseResponse;
import com.sudao.basemodule.http.CommonCallback;
import com.sudao.basemodule.http.HTTPHelper;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Samuel on 5/24/16 15:23
 * Email:xuzhou40@gmail.com
 * desc:用户登录（使用手机号码登录）
 */
public class AccountLogin {
    public OnBaseDataListener mOnBaseDataListener;
    Context mContext;
    private String username;
    private String password;

    public AccountLogin(Context context) {
        mContext = context;
    }

    public void setOnBaseDataListener(OnBaseDataListener onBaseDataListener) {
        mOnBaseDataListener = onBaseDataListener;
    }

    //检查输入
    private int checkInputValidity(String username, String password) {
        if (username.isEmpty()) {
            ToastHelper.showToast(mContext, R.string.hint_login_input_empty);//用户名或者密码为空
            return 0;
        }
        if (password.isEmpty()) {
            ToastHelper.showToast(mContext, R.string.hint_login_input_empty);//用户名或者密码为空
            return 0;
        }
        return 1;//输入正确
    }

    /**
     * 用户名和密码
     *
     * @param username
     * @param password
     */
    public void doLogin(String username, String password) {
        int inputValidity = checkInputValidity(username, password);
        if (inputValidity == 1) {
            LoginRequest loginRequest = new LoginRequest(username, password);
            BaseService baseService = HTTPHelper.getBaseService();
            Call<BaseResponse<Object>> login = baseService.login(loginRequest);
            login.enqueue(new CommonCallback<BaseResponse<Object>>() {
                @Override
                public void onResponse(Call<BaseResponse<Object>> call, Response<BaseResponse<Object>> response) {
                    BaseResponse<Object> body = response.body();
                    if (body != null) {
                        String code = body.getCode();
                        String message = body.getMessage();
                        Object data = body.getData();

                        mOnBaseDataListener.onBaseResponse(code, message, data);
                    }
                }

                @Override
                public void onFailure(Call<BaseResponse<Object>> call, Throwable t) {
                    super.onFailure(call, t);
                    mOnBaseDataListener.onBaseFailure("网络请求失败，请检查网络后重试");
                }
            });
        }
    }

}
