package com.sudao.basemodule.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.sudao.basemodule.common.util.LogUtil;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * @Description: （view 基类）
 **/
public abstract class BaseView extends RelativeLayout {
    protected AppCompatActivity mContext;

    public BaseView(Context context) {
        super(context);
        this.mContext = (AppCompatActivity) context;
        layout(context);
        ButterKnife.bind(this);
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = (AppCompatActivity) context;
        layout(context);
        ButterKnife.bind(this);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = (AppCompatActivity) context;
        layout(context);
        ButterKnife.bind(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.d(this.getClass().getSimpleName() + "--onAttachedToWindow");
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
        initView();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtil.d(this.getClass().getSimpleName() + "--onDetachedFromWindow");
//        EventBus.getDefault().unregister(this);
    }

    // 空方法，没有任何用处，不能删除
    @Subscribe
    public void onEventMainThread(StanceEventbus object) {

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    protected abstract void layout(Context context);

    public abstract void initView();

}
