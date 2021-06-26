package com.example.testapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.BaseFragment;
import com.example.testapplication.R;
import com.example.testapplication.activity.SubsInfoActivity;
import com.example.testapplication.adapter.SubsItemAdapter;
import com.example.testapplication.adapter.SubsItemAdapter.TypeItemClickListener;
import com.example.testapplication.bena.SubListBean;
import com.example.testapplication.http.GsonUtil;
import com.example.testapplication.http.OkHttpMgr;
import com.example.testapplication.http.OkMsgCallback;
import com.example.testapplication.util.SpUtil;

import java.util.HashMap;
import java.util.Map;

public class pageFragment2 extends BaseFragment {
    private static final String TAG = "TAB2";

    private SubsItemAdapter mAdapter;

    private static final int UPDATE_LIST = 0x121;

    private Handler mHandler = new Handler(msg ->{
        if (msg.what ==UPDATE_LIST){
            if (msg.obj ==null) return false;
            SubListBean bean = (SubListBean) msg.obj;
            if (bean.getData()!=null && !bean.getData().isEmpty()){
                mAdapter.setGoodList(bean.getData());
            }
        }
        return false;
    });


    @Override
    public void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    protected void afterLayout() {
        getSubsList();
    }

    private final TypeItemClickListener itemClickListener = (position, bean) -> {
        String bookId = bean.getBookId();
        Intent itn = new Intent(getActivity(), SubsInfoActivity.class);
        itn.putExtra("bookId", bookId);
        startActivity(itn);
    };

    private void getSubsList(){
        if (SpUtil.getInstance().getString("userId") == null){
            return;
        }
        String userId = SpUtil.getInstance().getString("userId");
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        String urlTag = "book/getAllById";
        OkHttpMgr.getInstance().postJson(urlTag, map, new OkMsgCallback() {
            @Override
            public void fail(String error) {
                Log.e(TAG, "error:" + error);
            }

            @Override
            public void success(int code, String body) {
                Log.e(TAG,body);
                if(code == 200) {
                    SubListBean bean = GsonUtil.inst().getTypeJson(body, SubListBean.class);

                    Message msg = mHandler.obtainMessage();
                    msg.what = UPDATE_LIST;
                    msg.obj = bean;
                    mHandler.sendMessage(msg);
                }
            }
        });
    }

    @Override
    protected void initView(View view) {
        RecyclerView subsList = view.findViewById(R.id.subs_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        subsList.setLayoutManager(layoutManager);
        mAdapter = new SubsItemAdapter(getContext());
        subsList.setAdapter(mAdapter);
        mAdapter.setTypeItemClickListener(itemClickListener);
    }

    @Override
    protected int contentLayout() {
        return R.layout.tab2;
    }

    @Override
    public void onClick(View view) {

    }
}
