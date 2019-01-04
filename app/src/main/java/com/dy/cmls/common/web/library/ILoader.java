package com.dy.cmls.common.web.library;

/**
 * Created by cenxiaozhong on 2017/6/3.
 */

public interface ILoader {


    void loadUrl(String url);

    void reload();

    void loadData(String data, String mimeType, String encoding);

    void stopLoading();

    void loadDataWithBaseURL(String baseUrl, String data,
                             String mimeType, String encoding, String historyUrl);

}
