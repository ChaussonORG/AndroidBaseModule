package com.sudao.basemodule.common.view;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 倒计时 http://blog.sina.com.cn/s/blog_670bfea20101h2gf.html
 *
 * @author xiaolong
 */
public class CountDown extends CountDownTimer {
    private TextView view;

    public CountDown(long millisInFuture, long countDownInterval, TextView view) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.view = view;
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        view.setText("获取验证码");
        view.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        view.setClickable(false);
        view.setText("(" + millisUntilFinished / 1000 + "s" + ")");
    }

}
