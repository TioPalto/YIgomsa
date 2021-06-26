package com.example.testapplication;

import android.Manifest;


public class PermissionParams {
    public static final String READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final String WRITE_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;//通过网络得到粗略位置
    public static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;//通过GPS得到精确位置
    public static final String CAMERA = Manifest.permission.CAMERA;//相机权限
    public static final String ACCESS_WIFI_STATE = Manifest.permission.ACCESS_WIFI_STATE;
    public static final String ACCESS_NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE;
    public static final String CHANGE_WIFI_STATE = Manifest.permission.CHANGE_WIFI_STATE;

    public static String[] PERMISSIONS = new String[] {
            READ_STORAGE, READ_PHONE_STATE, WRITE_STORAGE, ACCESS_WIFI_STATE,
            COARSE_LOCATION, FINE_LOCATION, CAMERA, CHANGE_WIFI_STATE, ACCESS_NETWORK_STATE
    };

}
