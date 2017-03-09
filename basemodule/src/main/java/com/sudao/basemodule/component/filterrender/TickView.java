package com.sudao.basemodule.component.filterrender;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Samuel on 5/19/16 17:17
 * Email:xuzhou40@gmail.com
 * desc:绘制一个勾
 */
public class TickView extends View {
    //绘制圆弧的进度值
    private int progress = 0;
    //线1的x轴
    private int line1_x = 0;
    //线1的y轴
    private int line1_y = 0;
    //线2的x轴
    private int line2_x = 0;
    //线2的y轴
    private int line2_y = 0;
    private Context mContext;
    private int mTickColor = android.R.color.holo_purple;

    public TickView(Context context) {
        super(context);
        mContext = context;
    }

    public TickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    public TickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

    }

    public void setTickColor(int tickColor) {
        mTickColor = tickColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //  绘制圆弧

        Paint paint = new Paint();
        //设置画笔颜色
//        paint.setColor(ContextCompat.getColor(mContext, mTickColor));
        paint.setColor(ContextCompat.getColor(mContext, mTickColor));
//        paint.setColor(Color.parseColor("#ff0099cc"));
        //设置圆弧的宽度
        paint.setStrokeWidth(3);
        //设置圆弧为空心
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //消除锯齿
        paint.setAntiAlias(true);

        //获取圆心的x坐标
        int center = getWidth() / 2;
        int center1 = center - getWidth() / 5;
        //圆弧半径
        int radius = getWidth() / 2 - 5;

        //定义的圆弧的形状和大小的界限
        RectF rectF = new RectF(center - radius - 1, center - radius - 1, center + radius + 1, center + radius + 1);

        canvas.drawArc(rectF, 235, 360, false, paint);

        paint.setColor(ContextCompat.getColor(mContext, android.R.color.white));


        //画第一根线
        canvas.drawLine(center1, center, center1 + radius / 3, center + radius / 3, paint);

        //画第二根线
        canvas.drawLine(center1 + radius / 3 - 1, center + radius / 3, center1 + radius, center - 1.0f / 3 * radius, paint);

    }


}
