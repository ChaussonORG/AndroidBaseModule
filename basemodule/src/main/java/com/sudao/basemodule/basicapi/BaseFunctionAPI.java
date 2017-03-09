package com.sudao.basemodule.basicapi;

import android.content.Context;

import com.sudao.basemodule.basicapi.listener.OnBaseDataListener;
import com.sudao.basemodule.basicapi.listener.OnBaseListener;
import com.sudao.basemodule.basicapi.listener.OnBindPhoneListener;
import com.sudao.basemodule.basicapi.listener.OnLoginListener;
import com.sudao.basemodule.basicapi.listener.OnLogoutListener;
import com.sudao.basemodule.basicapi.listener.OnPhoneCodeListener;
import com.sudao.basemodule.basicapi.listener.OnRegistListener;
import com.sudao.basemodule.basicapi.listener.OnResetPasswordListener;
import com.sudao.basemodule.basicapi.listener.OnSendAdviceListener;
import com.sudao.basemodule.basicapi.listener.OnUpdatePasswordListener;
import com.sudao.basemodule.basicapi.request.RegistRequest;

/**
 * Created by Samuel on 5/26/16 11:48
 * Email:xuzhou40@gmail.com
 * desc:基础功能对API调用，包括登录，忘记密码，更新密码等
 */
public class BaseFunctionAPI {
    public OnLoginListener mOnLoginListener;
    public OnUpdatePasswordListener mOnUpdatePasswordListener;
    public OnPhoneCodeListener mOnPhoneCodeListener;
    public OnRegistListener mOnRegistListener;
    public OnResetPasswordListener mOnResetPasswordListener;
    public OnLogoutListener mOnLogoutListener;
    public OnSendAdviceListener mOnSendAdviceListener;
    public OnBindPhoneListener mOnBindPhoneListener;
    private Context mContext;

    public BaseFunctionAPI(Context context) {
        mContext = context;
    }

    public void setOnBindPhoneListener(OnBindPhoneListener onBindPhoneListener) {
        mOnBindPhoneListener = onBindPhoneListener;
    }

    public void setOnSendAdviceListener(OnSendAdviceListener onSendAdviceListener) {
        mOnSendAdviceListener = onSendAdviceListener;
    }

    public void setOnLogoutListener(OnLogoutListener onLogoutListener) {
        mOnLogoutListener = onLogoutListener;
    }

    public void setOnResetPasswordListener(OnResetPasswordListener onResetPasswordListener) {
        mOnResetPasswordListener = onResetPasswordListener;
    }

    public void setOnRegistListener(OnRegistListener onRegistListener) {
        mOnRegistListener = onRegistListener;
    }

    public void setOnPhoneCodeListener(OnPhoneCodeListener onPhoneCodeListener) {
        mOnPhoneCodeListener = onPhoneCodeListener;
    }

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        mOnLoginListener = onLoginListener;
    }

    public void setOnUpdatePasswordListener(OnUpdatePasswordListener onUpdatePasswordListener) {
        mOnUpdatePasswordListener = onUpdatePasswordListener;
    }

    public void login(String username, String password) {
        AccountLogin accountLogin = new AccountLogin(mContext);
        accountLogin.doLogin(username, password);
        accountLogin.setOnBaseDataListener(new OnBaseDataListener() {
            @Override
            public void onBaseResponse(String code, String message, Object data) {
                mOnLoginListener.onLoginResponse(code, message, data);
            }

            @Override
            public void onBaseFailure(String message) {
                mOnLoginListener.onLoginFailure(message);
            }
        });

    }

    public void updatePassword(String oldPassword, String newPassword, String confirmPassword) {
        UpdatePassword updatePassword = new UpdatePassword(mContext);
        updatePassword.doUpdate(oldPassword, newPassword, confirmPassword);
        updatePassword.setOnBaseListener(new OnBaseListener() {
            @Override
            public void onBaseResponse(String code, String message) {
                mOnUpdatePasswordListener.onUpdateResponse(code, message);
            }

            @Override
            public void onBaseFailure(String message) {
                mOnUpdatePasswordListener.onUpdateFailure(message);
            }
        });
    }

    public void getPhoneCode(String phone) {
        GetPhoneCode getPhoneCode = new GetPhoneCode(mContext);
        getPhoneCode.doGet(phone);
        getPhoneCode.setOnBaseListener(new OnBaseListener() {
            @Override
            public void onBaseResponse(String code, String message) {
                mOnPhoneCodeListener.onPhoneCodeResponse(code, message);
            }

            @Override
            public void onBaseFailure(String message) {
                mOnPhoneCodeListener.onPhoneCodeFailure(message);
            }
        });

    }

    public void regist(String phone, String password, String code) {
        AccountRegist accountRegist = new AccountRegist(mContext);
        accountRegist.doRegist(phone, password, code);
        accountRegist.setOnBaseListener(new OnBaseListener() {
            @Override
            public void onBaseResponse(String code, String message) {
                mOnRegistListener.onRegistResponse(code, message);
            }

            @Override
            public void onBaseFailure(String message) {
                mOnRegistListener.onRegistFailure(message);
            }
        });
    }

    public void resetPassword(String phone, String password, String code) {
        ResetPassword resetPassword = new ResetPassword(mContext);
        resetPassword.doReset(phone, password, code);
        resetPassword.setOnBaseListener(new OnBaseListener() {
            @Override
            public void onBaseResponse(String code, String message) {
                mOnResetPasswordListener.onResetResponse(code, message);
            }

            @Override
            public void onBaseFailure(String message) {
                mOnResetPasswordListener.onResetFailure(message);
            }
        });
    }

    public void logout() {
        AccountLogout accountLogout = new AccountLogout(mContext);
        accountLogout.doLogout();
        accountLogout.setOnBaseListener(new OnBaseListener() {
            @Override
            public void onBaseResponse(String code, String message) {
                mOnLogoutListener.onLogoutResponse(code, message);
            }

            @Override
            public void onBaseFailure(String message) {
                mOnLogoutListener.onLogoutFailure(message);
            }
        });
    }

    public void sendAdvice(String advice) {
        SendAdvice sendAdvice = new SendAdvice(mContext);
        sendAdvice.doSend(advice);
        sendAdvice.setOnBaseListener(new OnBaseListener() {
            @Override
            public void onBaseResponse(String code, String message) {
                mOnSendAdviceListener.onSendAdviceResponse(code, message);
            }

            @Override
            public void onBaseFailure(String message) {
                mOnSendAdviceListener.onSendAdviceFailure(message);
            }
        });
    }

    public void bindPhone(String phone, String password, String code) {
        BindPhone bindPhone = new BindPhone(mContext);
        bindPhone.doBind(phone, password, code);
        bindPhone.setOnBaseListener(new OnBaseListener() {
            @Override
            public void onBaseResponse(String code, String message) {
                mOnBindPhoneListener.onBindPhoneResponse(code, message);
            }

            @Override
            public void onBaseFailure(String message) {
                mOnBindPhoneListener.onBindPhoneFailure(message);
            }
        });
    }

    public void bindPhoneWithUserId(RegistRequest registRequest) {
        BindPhone bindPhone = new BindPhone(mContext);
        bindPhone.doBindWithRegistRequest(registRequest);
        bindPhone.setOnBaseListener(new OnBaseListener() {
            @Override
            public void onBaseResponse(String code, String message) {
                mOnBindPhoneListener.onBindPhoneResponse(code, message);
            }

            @Override
            public void onBaseFailure(String message) {
                mOnBindPhoneListener.onBindPhoneFailure(message);
            }
        });
    }
}