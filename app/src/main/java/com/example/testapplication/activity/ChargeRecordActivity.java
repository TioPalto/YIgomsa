package com.example.testapplication.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.testapplication.R;
import com.example.testapplication.adapter.RecordListAdapter;
import com.example.testapplication.bena.RechargeListBean;
import com.example.testapplication.http.GsonUtil;
import com.example.testapplication.http.OkHttpMgr;
import com.example.testapplication.http.OkMsgCallback;
import com.example.testapplication.util.SpUtil;

import java.util.HashMap;
import java.util.Map;

public class ChargeRecordActivity extends BaseActivity{

    private static final String TAG = "RecordActivity";

    private RecordListAdapter mAdapter;
    private static final int UPDATE_LIST = 0x121;

    private final Handler mHandler = new Handler(msg -> {
        if(msg.what == UPDATE_LIST) {
            if(msg.obj == null) return false;
            RechargeListBean bean = (RechargeListBean) msg.obj;
            if(bean.getData() != null && !bean.getData().isEmpty()) {
                mAdapter.setGoodList(bean.getData());
            }
        }
        return false;
    });

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_charge_record);
//        ActionBar actionbar = getSupportActionBar();
//        if (actionbar != null) {
//            actionbar.hide();
//        }
//    }

    @Override
    protected int contentLayout() {
        return R.layout.activity_charge_record;
    }

    @Override
    protected void afterInitView() {
        setLayoutPaddingTop();
        getRecordList();
    }

    @Override
    protected void initView() {
        RecyclerView recordList = findViewById(R.id.record_list);
        LinearLayoutManager layoutMgr = new LinearLayoutManager(this.getBaseContext(),
                LinearLayoutManager.VERTICAL, false);
        recordList.setLayoutManager(layoutMgr);
        mAdapter = new RecordListAdapter(this.getBaseContext());
        recordList.setAdapter(mAdapter);
        findViewById(R.id.click_back).setOnClickListener(view -> {
            ChargeRecordActivity.this.finish();
        });
    }

    @Override
    protected void beforeLayout() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    private void getRecordList() {
        if(SpUtil.getInstance().getString("userId") == null) {
            return;
        }
        String userId = SpUtil.getInstance().getString("userId");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        String urlTag = "recharge/getRecord";
        OkHttpMgr.getInstance().postJson(urlTag, map, new OkMsgCallback() {
            @Override
            public void fail(String error) {
                Log.e(TAG, "error:" + error);
            }
            @Override
            public void success(int code, String body) {
                if(code == 200) {
                    RechargeListBean bean = GsonUtil.inst().getTypeJson(body, RechargeListBean.class);
                    Message msg = mHandler.obtainMessage();
                    msg.what = UPDATE_LIST;
                    msg.obj = bean;
                    mHandler.sendMessage(msg);
                }
            }
        });
    }
}