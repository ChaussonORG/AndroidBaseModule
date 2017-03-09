package com.sudao.basemodule.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sudao.basemodule.common.util.AppManager;
import com.sudao.basemodule.common.util.LogUtil;
import com.sudao.basemodule.component.dialog.CustomIOSDialog;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * @Description: （ui基类）
 **/
public abstract class BaseActivity extends AppCompatActivity {
    public String TAG = BaseActivity.class.getSimpleName();
    public AppCompatActivity mContext;
    // 进度对话框
    public CustomIOSDialog mProgressDialog = null;

    protected boolean SCREEN_ORIENTATION_PORTRAIT = true;// 是否禁止横竖屏切换

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if (SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 禁止横屏
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//        }

        mContext = this;
        LogUtil.d(this.getClass().getSimpleName() + "启动了");
        layout();
        ButterKnife.bind(this);
        initView();
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);

        if (getSupportActionBar() != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSupportActionBar().setElevation(0);
        }
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            CustomIOSDialog.Builder builder = new CustomIOSDialog.Builder(mContext);
            builder.setMessage("请等待");
            mProgressDialog = builder.createIOSProgressDialog();
        }
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public abstract void layout();

    public abstract void initView();


    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    // 空方法，没有任何用处，不能删除
    @Subscribe
    public void onEventMainThread(StanceEventbus object) {

    }

    //点击左上角返回键，相当于按下 back 键
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(this.getClass().getSimpleName() + "销毁了");
//        EventBus.getDefault().unregister(this);
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
    }

}
