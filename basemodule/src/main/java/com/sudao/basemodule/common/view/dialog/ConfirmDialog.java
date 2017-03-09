package com.sudao.basemodule.common.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.sudao.basemodule.R;


/**
 * Created by login on 2016/3/10.
 */
public class ConfirmDialog extends Dialog {
    private Context context;
    private String title;
    private String confirmButtonText;
    //         private String cacelButtonText;
    private ClickListenerInterface clickListenerInterface;

    public ConfirmDialog(Context context, String title, String confirmButtonText) {
//                super(mContext, android.R.style.Theme);

        super(context, R.style.dialogstyle);
        setOwnerActivity((Activity) context);
        this.context = context;
        this.title = title;
        this.confirmButtonText = confirmButtonText;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_submit, null);
        setContentView(view);
        setCancelable(false);// 设置点击屏幕Dialog不消失

        TextView tvTitle = (TextView) view.findViewById(R.id.tv_dialog_submit_title);

        Button tvsubmit = (Button) view.findViewById(R.id.btn_dialog_submit);

        tvTitle.setText(title);
        tvsubmit.setText(confirmButtonText);

        tvsubmit.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    public interface ClickListenerInterface {

        public void doConfirm();
//                         public void doCancel();
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.btn_dialog_submit) {
                clickListenerInterface.doConfirm();
            }
        }

    }

    ;
}
