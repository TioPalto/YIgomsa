package com.example.testapplication.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testapplication.BaseFragment;
import com.example.testapplication.R;
import com.example.testapplication.activity.BuyRecordActivity;
import com.example.testapplication.activity.ChargeRecordActivity;
import com.example.testapplication.util.SpUtil;
import com.google.android.material.imageview.ShapeableImageView;

public class pageFragment3 extends BaseFragment {
    private TextView txtBalance, txtName, txtLevel;
    private ShapeableImageView imgHead;
    Button button;

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
        txtBalance.setText("余额:￥" + balance);
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
        button = view.findViewById(R.id.btn_exit_login);
        view.findViewById(R.id.btn_exit_login).setOnClickListener(this);
        view.findViewById(R.id.click_buy_record).setOnClickListener(this);
        view.findViewById(R.id.click_recharge_record).setOnClickListener(this);
    }

    @Override
    protected int contentLayout() {
        return R.layout.tab3;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_exit_login){
            SpUtil.getInstance().save("isLogin", false);
            SpUtil.getInstance().save("userId", null);
            SpUtil.getInstance().save("name", null);
            SpUtil.getInstance().save("headImage", null);
            SpUtil.getInstance().save("phoneNum", null);
            SpUtil.getInstance().save("balance", null);
            getActivity().finish();
        }
        if(v.getId() == R.id.click_buy_record) {
            startActivity(new Intent(getActivity(), BuyRecordActivity.class));
        }
        if(v.getId() == R.id.click_recharge_record) {
            startActivity(new Intent(getActivity(), ChargeRecordActivity.class));
        }

    }
}
