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
import com.sudao.basemodule.basicapi.listener.OnPhoneCodeListener;
import com.sudao.basemodule.basicapi.listener.OnResetPasswordListener;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.common.util.ToolbarUtil;
import com.sudao.basemodule.common.view.CountDown;

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener, OnPhoneCodeListener, OnResetPasswordListener {
    private EditText mEdtPhone;
    private TextView mTvGetPhoneCode;
    private EditText mEdtPhoneCode;
    private EditText mEdtPassword;
    private Button mBtnRegister;
    private Toolbar mToolbar;
    private TextView mTvToolbarTitle;
    private BaseFunctionAPI mBaseFunctionAPI;
    private CountDown mCountDown;
    private boolean mPasswordVisible = false;
    private ImageView mIvEye;


    private void assignViews() {
        mEdtPhone = (EditText) findViewById(R.id.edt_phone);
        mTvGetPhoneCode = (TextView) findViewById(R.id.tv_get_phone_code);
        mEdtPhoneCode = (EditText) findViewById(R.id.edt_phone_code);
        mEdtPassword = (EditText) findViewById(R.id.edt_password);
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
        setContentView(R.layout.activity_regist);
    }

    @Override
    public void initView() {
        assignViews();
        ToolbarUtil.setupToolbar(mContext, mToolbar);
        mTvToolbarTitle.setText("忘记密码");
        mBtnRegister.setText("确定");

        mCountDown = new CountDown(60000, 1000, mTvGetPhoneCode);

        mBaseFunctionAPI = new BaseFunctionAPI(mContext);
        mBaseFunctionAPI.setOnPhoneCodeListener(this);
        mBaseFunctionAPI.setOnResetPasswordListener(this);
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
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_get_phone_code) {
            getPhoneCode();
        } else if (id == R.id.btn_register) {
            resetPassword();
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

    private void resetPassword() {
        String phone = mEdtPhone.getText().toString();
        String code = mEdtPhoneCode.getText().toString();
        String password = mEdtPassword.getText().toString();
        mBaseFunctionAPI.resetPassword(phone, password, code);
    }

    @Override
    public void onResetResponse(String code, String message) {
        if (code.equals("200")) {
            ToastHelper.showToast(mContext, "重置密码成功");
            finish();
        } else {
            ToastHelper.showToast(mContext, message);
        }
    }

    @Override
    public void onResetFailure(String message) {
        ToastHelper.showToast(mContext, message);
    }
}
