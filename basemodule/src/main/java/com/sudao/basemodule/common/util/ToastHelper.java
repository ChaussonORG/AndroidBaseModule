package com.sudao.basemodule.common.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 提示工具类
 */
public class ToastHelper {

    private static Toast mToast;
    private static ClickUtil clickUtil = new ClickUtil(2000);

    /**
     * 当快速弹toast时可以快速更新toast显示内容，不会造成toast文本显示滞后的问题.
     *
     * @param context    上下文
     * @param resourceId 文本资源ID
     */
    public static void showToast(Context context, int resourceId) {
        if (context == null) {
            return;
        }
        showToast(context, context.getString(resourceId));
    }

    /**
     * 显示Toast文本
     *
     * @param context 上下文
     * @param toast   要显示的文本内容 （字符串形式）
     */
    public static void showToast(Context context, String toast) {
        if (context == null) {
            return;
        }
        if (!clickUtil.canClick()) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(context, toast, Toast.LENGTH_SHORT);
        }

        mToast.setGravity(Gravity.CENTER, 0, 0);

        mToast.setText(toast);
        mToast.show();

    }
}
