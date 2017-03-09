package com.sudao.basemodule.component.filterrender;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sudao.basemodule.R;

import java.util.List;

/**
 * Created by Samuel on 5/17/16 15:46
 * Email:xuzhou40@gmail.com
 * desc:
 */
public class PopupRecyclerViewAdapter extends RecyclerView.Adapter<PopupRecyclerViewAdapter.PopupViewHolder> {
    public OnItemClickListener mOnItemClickListener;
    private List<String> mList;
    //    private LayoutInflater mInflater;
    private Context mContext;
    private int mSelectPosition;
    private int mTextColor = android.R.color.black;
    private int mTintColor = android.R.color.holo_blue_dark;
    private int mTickColor = android.R.color.holo_blue_dark;

    public PopupRecyclerViewAdapter(Context context, List<String> mDatas, int selectPosition) {
        mSelectPosition = selectPosition;
//        mInflater = LayoutInflater.from(mContext);
        this.mContext = context;
        this.mList = mDatas;
    }

    public void setTickColor(int tickColor) {
        mTickColor = tickColor;
    }

    public void setTintColor(int tintColor) {
        mTintColor = tintColor;
        mTickColor = tintColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public PopupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pop_menu_item, parent, false);
        return new PopupViewHolder(layoutView);

    }

    @Override
    public void onBindViewHolder(final PopupViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position));

        if (mSelectPosition == position) {
            holder.mTextView.setTextColor(ContextCompat.getColor(mContext, mTintColor));
            holder.mTickView.setVisibility(View.VISIBLE);
            holder.mTickView.setTickColor(mTickColor);
        } else {
            holder.mTextView.setTextColor(ContextCompat.getColor(mContext, mTextColor));
            holder.mTickView.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView, pos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class PopupViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        TickView mTickView;

        public PopupViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_menu_item);
            mTickView = (TickView) itemView.findViewById(R.id.tickview);
        }
    }


}
