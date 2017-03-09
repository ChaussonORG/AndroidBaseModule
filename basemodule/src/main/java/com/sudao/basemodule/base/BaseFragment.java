package com.sudao.basemodule.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sudao.basemodule.common.util.AppManager;
import com.sudao.basemodule.common.util.LogUtil;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {
    public static final String TAG = BaseFragment.class.getSimpleName();
    public AppCompatActivity mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        LogUtil.d(TAG + "--创建了");
        ButterKnife.bind(this, view);
        this.mContext = (AppCompatActivity) getActivity();
        AppManager.getAppManager().addActivity(getActivity());
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d(TAG + "--销毁了");
//        EventBus.getDefault().unregister(this);
    }

    public abstract int getLayoutId();

    public abstract void initView();

    @Subscribe
    public void onEventMainThread(StanceEventbus object) {

    }
}
