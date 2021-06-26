package com.example.testapplication.http;

import android.text.TextUtils;

import com.example.testapplication.util.SpUtil;

import java.util.ArrayList;
import java.util.List;

public class GoodShopMgr {
    public GoodShopMgr() {}
    private static class Holder{
        private static final GoodShopMgr instance = new GoodShopMgr();
    }
    public static GoodShopMgr inst(){
        return Holder.instance;
    }
    public void saveGood(String name,float price){
        String goodList = SpUtil.getInstance().getString("goodList");
        float total = SpUtil.getInstance().getFloat("totalPrice");
        total += price;
        SpUtil.getInstance().save("totalPrice", total);
        if(TextUtils.isEmpty(goodList)) {
            goodList = name;
        } else {
            goodList += ";" + name + ";";

        }
        if(goodList.endsWith(";")) {
            goodList = goodList.substring(0, goodList.length()-1);
        }
        SpUtil.getInstance().save("goodList", goodList);
    }
    public String getGood(){
        return SpUtil.getInstance().getString("goodList");
    }
    public List<String> getGoodNames(){
        String goodList = SpUtil.getInstance().getString("goodList");
        if (!TextUtils.isEmpty(goodList)){
            List<String> nameList = new ArrayList<>();
            String[] sp = goodList.split(";");
            for(String name : sp) {
                nameList.add(name);
            }
            return nameList;
        }
        return null;
    }
    public float getGoodPrice(){
        return SpUtil.getInstance().getFloat("totalPrice");
    }
    public void cleanGoods(){
        SpUtil.getInstance().save("goodList", "");
        SpUtil.getInstance().save("totalPrice", 0f);
    }
}
