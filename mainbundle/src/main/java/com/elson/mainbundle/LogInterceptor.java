package com.elson.mainbundle;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * Created by apple on 2018/1/23.
 */
@Interceptor(name = "logIntercepter", priority = 4)
public class LogInterceptor implements IInterceptor {

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Log.d("LogInterceptor", "我再拦截器内部");
        callback.onInterrupt(null);
    }

    @Override
    public void init(Context context) {

    }
}
