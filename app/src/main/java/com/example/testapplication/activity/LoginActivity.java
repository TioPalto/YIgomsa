package com.example.testapplication.activity;

import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapplication.MainActivity;
import com.example.testapplication.R;
import com.example.testapplication.bena.LoginBean;
import com.example.testapplication.http.GsonUtil;
import com.example.testapplication.http.OkHttpMgr;
import com.example.testapplication.http.OkMsgCallback;
import com.example.testapplication.util.SpUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {
    private String name = "admin";  //对应的用户名以及密码
    private String password = "123456";
    private EditText editText1;  //用户名输入
    private EditText editText2;  //密码输入
    private static String TAG = "LoginActivity";
    private Button btnLogin;

    @Override
    protected int contentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void afterInitView() {

    }

    @Override
    protected void initView() {
        editText1 = (EditText) findViewById(R.id.login_name1);
        editText2 = (EditText) findViewById(R.id.login_password1);
        btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(this);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }
    }

    @Override
    protected void beforeLayout() {

    }

    private void getHttpMsg() {
        String inputName = editText1.getText().toString();
        String inputPassword = editText2.getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("name",inputName);
        map.put("password",inputPassword);
        String urlTag = "user/loginAccount";
        OkHttpMgr.getInstance().postJson(urlTag, map, new OkMsgCallback() {
            @Override
            public void fail(String error) {
                Log.e(TAG, "error:" + error);
            }
            @Override
            public void success(int code, String body) {
                //Log.e(TAG, "code:" + code);
                //Log.e(TAG, "body:" + body);
                if(code == 200) {
                    LoginBean bean = GsonUtil.inst().getTypeJson(body, LoginBean.class);
                    startActivity(new Intent(LoginActivity.this.getBaseContext(), MainActivity.class));
                    SpUtil.getInstance().save("isLogin", true);
                    //持久化保存登录数据
                    if(bean.getData() != null) {
                        LoginBean.UserBean data = bean.getData();
                        SpUtil.getInstance().save("userId", data.getUserId());
                        SpUtil.getInstance().save("name", data.getName());
                        SpUtil.getInstance().save("headImage", data.getHeadImage());
                        SpUtil.getInstance().save("phoneNum", data.getPhoneNum());
                        SpUtil.getInstance().save("balance", data.getBalance().floatValue());
                    }
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.login) {
            getHttpMsg();
        }
    }
}
