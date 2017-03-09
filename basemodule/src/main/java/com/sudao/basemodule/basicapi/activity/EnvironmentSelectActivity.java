package com.sudao.basemodule.basicapi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sudao.basemodule.R;
import com.sudao.basemodule.base.BundleConst;
import com.sudao.basemodule.common.util.SPHelper;

public class EnvironmentSelectActivity extends AppCompatActivity implements View.OnClickListener {

    private String mBaseUrl;
    private String mBaseChatUrl;
    private TextView mTvCurrentEnvironment, mTvFormalEnvironment, mTvTestEnvironment, self;
    private EditText mEdtEnvironment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_select);
        initView();
    }

    private void initView() {
        mTvCurrentEnvironment = (TextView) findViewById(R.id.tv_now_enviroment);
        findViewById(R.id.tv_test_enviroment).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_submit).setOnClickListener(this);
        mTvFormalEnvironment = (TextView) findViewById(R.id.tv_formal_enviroment);
        mTvTestEnvironment = (TextView) findViewById(R.id.tv_test_enviroment);
        self = (TextView) findViewById(R.id.tv_self_enviroment);
        mEdtEnvironment = (EditText) findViewById(R.id.et_enviroment);
        mTvFormalEnvironment.setOnClickListener(this);
        mTvTestEnvironment.setOnClickListener(this);
        self.setOnClickListener(this);
        mEdtEnvironment.setEnabled(false);
        setView();
    }

    private void setView() {
        mTvCurrentEnvironment.setText(BundleConst.getUrl());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.tv_formal_enviroment) {
            mEdtEnvironment.setText("");
            mEdtEnvironment.setEnabled(false);
            mBaseUrl = BundleConst.BASE_URL;
            mBaseChatUrl = BundleConst.CHAT_BASE_URL;

            mTvFormalEnvironment.setClickable(false);
            mTvFormalEnvironment.setTextColor(getResources().getColor(R.color.colorFB979694));
            mTvTestEnvironment.setClickable(true);
            mTvTestEnvironment.setTextColor(getResources().getColor(R.color.colorFF31B5F2));
            self.setClickable(true);
            self.setTextColor(getResources().getColor(R.color.colorFF31B5F2));
            mTvCurrentEnvironment.setText(mBaseUrl);
        } else if (id == R.id.tv_test_enviroment) {
            mEdtEnvironment.setText("");
            mEdtEnvironment.setEnabled(false);
            mTvFormalEnvironment.setClickable(true);
            mTvFormalEnvironment.setTextColor(getResources().getColor(R.color.colorFF31B5F2));
            mTvTestEnvironment.setClickable(false);
            mTvTestEnvironment.setTextColor(getResources().getColor(R.color.colorFB979694));
            self.setClickable(true);
            self.setTextColor(getResources().getColor(R.color.colorFF31B5F2));
            mBaseUrl = BundleConst.BASE_URL_TEST;
            mBaseChatUrl = BundleConst.CHAT_BASE_URL_TEST;
            mTvCurrentEnvironment.setText(mBaseUrl);
        } else if (id == R.id.tv_self_enviroment) {
            mEdtEnvironment.setEnabled(true);
            mTvFormalEnvironment.setClickable(true);
            mTvFormalEnvironment.setTextColor(getResources().getColor(R.color.colorFF31B5F2));
            mTvTestEnvironment.setClickable(true);
            mTvTestEnvironment.setTextColor(getResources().getColor(R.color.colorFF31B5F2));
            self.setClickable(false);
            self.setTextColor(getResources().getColor(R.color.colorFB979694));
        } else if (id == R.id.btn_cancel) {
            finish();
            overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
        } else if (id == R.id.btn_submit) {
            String s = mEdtEnvironment.getText().toString().trim();
            if (s.length() > 0 && s.startsWith("http")) {
                mBaseUrl = mEdtEnvironment.getText().toString();
            } else {
                mBaseUrl = mTvCurrentEnvironment.getText().toString();
            }
            SPHelper.putString(BundleConst.BASE_ENVIRONMENT, mBaseUrl);
            SPHelper.putString(BundleConst.BASE_CHAT_ENVIRONMENT, mBaseChatUrl);

            finish();
            overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fade, R.anim.hold);
    }
}
