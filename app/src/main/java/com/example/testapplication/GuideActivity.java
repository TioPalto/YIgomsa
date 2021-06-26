package com.example.testapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testapplication.activity.BaseActivity;
import com.example.testapplication.activity.LoginActivity;
import com.example.testapplication.activity.SplashActivity;
import com.example.testapplication.adapter.ViewPagerAdapter;
import com.example.testapplication.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity{

    private ViewPager viewPager;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private Button button;
    private List<View> viewList;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //保存引导状态
        SpUtil.getInstance().save("isGuider",true);
        setContentView(R.layout.activity_guide);
        initView();
        viewPager();
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }
    }

    @Override
    protected int contentLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void afterInitView() {

    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.view_pager);
        txt1 = findViewById(R.id.text1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.text3);
        button = findViewById(R.id.buttom);
        button.setOnClickListener(this);
        findViewById(R.id.text_skip).setOnClickListener(this);
    }

    @Override
    protected void beforeLayout() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_skip:
                Intent main1 = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(main1);
                finish();
                break;
            case R.id.buttom:
                Intent main =new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(main);
                break;
        }
    }


    private void viewPager() {
        viewList = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        viewList.add(inflater.inflate(R.layout.guide_1, null));
        viewList.add(inflater.inflate(R.layout.guide_2, null));
        viewList.add(inflater.inflate(R.layout.guide_3, null));
        mAdapter = new ViewPagerAdapter(this, viewList);
        viewPager.setAdapter(mAdapter);
        viewPager.setOnPageChangeListener(pageListener);
        txt1.setText("O");
        txt2.setText("X");
        txt3.setText("X");
        button.setVisibility(View.GONE);
    }

    private ViewPager.OnPageChangeListener pageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position>=0 && position<3){
                switch (position){
                    case 0:
                        txt1.setText("O");
                        txt2.setText("X");
                        txt3.setText("X");
                        button.setVisibility(View.GONE);
                        break;
                    case 1:
                        txt1.setText("X");
                        txt2.setText("O");
                        txt3.setText("X");
                        button.setVisibility(View.GONE);
                        break;
                    case 2:
                        txt1.setText("X");
                        txt2.setText("X");
                        txt3.setText("O");
                        button.setVisibility(View.VISIBLE);
                        break;

                }
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {
//            if (state>=0 && state<3){
//                switch (state){
//                    case 0:
//                        txt1.setText("O");
//                        txt2.setText("X");
//                        txt3.setText("X");
//                        break;
//                    case 1:
//                        txt1.setText("X");
//                        txt2.setText("O");
//                        txt3.setText("X");
//                        break;
//                    case 2:
//                        txt1.setText("X");
//                        txt2.setText("X");
//                        txt3.setText("O");
//                        break;
//
//                }
//            }
        }

    };
}
