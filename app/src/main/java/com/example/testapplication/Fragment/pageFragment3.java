package com.example.testapplication.Fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.BaseFragment;
import com.example.testapplication.R;
import com.example.testapplication.activity.OrderInfoActivity;
import com.example.testapplication.adapter.BuyRecordListAdapter;
import com.example.testapplication.bena.BuyListBean;
import com.example.testapplication.http.GsonUtil;
import com.example.testapplication.http.OkHttpMgr;
import com.example.testapplication.http.OkMsgCallback;
import com.example.testapplication.util.SpUtil;

import java.util.HashMap;
import java.util.Map;

public class pageFragment3 extends BaseFragment {
    private static final String TAG = "MainOrderFragment";

    private RadioButton radioSend, radioGet, radioFinish;
    private RecyclerView recordList;
    private TextView txtNull;
    private BuyRecordListAdapter mAdapter;

    private static final int UPDATE_LIST = 0x121;
    private String status = "1";//1:代发货 2：待收获 3已完成

    private final Handler mHandler = new Handler(msg -> {
        if(msg.what == UPDATE_LIST) {
            if(msg.obj == null) return false;
            BuyListBean bean = (BuyListBean) msg.obj;
            if(bean.getData() != null && !bean.getData().isEmpty()) {
                mAdapter.setGoodList(bean.getData());
                recordList.setVisibility(View.VISIBLE);
                txtNull.setVisibility(View.GONE);
            } else {
                recordList.setVisibility(View.GONE);
                txtNull.setVisibility(View.VISIBLE);
            }
        }
        return false;
    });

    @Override
    protected void afterLayout() {
        getOrderList();
    }

    private final BuyRecordListAdapter.TypeItemClickListener itemClickListener = (position, bean) -> {
        if(bean != null) {
            String order = bean.getOrderNum();
            Intent itn = new Intent(getActivity(), OrderInfoActivity.class);
            itn.putExtra("orderNum", order);
            startActivity(itn);
        }
    };

    @Override
    protected void initView(View view) {
        txtNull = view.findViewById(R.id.txt_data_null);
        recordList = view.findViewById(R.id.record_list);
        LinearLayoutManager layoutMgr = new LinearLayoutManager(recordList.getContext(),
                LinearLayoutManager.VERTICAL, false);
        recordList.setLayoutManager(layoutMgr);
        mAdapter = new BuyRecordListAdapter(getContext());
        recordList.setAdapter(mAdapter);
        mAdapter.setTypeItemClickListener(itemClickListener);
        radioSend = view.findViewById(R.id.radio_send);
        radioGet = view.findViewById(R.id.radio_get);
        radioFinish = view.findViewById(R.id.radio_finish);
        radioSend.setOnClickListener(this);
        radioGet.setOnClickListener(this);
        radioFinish.setOnClickListener(this);
        txtNull.setVisibility(View.GONE);
    }


    private void getOrderList() {
        if(SpUtil.getInstance().getString("userId") == null) {
            return;
        }
        String userId = SpUtil.getInstance().getString("userId");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("status", status);
        String urlTag = "order/showByStatus";
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
        return R.layout.tab3;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.radio_send) {
            status = "1";
            getOrderList();
        }
        if(v.getId() == R.id.radio_get) {
            status = "2";
            getOrderList();
        }
        if(v.getId() == R.id.radio_finish) {
            status = "3";
            getOrderList();
        }
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
