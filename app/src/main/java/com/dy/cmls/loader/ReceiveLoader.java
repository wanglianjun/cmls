package com.dy.cmls.loader;

import com.dy.cmls.base.http.ObjectLoader;
import com.dy.cmls.base.http.RetrofitServiceManager;
import com.dy.cmls.loader.bean.BankListBean;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by lcjing on 2019/1/4.
 */

public class ReceiveLoader  extends ObjectLoader {
    private static final ReceiveLoader INSTANCE = new ReceiveLoader();
    private static final ThisService thisService = RetrofitServiceManager.getInstance().create(ThisService.class);

    public static ReceiveLoader getInstance() {
        return INSTANCE;
    }

    public Observable<BankListBean> getBank(String urlName) {
        RetrofitServiceManager.getInstance().setUrlName(urlName);
        return observe(thisService.getBank());
    }

    public interface ThisService{

        @POST("User/bank")
        Observable<BankListBean> getBank();
    }
}
