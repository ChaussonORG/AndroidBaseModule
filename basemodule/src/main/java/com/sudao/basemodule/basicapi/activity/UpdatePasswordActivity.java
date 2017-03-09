package com.sudao.basemodule.basicapi.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sudao.basemodule.R;
import com.sudao.basemodule.base.BaseActivity;
import com.sudao.basemodule.basicapi.BaseFunctionAPI;
import com.sudao.basemodule.basicapi.listener.OnUpdatePasswordListener;
import com.sudao.basemodule.common.util.ToastHelper;
import com.sudao.basemodule.common.util.ToolbarUtil;

public class UpdatePasswordActivity extends BaseActivity implements OnUpdatePasswordListener, View.OnClickListener {
    private EditText mEdtOldPassword;
    private EditText mEdtNewPassword;
    private EditText mEdtConfirmPassword;
    private Button mBtnUpdatePassword;
    private Toolbar mToolbar;
    private TextView mTvToolbarTitle;
    private BaseFunctionAPI mBaseFunctionAPI;

    private void assignViews() {
        mEdtOldPassword = (EditText) findViewById(R.id.edt_old_password);
        mEdtNewPassword = (EditText) findViewById(R.id.edt_new_password);
        mEdtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
        mBtnUpdatePassword = (Button) findViewById(R.id.btn_update_password);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        mBtnUpdatePassword.setOnClickListener(this);
    }


    @Override
    public void layout() {
        setContentView(R.layout.activity_update_password);
    }

    @Override
    public void initView() {
        assignViews();
        ToolbarUtil.setupToolbar(mContext, mToolbar);
        mTvToolbarTitle.setText("更改登录密码");
        mBaseFunctionAPI = new BaseFunctionAPI(mContext);
        mBaseFunctionAPI.setOnUpdatePasswordListener(this);
    }

    @Override
    public void onUpdateResponse(String code, String message) {
        if (code.equals("200")) {
            ToastHelper.showToast(mContext, "更改密码成功");
            finish();
        } else {
            ToastHelper.showToast(mContext, message);
        }
    }

    @Override
    public void onUpdateFailure(String message) {
        ToastHelper.showToast(mContext, message);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_update_password) {
            updatepassword();
        }
    }

    private void updatepassword() {
        String oldPassword = mEdtOldPassword.getText().toString();
        String newPassword = mEdtNewPassword.getText().toString();
        String confirmPassword = mEdtConfirmPassword.getText().toString();
        mBaseFunctionAPI.updatePassword(oldPassword, newPassword, confirmPassword);
    }
}
