package com.sudao.basemodule.component.filterrender;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.sudao.basemodule.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

/**
 * Created by Samuel on 5/18/16 18:17
 * Email:xuzhou40@gmail.com
 * desc: popupwindow 与 list 结合,下拉列表选择
 */
public class FilterRender {
    private List<String> mList;
    private int mDefaultIndex = -1;// 默认不选中
    private int mSelectPosition;

    private View mView;//调用 popupWindow 的 view
    private RecyclerView mRecyclerView;
    private PopupWindow mPopupWindow;
    private LinearLayoutManager mLinearLayoutManager;
    private PopupRecyclerViewAdapter mAdapter;
    private View mPopupWindowView;
    private OnPopupItemClickListener mOnPopupItemClickListener;
    private AppCompatActivity mContext;

    private int mTextColor = android.R.color.black;
    private int mTintColor = android.R.color.holo_blue_dark;
    private int mTickColor = android.R.color.holo_blue_dark;
    private int mBackgroundColor = android.R.color.white;

    public FilterRender(AppCompatActivity context, List<String> list, View view) {
        this.mContext = context;
        mList = list;
        mView = view;
        mSelectPosition = mDefaultIndex;
        init();
    }

    public void setList(List<String> list) {
        mList = list;
        init();
    }

    public void setView(View view) {
        mView = view;
    }

    public void setOnPopupItemClickListener(OnPopupItemClickListener onPopupItemClickListener) {
        this.mOnPopupItemClickListener = onPopupItemClickListener;
    }

    private void init() {
        initPopupWindow();
    }

    public void setTintColor(int tintColor) {
        mTintColor = tintColor;
        mTickColor = tintColor;
    }

    public boolean isShowing() {
        return mPopupWindow != null && mPopupWindow.isShowing();
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public void setTickColor(int tickColor) {
        mTickColor = tickColor;
    }

    public void setDefaultIndex(int defaultIndex) {
        mSelectPosition = defaultIndex;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
    }

    public void openPopupWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            hidePopupWindow();
        } else {
            init();
            mPopupWindow.showAsDropDown(mView);
        }
    }

    public void hidePopupWindow() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
            mOnPopupItemClickListener.onPopupDismissListener();
        }
    }

    private void initPopupWindow() {
        mPopupWindowView = mContext.getLayoutInflater().inflate(R.layout.popup_window, null);
        mPopupWindow = new PopupWindow(mPopupWindowView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(mContext, mBackgroundColor));
        mPopupWindow.setOutsideTouchable(true); // 设置popupwindow外部可点击
        mPopupWindow.setBackgroundDrawable(colorDrawable);
        mPopupWindow.setFocusable(true); // 获取焦点
        mPopupWindow.setAnimationStyle(R.style.PopupAnimation);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                hidePopupWindow();
            }
        });

        mRecyclerView = (RecyclerView) mPopupWindowView.findViewById(R.id.recyclerview);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mContext)
                .margin(20, 20)
                .size(1)
                .build());//设置 divider 分割线

        mAdapter = new PopupRecyclerViewAdapter(mContext, mList, mSelectPosition);
        mAdapter.setTextColor(mTextColor);
        mAdapter.setTintColor(mTintColor);
        mAdapter.setTickColor(mTickColor);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new PopupRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                mSelectPosition = position;
                mOnPopupItemClickListener.onPopupItemClick(view, position);

            }
        });

    }

    public interface OnPopupItemClickListener {
        void onPopupItemClick(View view, int position);

        void onPopupDismissListener();
    }
}
