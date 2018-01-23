package com.sx.easytalk.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hyphenate.util.DensityUtil;
import com.sx.easytalk.R;

/**
 * @Author sunxin
 * @Date 2018/1/6 17:34
 * @Description 自定义字母栏
 */

public class SlideBar extends View {

    public static final String[] LETTERS = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"
            , "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int mAvgHeight;
    private int mAvgWidth;
    private Paint mPaint;


    public SlideBar(Context context) {
        this(context, null);
    }

    public SlideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(DensityUtil.sp2px(getContext(), 10));
        mPaint.setColor(getResources().getColor(R.color.inActive));
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    public SlideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();

        mAvgWidth = measuredWidth / 2;
        mAvgHeight = measuredHeight / LETTERS.length;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < LETTERS.length; i++) {
            canvas.drawText(LETTERS[i], mAvgWidth, mAvgHeight * (i + 1),mPaint);
        }
    }
}
