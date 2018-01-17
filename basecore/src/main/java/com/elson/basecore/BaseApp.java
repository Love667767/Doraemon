package com.elson.basecore;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by elson on 2018/1/17
 */

public class BaseApp extends MultiDexApplication {

    private static BaseApp sApplication;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static BaseApp getInstance() {
        if (sApplication == null) {
            throw new RuntimeException("Application not init!");
        }
        return sApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // dex突破65535的限制
        MultiDex.install(this);
    }
}
