package com.elson.mainbundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.elson.basecore.base.BaseActivity;
import com.elson.basecore.base.BasePresenter;

/**
 * Created by apple on 2018/1/19.
 */

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
}
