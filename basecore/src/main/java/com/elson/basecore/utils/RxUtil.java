package com.elson.basecore.utils;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Observable的创建类
 * Created by elson on 2017/6/7
 */

public class RxUtil {

    /**
     * 倒计时
     *
     * @param seconds : 单位(秒)
     * @return
     */
    public static Observable<Long> countDownObservable(int seconds) {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(seconds + 1)
                .map(aLong -> seconds - aLong);
    }

    /**
     * 延时发送
     * @param seconds : 单位(秒)
     * @return
     */
    public static Observable<Long> timer(int seconds) {
        return Observable.timer(seconds, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 延时发送
     * @param milliSeconds : 单位(毫秒)
     * @return
     */
    public static Observable<Long> timerMilli(int milliSeconds) {
        return Observable.timer(milliSeconds, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
