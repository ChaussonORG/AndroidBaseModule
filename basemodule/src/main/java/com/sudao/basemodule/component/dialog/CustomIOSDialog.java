package com.sudao.basemodule.component.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.sudao.basemodule.R;


/**
 * Created by Samuel on 16/7/22 11:32
 * Email:xuzhou40@gmail.com
 * desc:仿iOS提示对话框
 */
public class CustomIOSDialog extends Dialog {
    public CustomIOSDialog(Context context) {
        super(context);
    }

    public CustomIOSDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context mContext;
        private String mTitle;
        private String mMessage;
        private String mConfirmText;
        private String mCancelText;
        private View mContentView;
        private boolean cancelable;
        private OnClickListener mConfirmClickListener;
        private OnClickListener mCancelClickListener;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setMessage(String message) {
            mMessage = message;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }


        public Builder setContentView(View contentView) {
            mContentView = contentView;
            return this;
        }

        public Builder setPositiveButton(String confirmText, OnClickListener confirmClickListener) {
            mConfirmText = confirmText;
            mConfirmClickListener = confirmClickListener;
            return this;
        }

        public Builder setNegativeButton(String cancelText, OnClickListener cancelClickListener) {
            mCancelText = cancelText;
            mCancelClickListener = cancelClickListener;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }


        private CustomIOSDialog create() {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CustomIOSDialog customIOSDialog = new CustomIOSDialog(mContext, R.style.IOSDialogStyle);
            View layout = inflater.inflate(R.layout.dialog_custom_ios, null);
            TextView tvTitle = (TextView) layout.findViewById(R.id.title);
            TextView tvMessage = (TextView) layout.findViewById(R.id.message);
            Button btnCancel = (Button) layout.findViewById(R.id.btn_cancel);
            Button btnConfirm = (Button) layout.findViewById(R.id.btn_confirm);
            View line = layout.findViewById(R.id.line);

            customIOSDialog.setCancelable(false);
            customIOSDialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tvTitle.setText(mTitle);
            tvTitle.getPaint().setFakeBoldText(true);

            if (mTitle != null || mTitle.trim().length() == 0) {
                tvMessage.setGravity(Gravity.CENTER);
            }

            if (mConfirmText != null) {
                btnConfirm.setText(mConfirmText);
                if (mConfirmClickListener != null) {
                    btnConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mConfirmClickListener.onClick(customIOSDialog, BUTTON_POSITIVE);
                            customIOSDialog.dismiss();
                        }
                    });
                } else {
                    btnConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customIOSDialog.dismiss();
                        }
                    });
                }
            } else {
                btnConfirm.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                btnCancel.setBackgroundResource(R.drawable.btn_one_ios);
            }

            if (mCancelText != null && mCancelText.length() != 0) {
                btnCancel.setText(mCancelText);
                if (mCancelClickListener != null) {
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mCancelClickListener.onClick(customIOSDialog, DialogInterface.BUTTON_NEGATIVE);
                            customIOSDialog.dismiss();

                        }
                    });
                } else {
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customIOSDialog.dismiss();
                        }
                    });
                }
            } else {
                btnCancel.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                btnConfirm.setBackgroundResource(R.drawable.btn_one_ios);
            }

            if (mMessage != null) {
                tvMessage.setText(mMessage);
            }
            customIOSDialog.setContentView(layout);

            return customIOSDialog;
        }

        public CustomIOSDialog createIOSProgressDialog() {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            CustomIOSDialog customIOSDialog = new CustomIOSDialog(mContext, R.style.IOSDialogStyle);
            View layout = inflater.inflate(R.layout.dialog_custom_ios_progress, null);
            TextView tvMessage = (TextView) layout.findViewById(R.id.message);
            tvMessage.setText(mMessage);
            customIOSDialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            customIOSDialog.setCanceledOnTouchOutside(false);
            if (!cancelable) {
                customIOSDialog.setCancelable(false);
            }

            WindowManager.LayoutParams winlp = customIOSDialog.getWindow()
                    .getAttributes();
            winlp.alpha = 0.7f; // 0.0-1.0
            customIOSDialog.getWindow().setAttributes(winlp);

            return customIOSDialog;
        }

        public void show() {
            this.create().show();
        }
    }
}
