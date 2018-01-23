package com.elson.mainbundle.login;

import android.Manifest;
import android.view.WindowManager;

import com.elson.basecore.base.BaseActivity;
import com.elson.basecore.base.BasePresenter;
import com.elson.basecore.navigator.Navigator;
import com.elson.basecore.utils.PermissionUtil;
import com.elson.basecore.utils.RxUtil;
import com.elson.basecore.utils.SPUtil;
import com.elson.mainbundle.MainActivity;
import com.elson.mainbundle.R;
import com.tbruyelle.rxpermissions.RxPermissions;


/**
 * 欢迎界面
 * Created by elson on 2017/4/27
 */

public class SplashActivity extends BaseActivity {

    public static final String IS_FIRST_LOGIN = "isFirstLogin";

    @Override
    protected int getLayoutID() {
        return R.layout.main_activity_splash;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @Override
    protected void initViews() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION, //定位
                Manifest.permission.WRITE_EXTERNAL_STORAGE,//外部存储卡
                Manifest.permission.READ_PHONE_STATE};// 读取手机状态

        RxPermissions rxPermissions = new RxPermissions(this);
        PermissionUtil.requestPermission(
                rxPermissions,
                permissions,
                isGranted -> {
                    RxUtil.timer(2)
                            .subscribe(o -> initFirstLogin());
                });
    }

    private void initFirstLogin() {
        boolean isFirstLogin = (boolean) SPUtil.get(this, IS_FIRST_LOGIN, true);
        if (isFirstLogin) { //跳转到引导页面,
            Navigator.navigationThenKill(this, GuideActivity.class);
        } else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            Navigator.navigationThenKill(this, MainActivity.class);
        }
    }

}
