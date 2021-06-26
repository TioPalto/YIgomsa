package com.example.testapplication.activity;

import com.example.testapplication.http.OkHttpMgr;
import com.example.testapplication.util.SpUtil;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SpUtil.getInstance().init(this.getApplicationContext());
        OkHttpMgr.getInstance().init("http://49.234.152.112:8080/");
    }
}
