package com.dy.cmls.view.paydialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/12/2
 * desc   : 密码遮挡自定义 View
 */
public class PasswordView extends View {

    // 密码总个数
    public static final int PASSWORD_COUNT = 6;

    // 已经输入的密码个数，也就是需要显示的小黑点个数
    private int mCurrentIndex = 0;

    private Paint mPaint;
    private Path mPath;
    private Paint mPointPaint;

    //密码框边界线的颜色值
    private int mStrokeColor = 0xFFECECEC;

    //单个密码框的高度
    private int mItemWidth = 44;

    private int mItemHeight = 41;

    //中心黑点的半径大小
    private int mPointRadius = 15;

    //中心黑点的颜色
    private int mPointColor = 0xFF666666;

    public PasswordView(Context context) {
        super(context);
        initialize(context);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
//        init();
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
//        init();
    }

    /**
     * 初始化
     */
    private void initialize(Context context) {


        mItemWidth = dp2px(mItemWidth);
        mItemHeight = dp2px(mItemHeight);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);  //设置抗锯齿
        mPaint.setColor(mStrokeColor);  //设置颜色
        mPaint.setStyle(Paint.Style.STROKE);    //设置描边



        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mItemWidth * PASSWORD_COUNT, 0);
        mPath.lineTo(mItemWidth * PASSWORD_COUNT, mItemHeight );
        mPath.lineTo(0, mItemHeight);
        mPath.close();

        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setColor(mPointColor);
    }

    private void init() {

        mItemWidth = dp2px(mItemWidth);
        mItemHeight = dp2px(mItemHeight);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);  //设置抗锯齿
        mPaint.setColor(mStrokeColor);  //设置颜色
        mPaint.setStyle(Paint.Style.STROKE);    //设置描边


        int radius=dp2px(5);

        mPath = new Path();
        mPath.moveTo(0, radius);
        RectF oval1 = new RectF(0, 0, 2 * radius, 2 * radius);
        mPath.arcTo(oval1, 180, 90);
        mPath.lineTo(mItemWidth * PASSWORD_COUNT - radius, 0);
        RectF oval2 = new RectF(mItemWidth * PASSWORD_COUNT - 2 * radius, 0, mItemWidth * PASSWORD_COUNT, 2 * radius);
        mPath.arcTo(oval2, -90, 90);
        mPath.lineTo(mItemWidth * PASSWORD_COUNT, mItemHeight - radius);
        RectF oval3 = new RectF(mItemWidth * PASSWORD_COUNT - 2 * radius, mItemHeight - 2 * radius, mItemWidth * PASSWORD_COUNT, mItemHeight);
        mPath.arcTo(oval3, 0, 90);
        mPath.lineTo(radius, mItemHeight);
        RectF oval4 = new RectF(0, mItemHeight - 2 * radius, 2 * radius, mItemHeight);
        mPath.arcTo(oval4, 90, 90);
        mPath.close();

        mPointPaint = new Paint();
        mPointPaint.setAntiAlias(true);
        mPointPaint.setStyle(Paint.Style.FILL);
        mPointPaint.setColor(mPointColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            return size;
        } else {
            return mItemWidth * PASSWORD_COUNT;
        }
    }

    private int measureHeight(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            return size;
        } else {
            return mItemHeight;
        }
    }

    public int dp2px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(5);
        old(canvas);
//        newCanvas(canvas);

    }

    private void old(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
        drawDivide(canvas);
        drawBlackPoint(canvas);
    }

    //有bug
    private void newCanvas(Canvas canvas) {
        int radius = dp2px(5);
        int radius2 = dp2px(5) - 5;

        RectF oval1 = new RectF(0, 0, mItemWidth * PASSWORD_COUNT, mItemHeight);
        canvas.drawRoundRect(oval1, radius, radius, mPaint);
        mPaint.setColor(Color.parseColor("#ffffffff"));  //设置颜色
        mPaint.setStrokeWidth(dp2px(40));
        RectF oval2 = new RectF(5, 5, mItemWidth * PASSWORD_COUNT - 5, mItemHeight - 5);
        canvas.drawRoundRect(oval2, radius2, radius2, mPaint);
        mPaint.setColor(mStrokeColor);  //设置颜色
        drawDivide(canvas);
        drawBlackPoint(canvas);
    }

    /**
     * 画单个的分割线
     */
    private void drawDivide(Canvas canvas) {

        mPaint.setStrokeWidth(3);
        for (int index = 1; index < PASSWORD_COUNT; index++) {
            canvas.drawLine(mItemWidth * index, 0, mItemWidth * index, mItemHeight, mPaint);
        }
    }

    /**
     * 绘制中间的小黑点
     */
    private void drawBlackPoint(Canvas canvas) {
        if (mCurrentIndex == 0) {
            return;
        }
        for (int i = 1; i <= mCurrentIndex; i++) {
            canvas.drawCircle(i * mItemWidth - mItemWidth / 2, mItemHeight / 2, mPointRadius, mPointPaint);
        }
    }

    /**
     * 改变密码提示小黑点的个数
     */
    public void setPassWord(int index) {
        mCurrentIndex = index;
        invalidate();
    }
}
