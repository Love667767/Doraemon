package com.elson.doraemon;

import com.alibaba.android.arouter.launcher.ARouter;
import com.elson.basecore.BaseApp;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import okhttp3.OkHttpClient;

/**
 * Created by elson on 2018/1/17
 */

public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
//        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        HttpHeaders headers = new HttpHeaders();
        HttpParams params = new HttpParams();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .addCommonHeaders(headers)
                .addCommonParams(params)
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3);
    }


}
