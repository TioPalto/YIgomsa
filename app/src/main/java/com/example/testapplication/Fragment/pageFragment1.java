package com.example.testapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.BaseFragment;
import com.example.testapplication.R;
import com.example.testapplication.adapter.BannerImgAdapter;
import com.example.testapplication.adapter.GoodListAdapter;
import com.example.testapplication.adapter.GoodListAdapter.TypeItemClickListener;
import com.example.testapplication.bena.BannerBean;
import com.example.testapplication.bena.GoodListBean;
import com.example.testapplication.http.GoodShopMgr;
import com.example.testapplication.http.GsonUtil;
import com.example.testapplication.http.OkHttpMgr;
import com.example.testapplication.http.OkMsgCallback;
import com.example.testapplication.view.MsgCenterDialog;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("SetTextI18n")
public class pageFragment1 extends BaseFragment {
    private static final String TAG = "TAB1";

    private TextView txtGoodNum, txtGoodPrice;
    private Banner imgBanner;
    private GoodListAdapter goodListAdapter;

    private static final int UPDATE_LIST = 0x131;
    private Handler mHandler = new Handler(msg ->{
        if(msg.what == UPDATE_LIST) {
            GoodListBean bean = (GoodListBean) msg.obj;
            if(bean.getData() != null) {
                goodListAdapter.setGoodList(bean.getData());
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
        setBanner();
        getGoodList();
    }
    private final TypeItemClickListener itemClickListener = new TypeItemClickListener() {
        @Override
        public void onBuyClick(int position, GoodListBean.GoodBean bean) {
            if (bean!=null){
                GoodShopMgr.inst().saveGood(bean.getName(), bean.getPrice().floatValue());
                setGoodInfo();
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        setGoodInfo();
    }
    private void setGoodInfo(){
        if(!TextUtils.isEmpty(GoodShopMgr.inst().getGood())) {
            float price = GoodShopMgr.inst().getGoodPrice();
            txtGoodPrice.setText("￥" + price);
            if(GoodShopMgr.inst().getGoodNames() != null) {
                int size = GoodShopMgr.inst().getGoodNames().size();
                txtGoodNum.setText(size + "件商品");
            } else {
                txtGoodNum.setText("0件商品");
            }
        } else {
            txtGoodPrice.setText("￥0");
            txtGoodNum.setText("0件商品");
        }
    }


    @Override
    protected void initView(View view) {
        imgBanner = view.findViewById(R.id.img_banner);
        RecyclerView goodList = view.findViewById(R.id.good_list);
        LinearLayoutManager layoutMgr = new LinearLayoutManager(goodList.getContext(),
                LinearLayoutManager.VERTICAL, false);
        goodList.setLayoutManager(layoutMgr);
        goodListAdapter = new GoodListAdapter(getContext());
        goodList.setAdapter(goodListAdapter);
        goodListAdapter.setTypeItemClickListener(itemClickListener);
        txtGoodNum = view.findViewById(R.id.txt_good_num);
        txtGoodPrice = view.findViewById(R.id.txt_good_price);
        view.findViewById(R.id.btn_clear_check).setOnClickListener(this);
        view.findViewById(R.id.btn_check_money).setOnClickListener(this);

    }

    @Override
    protected int contentLayout() {
        return R.layout.tab1;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_clear_check){
            GoodShopMgr.inst().cleanGoods();
            setGoodInfo();
        }
        if (v.getId() == R.id.btn_check_money){
            if (!TextUtils.isEmpty(GoodShopMgr.inst().getGood())){
                float price = GoodShopMgr.inst().getGoodPrice();
                int size = GoodShopMgr.inst().getGoodNames().size();
                String title = "结算商品";
                String content = size + "件商品，一共￥" + price + "元，确认结算吗?";
                MsgCenterDialog dialog = new MsgCenterDialog(getContext(), title, content,
                        "取消", "确认");
                dialog.show();
                dialog.setDialogClickListener(new MsgCenterDialog.onDialogClickListener() {
                    @Override
                    public void onCancelClick(View v) {
                        dialog.dismiss();
                    }
                    @Override
                    public void onDefineClick(View v) {
                        Snackbar.make(imgBanner, "商品结算成功!",
                                Snackbar.LENGTH_SHORT).show();
                        GoodShopMgr.inst().cleanGoods();
                        setGoodInfo();
                        dialog.dismiss();
                    }
                });
            }
        }
    }

    private void getGoodList() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("pageSize", "100");
        String urlTag = "goods/getGoodsByPage";
        OkHttpMgr.getInstance().postJson(urlTag, map, new OkMsgCallback() {
            @Override
            public void fail(String error) {
                Log.e(TAG, "error:" + error);
            }
            @Override
            public void success(int code, String body) {
                //Log.e(TAG, "body:" + body);
                if(code == 200) {
                    GoodListBean bean = GsonUtil.inst().getTypeJson(body, GoodListBean.class);
                    Message msg = mHandler.obtainMessage();
                    Log.d(TAG,body);
                    msg.what = UPDATE_LIST;
                    msg.obj = bean;
                    mHandler.sendMessage(msg);
                }
            }
        });
    }

    private void setBanner(){
        List<BannerBean> beanList = new ArrayList<>();
        beanList.add(new BannerBean("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1330784235,4146572500&fm=26&gp=0.jpg","地震云"));
        beanList.add(new BannerBean("http://n1-q.mafengwo.net/s9/M00/0A/9C/wKgBs1hjZFWAWX-NAA0HzXk6ND498.jpeg","威斯特彻斯特县旅游"));
        beanList.add(new BannerBean("https://img-arch.pconline.com.cn/images/upload/upc/tx/photoblog/1107/19/c2/8373140_8373140_1311046320283_mthumb.jpg","双飞燕"));
        BannerImgAdapter imgAdapter = new BannerImgAdapter(beanList);
        imgBanner.setAdapter(imgAdapter);
        imgBanner.setOnBannerListener((data, pos) ->{
            Snackbar.make(imgBanner, ((BannerBean)data).getTitle(),
                    Snackbar.LENGTH_SHORT)
                    .setAction("撤销", v -> {
                        Toast.makeText(this.getContext(),
                                "点击了撤销按钮", Toast.LENGTH_SHORT).show();
                    })
                    .show();
        });
    }
}
