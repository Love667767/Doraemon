package com.elson.mainbundle;

import android.support.annotation.NonNull;

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
        return 0;
    }

    @Override
    protected void initViews() {

    }
}
