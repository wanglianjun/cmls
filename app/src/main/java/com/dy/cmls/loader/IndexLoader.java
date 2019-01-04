package com.dy.cmls.loader;

import com.dy.cmls.base.http.ObjectLoader;
import com.dy.cmls.base.http.RetrofitServiceManager;
import com.dy.cmls.loader.bean.IndexBannerBean;
import com.dy.cmls.loader.bean.NewsListBean;
import com.dy.cmls.loader.bean.UserInfoBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lcjing on 2019/1/3.
 *
 * 接口地址 https://www.showdoc.cc/cmls?page_id=1341602581052771
 */

public class IndexLoader  extends ObjectLoader {
    private static final IndexLoader INSTANCE = new IndexLoader();
    private static final ThisService thisService = RetrofitServiceManager.getInstance().create(ThisService.class);

    public static IndexLoader getInstance() {
        return INSTANCE;
    }

    public Observable<IndexBannerBean> getIndexBanner(String urlName) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getIndexBanner());
    }

    public Observable<NewsListBean> getNews(String urlName) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getNews());
    }


    public interface ThisService {

        //首页-banner
        @POST("Index/banner")
        Observable<IndexBannerBean> getIndexBanner();

//        @FormUrlEncoded
        @POST("News/index")
        Observable<NewsListBean> getNews();

    }
}
