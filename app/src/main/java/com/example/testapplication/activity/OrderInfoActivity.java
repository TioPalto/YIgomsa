package com.example.testapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapplication.R;
import com.example.testapplication.adapter.OrderGoodAdapter;
import com.example.testapplication.bena.OrderInfoBean;
import com.example.testapplication.bena.OrderInfoBean.Address;
import com.example.testapplication.bena.OrderInfoBean.Order;
import com.example.testapplication.http.GsonUtil;
import com.example.testapplication.http.OkHttpMgr;
import com.example.testapplication.http.OkMsgCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("SetTextI18n")
public class OrderInfoActivity extends BaseActivity {
    private static final String TAG = "OrderInfoActivity";

    private TextView txtOrderNum, txtCreateTime, txtState, txtTotalMoney;
    private TextView goodReceiver, goodPhone, goodAddress, txtMoreMsg;
    private RecyclerView goodList;
    private Button btnCheckGood;
    private View layoutMoreMsg;
    private OrderGoodAdapter mAdapter;

    private String orderNum = "";//订单编号

    private static final int UPDATE_VIEW = 0x121;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == UPDATE_VIEW) {
                OrderInfoBean bean = (OrderInfoBean) msg.obj;
                if (bean.getData() == null){
                    Toast.makeText(OrderInfoActivity.this,"商品数据错误",Toast.LENGTH_SHORT).show();
                    finish();
                }else {updateViews(bean);}
            }
            return false;
        }
    });

    private void updateViews(OrderInfoBean bean) {
        if (bean.getData().getOrder() != null) {
            Order order = bean.getData().getOrder();
            txtOrderNum.setText("订单编号:" + order.getOrderNum());
            txtCreateTime.setText("创建时间:" + order.getCreateTime());
            String status = order.getStatus();
            if (status.equals("1")) {
                txtState.setText("待发货");
                txtState.setTextColor(ContextCompat.getColor(this.getApplication(), R.color.light_pure_green));
                btnCheckGood.setVisibility(View.GONE);
            }
            if (status.equals("2")) {
                txtState.setText("待收货");
                txtState.setTextColor(ContextCompat.getColor(this.getApplication(), R.color.btn_blue));
                btnCheckGood.setVisibility(View.VISIBLE);
            }
            if (status.equals("3")) {
                txtState.setText("已完成");
                txtState.setTextColor(ContextCompat.getColor(this.getApplication(), R.color.big_red));
                btnCheckGood.setVisibility(View.GONE);
            }
            String msg = order.getMessage();
            if (!TextUtils.isEmpty(msg)) {
                layoutMoreMsg.setVisibility(View.VISIBLE);
                txtMoreMsg.setText(msg);
            } else {
                layoutMoreMsg.setVisibility(View.GONE);
            }
        }
        if (bean.getData().getAddress() != null) {
            Address data = bean.getData().getAddress();
            goodReceiver.setText("收货人:" + data.getName());
            goodPhone.setText("收货人电话:" + data.getPhone());
            goodAddress.setText("收货地址:" + data.getCity() + data.getDistrict() + data.getDetail());
        }
        if (bean.getData().getItem_list() != null && !bean.getData().getItem_list().isEmpty()) {
            List<OrderInfoBean.Good> list = bean.getData().getItem_list();
            goodList.setVisibility(View.VISIBLE);
            mAdapter.setGoodList(list);
            float total = 0f;
            for (OrderInfoBean.Good data : list) {
                float f = Float.parseFloat(data.getPrice());
                total += f;
            }
            txtTotalMoney.setText("商品总价:￥" + total);
        } else {
            goodList.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


    @Override
    protected int contentLayout() {
        return R.layout.activity_order_info;
    }

    @Override
    protected void afterInitView() {
        setLayoutPaddingTop();
        orderNum = getIntent().getStringExtra("orderNum");
        getOrderInfo();
    }

    private void getOrderInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("orderNum", orderNum);
        String urlTag = "order/details";
        OkHttpMgr.getInstance().postJson(urlTag, map, new OkMsgCallback() {
            @Override
            public void fail(String error) {
                Log.e(TAG, "error:" + error);
            }

            @Override
            public void success(int code, String body) {
                if (code == 200) {
                    OrderInfoBean bean = GsonUtil.inst().getTypeJson(body, OrderInfoBean.class);
                    Message msg = mHandler.obtainMessage();
                    msg.what = UPDATE_VIEW;
                    msg.obj = bean;
                    mHandler.sendMessage(msg);
                }
            }
        });
    }

    @Override
    protected void initView() {
        txtOrderNum = findViewById(R.id.txt_order_num);
        txtCreateTime = findViewById(R.id.txt_create_time);
        txtState = findViewById(R.id.item_txt_state);
        goodReceiver = findViewById(R.id.txt_good_man);
        goodPhone = findViewById(R.id.txt_good_phone);
        goodAddress = findViewById(R.id.txt_good_address);
        txtMoreMsg = findViewById(R.id.txt_more_msg);
        goodList = findViewById(R.id.good_list);
        LinearLayoutManager layoutMgr = new LinearLayoutManager(this.getBaseContext(),
                LinearLayoutManager.VERTICAL, false);
        goodList.setLayoutManager(layoutMgr);
        mAdapter = new OrderGoodAdapter(this.getApplication());
        goodList.setAdapter(mAdapter);
        txtTotalMoney = findViewById(R.id.txt_total_price);
        btnCheckGood = findViewById(R.id.btn_check_good);
        btnCheckGood.setOnClickListener(this);
        btnCheckGood.setVisibility(View.GONE);
        layoutMoreMsg = findViewById(R.id.layout_more_msg);
        layoutMoreMsg.setVisibility(View.GONE);

    }

    @Override
    protected void beforeLayout() {

    }

    @Override
    public void onClick(View view) {
    }

}