package com.dy.cmls.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dy.cmls.R;
import com.dy.cmls.utils.PixelUtil;

/**
 * @author tangji
 * @date 2017/12/29 15:12
 */

public class CircleProgressView extends View {
    private Context mContext;
    // 画圆所在的距形区域
    private RectF mRectF;
    private Paint mPaint;
    private int progressColor;//进度色
    private int backgroundColor;//背景色
    private float strokeWidth;//宽度
    private float maxProgress;//最大进度
    private float progress;//当前进度
    private int endingProgress;//绘制中剩余份数
    private float viewProgress;//绘制中进度所占角度
    private float oneProgress;//每份进度所占角度

    public CircleProgressView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        progressColor = typedArray.getColor(R.styleable.CircleProgressView_progressColor, 0xffe91306);
        backgroundColor = typedArray.getColor(R.styleable.CircleProgressView_backgroundColor, 0xfff6f6f6);
        strokeWidth = typedArray.getDimension(R.styleable.CircleProgressView_strokeWidth, 2);
        maxProgress = typedArray.getFloat(R.styleable.CircleProgressView_maxProgress, 100);
        progress = typedArray.getFloat(R.styleable.CircleProgressView_progress, 0);
        typedArray.recycle();
        oneProgress = 360f / maxProgress;
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        progressColor = typedArray.getColor(R.styleable.CircleProgressView_progressColor, 0xffe91306);
        backgroundColor = typedArray.getColor(R.styleable.CircleProgressView_backgroundColor, 0xfff6f6f6);
        strokeWidth = typedArray.getDimension(R.styleable.CircleProgressView_strokeWidth, 2);
        maxProgress = typedArray.getInteger(R.styleable.CircleProgressView_maxProgress, 100);
        progress = typedArray.getInteger(R.styleable.CircleProgressView_progress, 0);
        typedArray.recycle();
        oneProgress = 360f / maxProgress;
    }

    private void initView() {
        this.mRectF = new RectF();
        this.mPaint = new Paint();
        this.progressColor = 0xffe91306;
        this.backgroundColor = 0xfff6f6f6;
        this.strokeWidth = 4;
        this.maxProgress = 100f;
        this.progress = 0f;
        this.oneProgress = 3.6f;
        this.viewProgress = 0f;
        this.endingProgress = 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int roundWidth = PixelUtil.dp2px(mContext, strokeWidth);
        //画最外层的大圆环
        int centre = getWidth() / 2; //获取圆心的x坐标
        int radius = (int) (centre - roundWidth / 2); //圆环的半径
        mPaint.setColor(backgroundColor); //设置圆环的颜色
        mPaint.setStyle(Style.STROKE); //设置空心
        mPaint.setStrokeWidth(roundWidth); //设置圆环的宽度
        mPaint.setAntiAlias(true);  //消除锯齿
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆弧圆角
        canvas.drawCircle(centre, centre, radius, mPaint); //画出圆环

        //设置进度是实心还是空心
        mPaint.setStrokeWidth(roundWidth); //设置圆环的宽度
        mPaint.setColor(progressColor);  //设置进度的颜色
        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);
        //用于定义的圆弧的形状和大小的界限
        mPaint.setStyle(Style.STROKE);
        canvas.drawArc(oval, 270, viewProgress, false, mPaint);  //根据进度画圆弧
    }

    public synchronized float getMax() {
        return maxProgress;
    }

    /**
     * 设置进度的最大值
     */
    public synchronized void setMax(float maxProgress) {
        if (maxProgress < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.maxProgress = maxProgress;
    }

    public void startShowProgress() {
        new ProgressThread().start();
    }

    private class ProgressThread extends Thread {
        @Override
        public void run() {
            while (viewProgress <= progress * oneProgress) {
                try {
                    if (progress == 0) {
                        viewProgress = 0;
                    } else {
                        viewProgress += oneProgress;
                    }
                    endingProgress--;
                    handler.sendEmptyMessage(0);
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            invalidate();
        }
    };

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setMaxProgress(float maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
