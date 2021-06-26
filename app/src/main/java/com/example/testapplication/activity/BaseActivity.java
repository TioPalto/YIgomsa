package com.example.testapplication.activity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.testapplication.R;
import com.example.testapplication.util.StateBarUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected static final int REQUEST_PERMISSION_CODE = 199;
    protected static final int REQUEST_CAMERA_CODE = 212;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeLayout();//在setContentView前，若需要则实现具体业务逻辑
        setContentView(contentLayout());
        int stateBarHeight = StateBarUtil.getStatusBarHeight(this);
        //Log.e("Base", "stateBarHeight=" + stateBarHeight);
        StateBarUtil.transparencyBar(this);
        //StateBarUtil.setStateBarColor(this, R.color.white);
        StateBarUtil.setLightStatusBar(this, true, true);
        initView();
        afterInitView();
    }

    //在setContentView前，若需要则实现具体业务逻辑
    protected abstract int contentLayout();
    //在setContentView后，实现具体业务逻辑
    protected abstract void afterInitView();
    protected abstract void initView();
    //setContentView需要的layoutId
    protected abstract void beforeLayout();

    protected void setLayoutPaddingTop() {
        if(findViewById(R.id.holder_layout) != null) {
            View view = findViewById(R.id.holder_layout);
            int stateBarHeight = StateBarUtil.getStatusBarHeight(this) - 18;
            view.setPadding(0, stateBarHeight, 0, 0);
            ActionBar actionbar = getSupportActionBar();
            if (actionbar != null) {
                actionbar.hide();
            }
        }
    }

    protected void requestPermissions(String[] permissions) {
        //大于6.0要动态申请权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            List<String> needList = new ArrayList<>();
            //找出需要请求权限的权限
            for(int i = 0; i < permissions.length; i++) {
                String perm = permissions[i];
                if(!isPermissionChecked(perm)) {
                    needList.add(perm);
                }
            }
            String[] needArray = needList.toArray(new String[needList.size()]);
            if(needArray != null && needArray.length > 0) {
                ActivityCompat.requestPermissions(this, needArray, REQUEST_PERMISSION_CODE);
            }
        }
    }

    //判断是否有权限未通过
    protected boolean checkPermissions(String[] permissions) {
        if(permissions!= null && permissions.length > 0) {
            for(String perm : permissions) {
                if(!isPermissionChecked(perm)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //收集申请通过不正常的权限
    protected ArrayList<String> denyPermissionList = new ArrayList<>();

    //查看某个权限是否已申请
    protected boolean isPermissionChecked(String permission) {
        int state = ActivityCompat.checkSelfPermission(this, permission);
        if(state == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

}
