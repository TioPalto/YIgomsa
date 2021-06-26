package com.example.testapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.testapplication.R;
import com.example.testapplication.bena.SubsInfoBean;
import com.example.testapplication.http.GsonUtil;
import com.example.testapplication.http.OkHttpMgr;
import com.example.testapplication.http.OkMsgCallback;

import java.util.HashMap;
import java.util.Map;

public class SubsInfoActivity extends BaseActivity {

    private static final String TAG = "SubsInfoActivity";

    private TextView txtSubState, txtSubsName, txtSubsTime, txtComeTime, txtMarkMsg;

    private static final int UPDATE_DATA = 0x123;
    private String bookId;

    private Handler mHandler = new Handler(msg -> {
        if(msg.what == UPDATE_DATA) {
            SubsInfoBean bean = (SubsInfoBean) msg.obj;
            if(bean.getData() != null) {
                updateViews(bean.getData());
            }
        }
        return false;
    });


    @SuppressLint("SetTextI18n")
    private void updateViews(SubsInfoBean.SubsData data) {
        String status = data.getStatus();
        if(status.equals("0")) {
            txtSubState.setText("过期");
            txtSubState.setTextColor(
                    ContextCompat.getColor(txtSubState.getContext(), R.color.txt_gray_deep));
        }
        if(status.equals("1")) {
            txtSubState.setText("接受");
            txtSubState.setTextColor(
                    ContextCompat.getColor(txtSubState.getContext(), R.color.btn_green));
        }
        if(status.equals("2")) {
            txtSubState.setText("拒绝");
            txtSubState.setTextColor(
                    ContextCompat.getColor(txtSubState.getContext(), R.color.dark_red));
        }
        if(status.equals("3")) {
            txtSubState.setText("未处理");
            txtSubState.setTextColor(
                    ContextCompat.getColor(txtSubState.getContext(), R.color.btn_blue));
        }
        txtSubsName.setText(data.getBookItem());
        txtSubsTime.setText("预约时间:" + data.getBookTime());
        txtComeTime.setText("到店时间:" + data.getComeTime());;
        txtMarkMsg.setText(data.getMessage());
    }


    private void getSubsInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("bookId", bookId);
        String urlTag = "book/details";
        OkHttpMgr.getInstance().postJson(urlTag, map, new OkMsgCallback() {
            @Override
            public void fail(String error) {
                Log.e(TAG, "error:" + error);
            }
            @Override
            public void success(int code, String body) {
                //Log.e(TAG,"body:" + body);
                if(code == 200) {
                    SubsInfoBean bean = GsonUtil.inst().getTypeJson(body, SubsInfoBean.class);
                    Message msg = mHandler.obtainMessage();
                    msg.what = UPDATE_DATA;
                    msg.obj = bean;
                    mHandler.sendMessage(msg);
                }
            }
        });
    }
    @Override
    protected int contentLayout() {
        return R.layout.activity_subs_info;
    }

    @Override
    protected void afterInitView() {
        setLayoutPaddingTop();
        bookId = getIntent().getStringExtra("bookId");
        getSubsInfo();
    }

    @Override
    protected void initView() {
        txtSubState = findViewById(R.id.txt_subs_state);
        txtSubsName = findViewById(R.id.txt_subs_name);
        txtSubsTime = findViewById(R.id.txt_subs_time);
        txtComeTime = findViewById(R.id.txt_come_time);
        txtMarkMsg = findViewById(R.id.txt_mark_msg);
        findViewById(R.id.click_back).setOnClickListener(this);
    }

    @Override
    protected void beforeLayout() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.click_back) {
            SubsInfoActivity.this.finish();
        }
    }

}