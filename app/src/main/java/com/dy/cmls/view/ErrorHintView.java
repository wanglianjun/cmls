package com.dy.cmls.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dy.cmls.R;
import com.dy.cmls.view.interfaces.IStrategy;


/**
 * Created by Administrator on 2016/11/15 0015.
 */

public class ErrorHintView extends RelativeLayout {
    RelativeLayout mContainer;
    LayoutParams layoutParams;
    private AnimationDrawable animationDrawable;
    private ErrorHandler mErrorHandler = new ErrorHandler();

    public interface OperateListener {
        void operate();
    }

    private OperateListener mOperateListener;

    public ErrorHintView(Context context) {
        super(context);
        init();
    }

    public ErrorHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ErrorHintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.layout_custom_error_hit_view, this);
        mContainer = (RelativeLayout) findViewById(R.id.container);
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    public void close() {
        setVisibility(View.GONE);
    }

    /***
     * 锦囊 用于实施策略,处理状态
     */
    class ErrorHandler {

        public ErrorHandler() {
        }

        public void operate(IStrategy strategy) {
            show();
            strategy.operate();
        }
    }

    /**
     * 显示加载失败UI
     *
     * @param listener
     */
    public void loadFailure(OperateListener listener) {
        this.mOperateListener = listener;
        mErrorHandler.operate(new LoadFailure());

    }

    View loadFailure;

    /**
     * 加载失败
     */
    class LoadFailure implements IStrategy {

        @Override
        public void operate() {

            if (loadFailure == null) {
                loadFailure = View.inflate(getContext(), R.layout.layout_load_failure, null);
                View view = loadFailure.findViewById(R.id.load_retry);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOperateListener.operate();
                    }
                });
            }
            mContainer.removeAllViews();
            mContainer.addView(loadFailure, layoutParams);

        }
    }

    /**
     * 显示无网络
     */
    public void netError(OperateListener listener) {
        this.mOperateListener = listener;
        mErrorHandler.operate(new NetWorkError());

    }

    View netError;

    /**
     * 无网络
     */
    class NetWorkError implements IStrategy {

        @Override
        public void operate() {


            if (netError == null) {
                netError = View.inflate(getContext(), R.layout.layout_load_wifi_failure, null);
                View view = netError.findViewById(R.id.wifi_retry);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOperateListener.operate();
                    }
                });
            }

            mContainer.removeAllViews();
            mContainer.addView(netError, layoutParams);
        }
    }

    /**
     * 显示无数据
     */
    public void noData(OperateListener listener) {
        this.mOperateListener = listener;
        mErrorHandler.operate(new NoDataError());
    }

    View noData;

    /**
     * 无数据
     */
    class NoDataError implements IStrategy {


        @Override
        public void operate() {
            if (noData == null) {
                noData = View.inflate(getContext(), R.layout.layout_load_noorder, null);
            }
            mContainer.removeAllViews();
            mContainer.addView(noData, layoutParams);

        }
    }

    View loadingdata;

    class LoadingData implements IStrategy {
        @Override
        public void operate() {
            if (loadingdata == null) {
                loadingdata = View.inflate(getContext(), R.layout.layout_load_loading, null);
            }
            ImageView iv = (ImageView) loadingdata.findViewById(R.id.loading_iv);
            mContainer.removeAllViews();
            mContainer.addView(loadingdata, layoutParams);
            showLoading(iv);
        }
    }

    /**
     * 加载数据
     */
    public void loadingData() {
        mErrorHandler.operate(new LoadingData());
    }

    /**
     * 加载数据
     */
    public void loadingBellData() {
        mErrorHandler.operate(new BellLoadingData());
    }

    /**
     * 显示动画loading
     */
    public void showLoading(ImageView iv) {
        animationDrawable = (AnimationDrawable) iv.getBackground();
        animationDrawable.start();
    }

    /**
     * 隐藏动画loading
     */
    public void hideLoading() {
        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
        close();
    }

    /**
     * 仿58同城加载数据
     */
    View bellLoadingData;

    class BellLoadingData implements IStrategy {
        @Override
        public void operate() {
            if (bellLoadingData == null) {
                bellLoadingData = View.inflate(getContext(), R.layout.layout_loading_bellloading,
                        null);
            }
            //            ImageView iv = (ImageView) bellLoadingData.findViewById(R.id.loading_iv);
            mContainer.removeAllViews();
            mContainer.addView(bellLoadingData, layoutParams);
            //            showLoading(iv);
        }
    }

    /**
     * 加载数据
     */
    public void loadBellingData() {
        mErrorHandler.operate(new BellLoadingData());
    }

    /**
     * 隐藏动画loading
     */
    public void hideBellLoading() {
        mContainer.removeAllViews();
        close();
    }
}
