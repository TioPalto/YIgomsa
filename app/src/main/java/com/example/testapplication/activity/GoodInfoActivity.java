package com.example.testapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testapplication.R;
import com.example.testapplication.bena.GoodInfoBean;
import com.example.testapplication.http.GoodShopMgr;
import com.example.testapplication.http.GsonUtil;
import com.example.testapplication.http.OkHttpMgr;
import com.example.testapplication.http.OkMsgCallback;

import java.util.HashMap;
import java.util.Map;

public class GoodInfoActivity extends BaseActivity {
    private static final String TAG = "GoodInfoActivity";

    private TextView goodTitle;
    private ImageView goodImg;
    private TextView goodIntro;

    private String goodsId;
    private GoodInfoBean.InfoBean goodData;

    private static final int UPDATE_VIEW = 0x133;

    private Handler mHandler = new Handler(msg -> {
        if(msg.what == UPDATE_VIEW) {
            GoodInfoBean bean = (GoodInfoBean) msg.obj;
            if(bean.getData() != null) {
                goodData = bean.getData();
                updateViews();
            }
        }
        return false;
    });

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    private void updateViews() {
        goodTitle.setText(goodData.getName());
        goodIntro.setText(goodData.getDetail());
        Glide.with(goodImg).load(goodData.getImage()).into(goodImg);
    }

    private void getGoodInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("goodsId", goodsId);
        String urlTag = "goods/goodsDetails";
        OkHttpMgr.getInstance().postJson(urlTag, map, new OkMsgCallback() {
            @Override
            public void fail(String error) {
                Log.e(TAG, "error:" + error);
            }
            @Override
            public void success(int code, String body) {
                //Log.e(TAG, "body:" + body);
                if(code == 200) {
                    GoodInfoBean bean = GsonUtil.inst().getTypeJson(body, GoodInfoBean.class);
                    Message msg = mHandler.obtainMessage();
                    Log.d(TAG, body);
                    msg.what = UPDATE_VIEW;
                    msg.obj = bean;
                    mHandler.sendMessage(msg);
                }
            }
        });
    }

    @Override
    protected int contentLayout() {
        return R.layout.activity_good_info;
    }

    @Override
    protected void afterInitView() {
        setLayoutPaddingTop();
        goodsId = getIntent().getStringExtra("goodId");
        getGoodInfo();
    }

    @Override
    protected void initView() {
        goodTitle = findViewById(R.id.txt_good_title);
        goodImg = findViewById(R.id.img_good);
        goodIntro = findViewById(R.id.txt_intro);
        findViewById(R.id.btn_add_shop).setOnClickListener(this);
        findViewById(R.id.click_back).setOnClickListener(this);

    }

    @Override
    protected void beforeLayout() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.click_back) {
            GoodInfoActivity.this.finish();
        }
        if(v.getId() == R.id.btn_add_shop) {
            if(goodData != null) {
                String name = goodData.getName();
                GoodShopMgr.inst().saveGood(goodData.getName(), goodData.getPrice().floatValue());
                GoodInfoActivity.this.finish();
            }
        }
    }
}