package com.elson.mainbundle;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.elson.basecore.base.BaseActivity;
import com.elson.basecore.base.BasePresenter;

import butterknife.OnClick;

/**
 * Created by apple on 2018/1/19.
 */
@Route(path = "/main/activity/main")
public class MainActivity extends BaseActivity {

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void initViews() {

        // 获取Fragment
        Fragment fragment = (Fragment) ARouter.getInstance().build("/movie/movielist").navigation();
//        Fragment fragment = (Fragment) ARouter.getInstance().build("/main/guide").withInt("index", 0).navigation();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commitAllowingStateLoss();

    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @OnClick(R2.id.textView)
    public void onViewClicked() {
        ARouter.getInstance().build("/movie/activity").greenChannel().navigation(this, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                Log.d("LogInterceptor", "onFound");
            }

            @Override
            public void onLost(Postcard postcard) {
                Log.d("LogInterceptor", "onLost");
            }

            @Override
            public void onArrival(Postcard postcard) {
                Log.d("LogInterceptor", "onArrival");
            }

            @Override
            public void onInterrupt(Postcard postcard) {

                Log.d("LogInterceptor", "我再拦截器外部");
            }
        });
    }
}
