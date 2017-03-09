package com.sudao.basemodule.basicapi.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sudao.basemodule.R;
import com.sudao.basemodule.base.BaseActivity;
import com.sudao.basemodule.base.BundleConst;
import com.sudao.basemodule.basicapi.BaseFunctionAPI;
import com.sudao.basemodule.basicapi.listener.OnLoginListener;
import com.sudao.basemodule.basicapi.request.ThirdLoginRequest;
import com.sudao.basemodule.common.util.LogUtil;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.common.util.ToolbarUtil;
import com.sudao.basemodule.http.BaseResponse;
import com.sudao.basemodule.http.CommonCallback;
import com.sudao.basemodule.http.HTTPHelper;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public abstract class BaseLoginActivity extends BaseActivity implements OnLoginListener, View.OnClickListener {
    protected int userType;
    private ImageView mClose;
    private ImageView mIvLogo;
    //    private RelativeLayout mRlThirdLogin;
//    private Button mWeiboLogin;
//    private ImageView mQqLogin;
//    private ImageView mWechatLogin;
    private EditText mEdtUsername;
    private EditText mEdtPassword;
    private Toolbar mToolBar;
    private TextView mToolTitle;
    private Button mLogin;
    //private TextView mRegist;
    private TextView mForgetPassword;
    private BaseFunctionAPI mBaseFunctionAPI;
    private ImageView mIvEye;
    private boolean mPasswordVisible = false;
    private UMShareAPI mShareAPI;
    private UMAuthListener mUMAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            getIDAndToken(share_media, map);
            LogUtil.d(map.toString());

        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            ToastHelper.showToast(mContext, "授权失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            ToastHelper.showToast(mContext, "授权取消");
        }
    };

    private void assignViews() {
        mClose = (ImageView) findViewById(R.id.close);
        mIvLogo = (ImageView) findViewById(R.id.iv_logo);
        mIvEye = (ImageView) findViewById(R.id.iv_eye);

        mEdtUsername = (EditText) findViewById(R.id.edt_username);
        mEdtPassword = (EditText) findViewById(R.id.edt_password);
        mLogin = (Button) findViewById(R.id.login);
        //mRegist = (TextView) findViewById(R.id.regist);
        mForgetPassword = (TextView) findViewById(R.id.forget_password);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        mLogin.setOnClickListener(this);
        //mRegist.setOnClickListener(this);
        mForgetPassword.setOnClickListener(this);
//        mClose.setOnClickListener(this);
//        mIvEye.setOnClickListener(this);

        /*mRlThirdLogin = (RelativeLayout) findViewById(R.id.rl_third_login);
        mWeiboLogin = (Button) findViewById(R.id.weibo_login);*/
        //mWeiboLogin.setOnClickListener(this);
//        mQqLogin = (ImageView) findViewById(R.id.qq_login);
//        mWechatLogin = (ImageView) findViewById(R.id.wechat_login);
//        mQqLogin.setOnClickListener(this);
//        mWechatLogin.setOnClickListener(this);
    }

    @Override
    public void layout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {
        assignViews();
        ToolbarUtil.setupHomeToolbar(mContext, mToolBar);
        mToolTitle.setText("登录");
        mBaseFunctionAPI = new BaseFunctionAPI(mContext);
        mBaseFunctionAPI.setOnLoginListener(this);
//        mShareAPI = UMShareAPI.get(mContext);
        initViewLogin();

    }


    public abstract void initViewLogin();


    @Override
    public void onLoginResponse(String code, String message, Object data) {
        loginResult(code, message, data);
    }

    @Override
    public void onLoginFailure(String message) {
        ToastHelper.showToast(mContext, message);
    }

    public abstract void loginResult(String code, String message, Object data);

    public abstract void thirdLoginResult(String code, String message, Object data);

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.login) {
            normalLogin();//普通账号登录
        } else if (id == R.id.regist) {
            jumpToRegist();
        } else if (id == R.id.forget_password) {
            jumpToResetPassword();
        } else if (id == R.id.close) {
            close();
        } else if (id == R.id.iv_eye) {
            showPassword();
        }
        /*else if (id == R.id.weibo_login) {
            weiboLogin();
        } */
//        else if (id == R.id.qq_login) {
//            qqLogin();
//        } else if (id == R.id.wechat_login) {
//            wechatLogin();
//        }

    }

    private void showPassword() {
        if (mPasswordVisible) {
            mIvEye.setImageResource(R.drawable.ic_password_hide);
            mEdtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            mIvEye.setImageResource(R.drawable.ic_password_show);
            mEdtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        mPasswordVisible = !mPasswordVisible;
    }

//    private void qqLogin() {
//        userType = 12;
//        SHARE_MEDIA platform = SHARE_MEDIA.QQ;
//        mShareAPI.doOauthVerify(mContext, platform, mUMAuthListener);
//    }
//
//    private void wechatLogin() {
//        userType = 11;
//        SHARE_MEDIA platform = SHARE_MEDIA.WEIXIN;
//        mShareAPI.doOauthVerify(mContext, platform, mUMAuthListener);
//    }

//    private void weiboLogin() {
//        SHARE_MEDIA platform = SHARE_MEDIA.SINA;
//        mShareAPI.doOauthVerify(mContext, platform, mUMAuthListener);
//
//    }

    private void getIDAndToken(SHARE_MEDIA share_media, Map<String, String> map) {
        String openId = "";
        String accessToken = "";
        String userType = "";
        if (share_media.equals(SHARE_MEDIA.WEIXIN)) {
            openId = map.get("openid");
            accessToken = map.get("access_token");
            userType = BundleConst.ACCOUNTTYPEKEY_WEIXIN;
        } else if (share_media.equals(SHARE_MEDIA.QQ)) {
            openId = map.get("uid");
            accessToken = map.get("access_token");
            userType = BundleConst.ACCOUNTTYPEKEY_QQ;
        }
//        else if (share_media.equals(SHARE_MEDIA.SINA)) {
//            openId = map.get("uid");
//            accessToken = map.get("access_token");
//            userType = BundleConst.ACCOUNTTYPEKEY_WEIBO;
//        }
        thirdLogin(openId, accessToken, userType);
    }

    private void thirdLogin(String openId, String accessToken, String userType) {
        ThirdLoginRequest thirdLoginRequest = new ThirdLoginRequest(openId, accessToken);
        Call<BaseResponse<Object>> call = HTTPHelper.getBaseService().thirdLogin(thirdLoginRequest, userType);
        call.enqueue(new CommonCallback<BaseResponse<Object>>() {
            @Override
            public void onResponse(Call<BaseResponse<Object>> call, Response<BaseResponse<Object>> response) {
                super.onResponse(call, response);
                BaseResponse<Object> body = response.body();
                if (body != null) {
                    String code = body.getCode();
                    String message = body.getMessage();
                    Object data = body.getData();

                    thirdLoginResult(code, message, data);
                }

            }

            @Override
            public void onFailure(Call<BaseResponse<Object>> call, Throwable t) {
                super.onFailure(call, t);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    private void jumpToResetPassword() {
        Intent intent = new Intent(mContext, ResetPasswordActivity.class);
        startActivity(intent);
    }

    private void jumpToRegist() {
//        Intent intent = new Intent(mContext, RegistActivity.class);
        Intent intent = new Intent(mContext, RegistWithInviteCodeActivity.class);
        startActivity(intent);
    }

    public abstract void close();


    private void normalLogin() {
        String username = mEdtUsername.getText().toString();
        String password = mEdtPassword.getText().toString();
        mBaseFunctionAPI.login(username, password);
    }
}
