package com.elson.basecore.utils;

import android.Manifest;

import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

/**
 * 权限校验
 *
 */

public class PermissionUtil {

    private PermissionUtil() {}


    /**
     * 权限校验
     * @param rxPermissions：实际校验权限的类
     * @param permissions：所需校验的权限集
     * @param action
     */
    public static void requestPermission(RxPermissions rxPermissions, String[] permissions, Action1<Boolean> action) {
        rxPermissions.request(permissions).subscribe(action);
    }


    /**
     * 请求摄像头权限
     */
    public static void launchCamera(RxPermissions rxPermissions, Action1<Boolean> action) {
        //先确保是否已经申请过摄像头，和写入外部存储的权限
//        boolean isPermissionsGranted = rxPermissions.isGranted(Manifest.permission.CAMERA)
//                 && rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        requestPermission(rxPermissions, permissions, action);
    }


    /**
     * 请求外部存储的权限
     */
    public static void externalStorage(RxPermissions rxPermissions, Action1<Boolean> action) {
        //先确保是否已经申请过摄像头，和写入外部存储的权限
//        boolean isPermissionsGranted = rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        requestPermission(rxPermissions, permissions, action);
    }


    /**
     * 请求发送短信权限
     */
    public static void sendSms(RxPermissions rxPermissions, Action1<Boolean> action) {
        //先确保是否已经申请过权限
//        boolean isPermissionsGranted = rxPermissions.isGranted(Manifest.permission.SEND_SMS);

        String[] permissions = {Manifest.permission.SEND_SMS};
        requestPermission(rxPermissions, permissions, action);
    }


    /**
     * 请求打电话权限
     */
    public static void callPhone(RxPermissions rxPermissions, Action1<Boolean> action) {
        //先确保是否已经申请过权限
//        boolean isPermissionsGranted = rxPermissions.isGranted(Manifest.permission.CALL_PHONE);

        String[] permissions = {Manifest.permission.CALL_PHONE};
        requestPermission(rxPermissions, permissions, action);
    }


    /**
     * 请求获取手机状态的权限
     */
    public static void readPhoneState(RxPermissions rxPermissions, Action1<Boolean> action) {
        String[] permissions = {Manifest.permission.CALL_PHONE};
        requestPermission(rxPermissions, permissions, action);
    }

}

