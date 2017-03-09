package com.sudao.basemodule.component.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Samuel on 16/7/22 09:58
 * Email:xuzhou40@gmail.com
 * desc:提示对话框
 */
public class DialogHelper {
    private static final int DEFAULT = 0;//系统默认样式
    private static final int IOS = 1;//iOS样式
    private static final int UBER = 2;//Uber样式
    private static final String DEFAULT_CONFIRM = "确定";
    private static final String DEFAULT_CANCEL = "取消";

    /*
     * 参数说明
     *
     * @param mContext              非空
     * @param title                提示框标题,非空
     * @param message              提示信息,非空
     * @param confirm              确认按钮文字,非空
     * @param cancel               取消按钮文字,非空
     * @param confirmClickListener 确认按钮监听器 null代表没有事件
     * @param cancelClickListener  取消按钮监听器 null代表没有事件
     */

    public static void alertConfirmTitle(Context context, String title, String message, DialogInterface.OnClickListener confirmClickListener) {
        alertConfirmTitle(context, title, message, DEFAULT_CONFIRM, confirmClickListener, 0);
    }

    public static void alertConfirmTitle(Context context, String title, String message, String confirm, DialogInterface.OnClickListener confirmClickListener) {
        alertConfirmTitle(context, title, message, confirm, confirmClickListener, 0);
    }

    /**
     * @param context
     * @param title                标题
     * @param message              提示内容
     * @param confirm              确定按钮文字
     * @param confirmClickListener 确定点击事件，null 则没有点击事件
     * @param style                提示框样式
     */
    public static void alertConfirmTitle(Context context, String title, String message, String confirm, DialogInterface.OnClickListener confirmClickListener, int style) {
        switch (style) {
            case DEFAULT:
                new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton(confirm, confirmClickListener)
                        .show();
                break;
            case IOS:
                CustomIOSDialog.Builder builder = new CustomIOSDialog.Builder(context);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.setPositiveButton(confirm, confirmClickListener);
                builder.show();
                break;
            case UBER:
                CustomUberDialog.Builder builder1 = new CustomUberDialog.Builder(context);
                builder1.setTitle(title);
                builder1.setMessage(message);
                builder1.setPositiveButton(confirm, confirmClickListener);
                builder1.show();
                break;
        }
    }


    public static void alertDialogTitle(Context context, String title, String message, DialogInterface.OnClickListener confirmClickListener) {
        alertDialogTitle(context, title, message, DEFAULT_CONFIRM, DEFAULT_CANCEL, confirmClickListener, null, 0);
    }

    public static void alertDialogTitle(Context context, String title, String message, DialogInterface.OnClickListener confirmClickListener, int style) {
        alertDialogTitle(context, title, message, DEFAULT_CONFIRM, DEFAULT_CANCEL, confirmClickListener, null, style);
    }

    public static void alertDialogTitle(Context context, String title, String message, DialogInterface.OnClickListener confirmClickListener, DialogInterface.OnClickListener cancelClickListener) {
        alertDialogTitle(context, title, message, DEFAULT_CONFIRM, DEFAULT_CANCEL, confirmClickListener, cancelClickListener, 0);
    }

    public static void alertDialogTitle(Context context, String title, String message, DialogInterface.OnClickListener confirmClickListener, DialogInterface.OnClickListener cancelClickListener, int style) {
        alertDialogTitle(context, title, message, DEFAULT_CONFIRM, DEFAULT_CANCEL, confirmClickListener, cancelClickListener, style);
    }

    public static void alertDialogTitle(Context context, String title, String message, String confirm, String cancel, DialogInterface.OnClickListener confirmClickListener, DialogInterface.OnClickListener cancelClickListener) {
        alertDialogTitle(context, title, message, confirm, cancel, confirmClickListener, cancelClickListener, 0);
    }

    /**
     * @param context
     * @param title                标题
     * @param message              提示内容
     * @param confirm              确认按钮文字
     * @param cancel               取消按钮文字
     * @param confirmClickListener 确认按钮事件
     * @param cancelClickListener  取消按钮事件
     * @param style                对话框样式
     */
    public static void alertDialogTitle(Context context, String title, String message, String confirm, String cancel, DialogInterface.OnClickListener confirmClickListener, DialogInterface.OnClickListener cancelClickListener, int style) {
        switch (style) {
            case DEFAULT:
                new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(message)
                        .setCancelable(false)
                        .setPositiveButton(confirm, confirmClickListener)
                        .setNegativeButton(cancel, cancelClickListener)
                        .show();
                break;
            case IOS:
                CustomIOSDialog.Builder builder = new CustomIOSDialog.Builder(context);
                builder.setTitle(title);
                builder.setMessage(message);
                builder.setPositiveButton(confirm, confirmClickListener);
                builder.setNegativeButton(cancel, cancelClickListener);
                builder.show();
                break;
            case UBER:
                CustomUberDialog.Builder builder1 = new CustomUberDialog.Builder(context);
                builder1.setTitle(title);
                builder1.setMessage(message);
                builder1.setPositiveButton(confirm, confirmClickListener);
                builder1.setNegativeButton(cancel, cancelClickListener);
                builder1.show();
                break;
        }
    }
}
