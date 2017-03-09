package com.sudao.basemodule.common.util;

/**
 * 控制频繁点击
 *
 * @author Administrator
 */
public class ClickUtil {
    private long delay;
    private long lastTime;

    public ClickUtil(long delay) {
        this.delay = delay;
    }

    public boolean canClick() {
        if (System.currentTimeMillis() - lastTime > delay) {
            lastTime = System.currentTimeMillis();
            return true;
        } else {
            lastTime = System.currentTimeMillis();
            return false;
        }
    }

}
