package com.example.testapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
//import com.citys.ljx.yigou.R;
//import com.citys.ljx.yigou.activity.BuyRecordActivity;
//import com.citys.ljx.yigou.activity.ChargeRecordActivity;
//import com.citys.ljx.yigou.base.BaseFragment;
//import com.citys.ljx.yigou.util.SpUtil;
import com.example.testapplication.BaseFragment;
import com.example.testapplication.R;
import com.example.testapplication.activity.BuyRecordActivity;
import com.example.testapplication.activity.ChargeRecordActivity;
import com.example.testapplication.util.SpUtil;
import com.google.android.material.imageview.ShapeableImageView;
@SuppressLint("SetTextI18n")
public class pageFragment4 extends BaseFragment {
    private TextView txtBalance, txtName, txtLevel;
    private ShapeableImageView imgHead;

    @Override
    protected void afterLayout() {
        setInfo();
    }

    private void setInfo() {
        if(SpUtil.getInstance().getString("name") != null) {
            String userName = SpUtil.getInstance().getString("name");
            txtName.setText(userName);
        }
        txtLevel.setText("等级:11");
        float balance = SpUtil.getInstance().getFloat("balance");
        txtBalance.setText("余额:" + balance);
        if(SpUtil.getInstance().getString("headImage") != null) {
            String headUrl = SpUtil.getInstance().getString("headImage");
            Glide.with(imgHead.getContext()).load(headUrl).into(imgHead);
        }
    }

    @Override
    protected void initView(View view) {
        txtBalance = view.findViewById(R.id.txt_balance);
        txtName = view.findViewById(R.id.mine_txt_name);
        txtLevel = view.findViewById(R.id.mine_txt_level);
        imgHead = view.findViewById(R.id.mine_img_head);
        view.findViewById(R.id.txt_recharge).setOnClickListener(this);
        view.findViewById(R.id.click_buy_record).setOnClickListener(this);
        view.findViewById(R.id.click_recharge_record).setOnClickListener(this);
    }

    @Override
    protected int contentLayout() {
        return R.layout.tab4;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.txt_recharge) {

        }
        if(v.getId() == R.id.click_buy_record) {
            startActivity(new Intent(getActivity(), BuyRecordActivity.class));
        }
        if(v.getId() == R.id.click_recharge_record) {
            startActivity(new Intent(getActivity(), ChargeRecordActivity.class));
        }
    }
}
