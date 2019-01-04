package com.dy.cmls.base.http;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author lcj
 * @date 2017/12/29 15:12
 *
 * OkHttp 公共参数拦截器
 * from: https://github.com/jkyeo/okhttp-basicparamsinterceptor.git
 */
public class BasicParamsInterceptor implements Interceptor {
    private final String mApiKey;
    private final String mApiSecret;

    public BasicParamsInterceptor(String apiKey, String apiSecret) {
        mApiKey = apiKey;
        mApiSecret = apiSecret;
    }

    @Override public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
            .newBuilder()
            .scheme(oldRequest.url().scheme())
            .host(oldRequest.url().host())
            .addQueryParameter(mApiKey, mApiSecret);

        // 新的请求
        Request newRequest =
            oldRequest.newBuilder().method(oldRequest.method(), oldRequest.body()).url(authorizedUrlBuilder.build()).build();

        return chain.proceed(newRequest);
    }
}
