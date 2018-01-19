package com.elson.basecore.utils.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * <pre>
 * Activity相关工具类
 *
 * 功能：
 * 1. 将Activity加入栈中
 * 2. 获取当前Activity
 * 3. 关闭当前Activity
 * 4. 关闭栈中所有Activity
 * 5. 关闭指定类名的Activity
 * 6. 关闭指定Activity之前的所有Activity
 * 7. 退出应用程序
 * </pre>
 *
 * Created by elson on 2018/1/19
 */
public class ActivityUtil {

    private Stack<Activity> activityStack = new Stack<Activity>();

    private static class Holder {
        static ActivityUtil INSTANCE = new ActivityUtil();
    }

    /**
     * 单一实例
     */
    public static ActivityUtil getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finish() {
        Activity activity = activityStack.lastElement();
        finish(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finish(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finish(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finish(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAll() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 关闭指定Activity之前的所有Activity
     *
     * @param clazz
     */
    public boolean backToActivity(Activity clazz) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(clazz)) {
                return true;
            } else {
                activity.finish();
            }
        }
        return false;
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAll();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public boolean isAppExit() {
        return activityStack == null || activityStack.isEmpty();
    }

}
