package com.elson.basecore;

import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by elson on 2018/1/17
 */

public class BaseApp extends MultiDexApplication {

    protected static Context sContext;
    protected static Handler sHandler;
    protected static int sMainThreadId;
    private static BaseApp sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        sHandler = new Handler();
        sMainThreadId = android.os.Process.myTid();
    }

    public static synchronized BaseApp getInstance() {
        return sApp;
    }

    public static Context getsContext() {
        return sContext;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return sHandler;
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return sMainThreadId;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // dex突破65535的限制
        MultiDex.install(this);
    }
}
