package com.dy.cmls.base.http;





import com.dy.cmls.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lcj
 * @date 2017/12/29 15:12
 */

public class RetrofitServiceManagerJiaShiZheng {
    //超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private static final int DEFAULT_TIME_OUT = 5;
    private Retrofit mRetrofit;
    private String urlName = "";

    private RetrofitServiceManagerJiaShiZheng() {
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //连接超时时间
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        //写操作超时时间
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        //读操作超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);

        //打印log
        HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override public void log(String message) {
                    if (!message.contains("Content-Type")
                        && !message.contains("Content-Length")
                        && !message.contains("END")
                        && !message.contains("<--")
                        && !message.contains("Cache-Control")
                        && !message.contains("Pragma")
                        && !message.contains("Expires")
                        && !message.contains("Vary")
                        && !message.contains("Server")
                        && !message.contains("Set-Cookie")
                        && !message.contains("X-Powered-By")
                        && !message.contains("Date")
                        && !message.contains("Keep-Alive")
                        && !message.contains("Connection")
                        && !message.contains("Transfer-Encoding")
                        && !message.isEmpty()) {
                        LogUtil.e("请求" + urlName + ":", message);
                    } else if (message.contains("{")) {
                        LogUtil.e("返回:", message);
                    }
                }
            });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        // 创建Retrofit
        mRetrofit = new Retrofit.Builder().client(builder.build())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://v.juhe.cn/driverLicense/")
            .build();
    }

    private static class SingletonHolder {
        private static final RetrofitServiceManagerJiaShiZheng
            INSTANCE = new RetrofitServiceManagerJiaShiZheng();
    }

    /**
     * 获取RetrofitServiceManager
     */
    public static RetrofitServiceManagerJiaShiZheng getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }
}
