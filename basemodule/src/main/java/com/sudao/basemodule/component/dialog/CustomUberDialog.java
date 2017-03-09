package com.sudao.basemodule.component.dialog;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sudao.basemodule.R;

/**
 * Created by Samuel on 16/7/22 11:32
 * Email:xuzhou40@gmail.com
 * desc:仿Uber提示对话框
 */
public class CustomUberDialog extends Dialog {
    public CustomUberDialog(Context context) {
        super(context);
    }

    public CustomUberDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context mContext;
        private String mTitle;
        private String mMessage;
        private String mConfirmText;
        private String mCancelText;
        private View mContentView;
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

            return this;
        }


        private CustomUberDialog create() {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CustomUberDialog customUberDialog = new CustomUberDialog(mContext, R.style.UberDialogStyle);
            View layout = inflater.inflate(R.layout.dialog_custom_uber, null);
            TextView tvTitle = (TextView) layout.findViewById(R.id.title);
            TextView tvMessage = (TextView) layout.findViewById(R.id.message);
            Button btnCancel = (Button) layout.findViewById(R.id.btn_cancel);
            Button btnConfirm = (Button) layout.findViewById(R.id.btn_confirm);
            View line = layout.findViewById(R.id.line);

            customUberDialog.setCancelable(false);
            customUberDialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
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
                            mConfirmClickListener.onClick(customUberDialog, BUTTON_POSITIVE);
                            customUberDialog.dismiss();
                        }
                    });
                } else {
                    btnConfirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customUberDialog.dismiss();
                        }
                    });
                }
            } else {
                btnConfirm.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                btnCancel.setBackgroundResource(R.drawable.btn_one_uber);
            }

            if (mCancelText != null && mCancelText.length() != 0) {
                btnCancel.setText(mCancelText);
                if (mCancelClickListener != null) {
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mCancelClickListener.onClick(customUberDialog, DialogInterface.BUTTON_NEGATIVE);
                            customUberDialog.dismiss();

                        }
                    });
                } else {
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customUberDialog.dismiss();
                        }
                    });
                }
            } else {
                btnCancel.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                btnConfirm.setBackgroundResource(R.drawable.btn_one_uber);
            }

            if (mMessage != null) {
                tvMessage.setText(mMessage);
            }
            customUberDialog.setContentView(layout);

            return customUberDialog;
        }

        public void show() {
            this.create().show();
        }
    }
}
