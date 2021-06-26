package com.example.testapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testapplication.activity.BaseActivity;
import com.example.testapplication.activity.LoginActivity;
import com.example.testapplication.activity.SplashActivity;
import com.example.testapplication.adapter.ViewPagerAdapter;
import com.example.testapplication.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity{
    private ImageView imgPoint1, imgPoint2, imgPoint3, imgPoint4;
    private Button btnLogin;
    private ViewPager viewPager;

    @Override
    protected int contentLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void onStop() {
        super.onStop();
        GuideActivity.this.finish();
    }

    @Override
    protected void afterInitView() {
        initGuide();
    }
    private void goLogin() {
        SpUtil.getInstance().save("isGuider", true);
        startActivity(new Intent(this.getBaseContext(), LoginActivity.class));
    }

    private void initGuide() {
        List<View> viewList = new ArrayList<>();
        View view1 = LayoutInflater.from(this.getBaseContext()).
                inflate(R.layout.layout_guide, null);
        View view2 = LayoutInflater.from(this.getBaseContext()).
                inflate(R.layout.layout_guide, null);
        View view3 = LayoutInflater.from(this.getBaseContext()).
                inflate(R.layout.layout_guide, null);
        View view4 = LayoutInflater.from(this.getBaseContext()).
                inflate(R.layout.layout_guide, null);
        ImageView imgGuide1 = view1.findViewById(R.id.img_guide);
        ImageView imgGuide2 = view2.findViewById(R.id.img_guide);
        ImageView imgGuide3 = view3.findViewById(R.id.img_guide);
        ImageView imgGuide4 = view4.findViewById(R.id.img_guide);
        imgGuide1.setImageResource(R.mipmap.guide1);
        viewList.add(view1);
        imgGuide2.setImageResource(R.mipmap.guide2);
        viewList.add(view2);
        imgGuide3.setImageResource(R.mipmap.guide3);
        viewList.add(view3);
        imgGuide4.setImageResource(R.mipmap.guide4);
        viewList.add(view4);
        ViewPagerAdapter vAdapter = new ViewPagerAdapter(this.getBaseContext(), viewList);
        viewPager.setAdapter(vAdapter);
        viewPager.setOnPageChangeListener(pageListener);
    }

    private final ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }
        @Override
        public void onPageSelected(int position) {
            if(position >= 0 && position < 4) {
                if(position == 0) {
                    imgPoint1.setImageResource(R.mipmap.point_selected);
                } else {
                    imgPoint1.setImageResource(R.mipmap.point_unselected);
                }
                if(position == 1) {
                    imgPoint2.setImageResource(R.mipmap.point_selected);
                } else {
                    imgPoint2.setImageResource(R.mipmap.point_unselected);
                }
                if(position == 2) {
                    imgPoint3.setImageResource(R.mipmap.point_selected);
                } else {
                    imgPoint3.setImageResource(R.mipmap.point_unselected);
                }
                if(position == 3) {
                    imgPoint4.setImageResource(R.mipmap.point_selected);
                } else {
                    imgPoint4.setImageResource(R.mipmap.point_unselected);
                }
                if(position == 3) {
                    btnLogin.setVisibility(View.VISIBLE);
                } else {
                    btnLogin.setVisibility(View.GONE);
                }
            }
        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.view_pager);
        imgPoint1 = findViewById(R.id.img_point1);
        imgPoint2 = findViewById(R.id.img_point2);
        imgPoint3 = findViewById(R.id.img_point3);
        imgPoint4 = findViewById(R.id.img_point4);
        TextView txtSkip = findViewById(R.id.text_skip);
        btnLogin = findViewById(R.id.btn_main);
        btnLogin.setVisibility(View.GONE);
        txtSkip.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }

    }

    @Override
    protected void beforeLayout() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.text_skip:
            case R.id.btn_main:
                goLogin();
                break;
        }
    }
}
