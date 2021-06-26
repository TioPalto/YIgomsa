package com.example.testapplication.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.example.testapplication.R;
import com.example.testapplication.adapter.BuyRecordListAdapter;
import com.example.testapplication.bena.BuyListBean;
import com.example.testapplication.http.GsonUtil;
import com.example.testapplication.http.OkHttpMgr;
import com.example.testapplication.http.OkMsgCallback;
import com.example.testapplication.util.SpUtil;

import java.util.HashMap;
import java.util.Map;

public class BuyRecordActivity extends BaseActivity {

    private static final String TAG = "BuyRecordActivity";

    private RadioButton radioTime, radioMoney;
    private BuyRecordListAdapter mAdapter;

    private String valueBy = "createTime";//money/createTime，默认createTime
    private static final int UPDATE_LIST = 0x121;
    private final Handler mHandler = new Handler(msg -> {
        if(msg.what == UPDATE_LIST) {
            if(msg.obj == null) return false;
            BuyListBean bean = (BuyListBean) msg.obj;
            if(bean.getData() != null && !bean.getData().isEmpty()) {
                mAdapter.setGoodList(bean.getData());
            }
        }
        return false;
    });
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_buy_record);
//    }

    private void getBuyList() {
        if(SpUtil.getInstance().getString("userId") == null) {
            return;
        }
        String userId = SpUtil.getInstance().getString("userId");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("orderBy", "1");//排序方式，正序“1”，倒序“0”
        map.put("valueBy", valueBy );
        String urlTag = "order/show";
        OkHttpMgr.getInstance().postJson(urlTag, map, new OkMsgCallback() {
            @Override
            public void fail(String error) {
                Log.e(TAG, "error:" + error);
            }
            @Override
            public void success(int code, String body) {
                //Log.e(TAG, "body:" + body);
                if(code == 200) {
                    BuyListBean bean = GsonUtil.inst().getTypeJson(body, BuyListBean.class);
                    Message msg = mHandler.obtainMessage();
                    msg.what = UPDATE_LIST;
                    msg.obj = bean;
                    mHandler.sendMessage(msg);
                }
            }
        });
    }

    @Override
    protected int contentLayout() {
        return R.layout.activity_buy_record;
    }

    @Override
    protected void afterInitView() {
        setLayoutPaddingTop();
        getBuyList();

    }

    @Override
    protected void initView() {
        radioTime = findViewById(R.id.radio_time);
        radioMoney = findViewById(R.id.radio_money);
        RecyclerView recordList = findViewById(R.id.record_list);
        LinearLayoutManager layoutMgr = new LinearLayoutManager(this.getBaseContext(),
                LinearLayoutManager.VERTICAL, false);
        recordList.setLayoutManager(layoutMgr);
        mAdapter = new BuyRecordListAdapter(this.getBaseContext());
        recordList.setAdapter(mAdapter);
        radioTime.setOnClickListener(this);
        radioMoney.setOnClickListener(this);
        findViewById(R.id.click_back).setOnClickListener(this);
    }

    @Override
    protected void beforeLayout() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.click_back) {
            BuyRecordActivity.this.finish();
        }
        if(v.getId() == R.id.radio_time) {
            valueBy = "createTime";
            getBuyList();
            mAdapter.notifyDataSetChanged();
        }
        if(v.getId() == R.id.radio_money) {
            valueBy = "money";
            getBuyList();
            mAdapter.notifyDataSetChanged();
        }
    }
}