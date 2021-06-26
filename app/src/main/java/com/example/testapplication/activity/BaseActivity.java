package com.example.testapplication.activity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeLayout();
        setContentView(contentLayout());
        int stateBarHeight = StateBarUtil.getStatusBarHeight(this);
        StateBarUtil.transparencyBar(this);
        StateBarUtil.setLightStatusBar(this, true, true);
        initView();
        afterInitView();
    }


    protected abstract int contentLayout();
    protected abstract void afterInitView();
    protected abstract void initView();
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
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            List<String> needList = new ArrayList<>();
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

    protected ArrayList<String> denyPermissionList = new ArrayList<>();

    protected boolean isPermissionChecked(String permission) {
        int state = ActivityCompat.checkSelfPermission(this, permission);
        if(state == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

}
