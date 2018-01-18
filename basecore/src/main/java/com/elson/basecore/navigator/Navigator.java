package com.elson.basecore.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 路由导航
 * Created by elson on 2017/10/26
 */

public class Navigator {

    private Navigator() {}

    public static void navigation(Context context, Class<?> clazz) {
        navigation(context, clazz, null);
    }

    /**
     *
     * @param context
     * @param clazz
     * @param bundle
     * @param listener: 添加跳转前的条件判断
     */
    public static void navigation(Context context, Class<?> clazz, Bundle bundle, NavigationListener listener) {
        if (listener != null && listener.beforeNavigation()) {
            navigation(context, clazz, bundle);
        }
    }

    public static void navigation(Context context, Class<?> clazz, Bundle bundle) {
        checkContext(context);
        Intent intent = new Intent(context, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void navigationThenKill(Context context, Class<?> clazz) {
        navigationThenKill(context, clazz, null);
    }

    public static void navigationThenKill(Context context, Class<?> clazz, Bundle bundle) {
        checkContext(context);
        Intent intent = new Intent(context, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        ((Activity)context).finish();
    }

    public static void navigationForResult(Context context, Class<?> clazz, int requestCode) {
        navigationForResult(context, clazz, requestCode, null);
    }

    public static void navigationForResult(Context context, Class<?> clazz, int requestCode, Bundle bundle) {
        checkContext(context);
        Intent intent = new Intent(context, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        ((Activity)context).startActivityForResult(intent, requestCode);
    }

    private static void checkContext(Context context) {
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("sContext is not instanceof Activity");
        }
    }

    public interface NavigationListener {
        /**
         * @return true=允许执行， false=不允许执行
         */
        boolean beforeNavigation();
    }
}
