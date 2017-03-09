package com.sudao.basemodule.basicapi.activity;

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
import com.sudao.basemodule.basicapi.BaseFunctionAPI;
import com.sudao.basemodule.basicapi.BaseService;
import com.sudao.basemodule.basicapi.listener.OnPhoneCodeListener;
import com.sudao.basemodule.basicapi.listener.OnRegistListener;
import com.sudao.basemodule.basicapi.request.RegistRequest;
import com.sudao.basemodule.common.util.StringUtil;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.common.util.ToolbarUtil;
import com.sudao.basemodule.common.view.CountDown;
import com.sudao.basemodule.http.BaseResponse;
import com.sudao.basemodule.http.CommonCallback;
import com.sudao.basemodule.http.HTTPHelper;

import retrofit2.Call;
import retrofit2.Response;

public class RegistWithInviteCodeActivity extends BaseActivity implements OnPhoneCodeListener, OnRegistListener, View.OnClickListener {
    private EditText mEdtPhone;
    private TextView mTvGetPhoneCode;
    private EditText mEdtPhoneCode;
    private EditText mEdtPassword;
    private Button mBtnRegister;
    private Toolbar mToolbar;
    private TextView mTvToolbarTitle;
    private BaseFunctionAPI mBaseFunctionAPI;
    private CountDown mCountDown;
    private ImageView mIvEye;
    private boolean mPasswordVisible = false;
    private EditText mEdtInviteCode;

    private void assignViews() {
        mEdtPhone = (EditText) findViewById(R.id.edt_phone);
        mTvGetPhoneCode = (TextView) findViewById(R.id.tv_get_phone_code);
        mEdtPhoneCode = (EditText) findViewById(R.id.edt_phone_code);
        mEdtPassword = (EditText) findViewById(R.id.edt_password);
        mEdtInviteCode = (EditText) findViewById(R.id.edt_invite_code);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        mIvEye = (ImageView) findViewById(R.id.iv_eye);


        mTvGetPhoneCode.setOnClickListener(this);
        mBtnRegister.setOnClickListener(this);
        mIvEye.setOnClickListener(this);
    }


    @Override
    public void layout() {
        setContentView(R.layout.activity_regist_with_code);
    }

    @Override
    public void initView() {
        assignViews();
        ToolbarUtil.setupToolbar(mContext, mToolbar);
        mTvToolbarTitle.setText("注册");
        mBtnRegister.setText("注册");

        mCountDown = new CountDown(60000, 1000, mTvGetPhoneCode);

        mBaseFunctionAPI = new BaseFunctionAPI(mContext);
        mBaseFunctionAPI.setOnPhoneCodeListener(this);

    }

    @Override
    public void onPhoneCodeResponse(String code, String message) {
        if (code.equals("200")) {
            ToastHelper.showToast(mContext, "验证码已发送，请注意查收");
            mCountDown.start();
        } else {
            ToastHelper.showToast(mContext, message);
        }
    }

    @Override
    public void onPhoneCodeFailure(String message) {
        ToastHelper.showToast(mContext, message);
    }

    @Override
    public void onRegistResponse(String code, String message) {
        if (code.equals("200")) {
            ToastHelper.showToast(mContext, "注册成功");
            finish();
        } else {
            ToastHelper.showToast(mContext, message);
        }
    }

    @Override
    public void onRegistFailure(String message) {
        ToastHelper.showToast(mContext, message);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_get_phone_code) {
            getPhoneCode();
        } else if (id == R.id.btn_register) {
            regist();
        } else if (id == R.id.iv_eye) {
            showPassword();
        }
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

    private void getPhoneCode() {
        String phone = mEdtPhone.getText().toString();
        mBaseFunctionAPI.getPhoneCode(phone);
    }

    private void regist() {
        String phone = mEdtPhone.getText().toString().trim();
        String code = mEdtPhoneCode.getText().toString().trim();
        String password = mEdtPassword.getText().toString().trim();
        String inviteCode = mEdtInviteCode.getText().toString().trim();

        if (checkInputValidity(phone, password, code) == 1) {
            RegistRequest registRequest = new RegistRequest(phone, password, code);
            if (inviteCode.length() > 0) {
                registRequest.setInviteCode(inviteCode);
            }

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
                        if ("200".equals(code)) {
                            ToastHelper.showToast(mContext, "注册成功");
                            finish();
                        } else {
                            ToastHelper.showToast(mContext, message);
                        }
                    }
                }
            });
        }

    }

    //检查输入
    private int checkInputValidity(String phone, String password, String code) {
        if (phone.isEmpty()) {
            ToastHelper.showToast(this, R.string.hint_phone_input_empty);//手机号码为空
            return 0;
        }
        if (code.isEmpty()) {
            ToastHelper.showToast(this, R.string.hint_code_input_empty);//手机验证码为空
            return 0;
        }
        if (password.isEmpty()) {
            ToastHelper.showToast(this, R.string.hint_password_input_empty);//密码为空
            return 0;
        }
        if (password.length() < 6) {
            ToastHelper.showToast(this, R.string.hint_passowrd_too_short);//密码太短
            return 0;
        }
        if (!StringUtil.checkPhoneNumber(phone)) {
            ToastHelper.showToast(this, R.string.hint_input_phone_error);
            return 0;//手机号不正确
        }
        return 1;//输入正确
    }


}
