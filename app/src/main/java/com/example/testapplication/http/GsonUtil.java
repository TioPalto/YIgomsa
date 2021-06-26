package com.example.testapplication.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class GsonUtil {

    private static class Holder {
        private static GsonUtil instance = new GsonUtil();
    }

    public static GsonUtil inst() {
        return Holder.instance;
    }

    private Gson gson;

    private GsonUtil() {
        //initEasy();//简单初始化
        initBuilder();//带参数初始化
    }

    //简单初始化
    private void initEasy() {
        if(gson != null) {
            gson = new Gson();
        }
    }

    //带参数初始化
    public void initBuilder() {
        if(gson == null) {
            gson = new GsonBuilder()
                    .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                    .serializeNulls()//当字段值为空或null时，依然对该字段进行转换
                    .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS") //时间转化为特定格式
                    .disableHtmlEscaping() //防止特殊字符出现乱码
                    .setPrettyPrinting() //对结果进行格式化，增加换行
                    .create();
        }
    }

    public String toJson(Object src) {
        if(gson != null) {
            return gson.toJson(src);
        }
        return null;
    }

    public String toTypeJson(Object src, Type type) {
        if(gson != null) {
            return gson.toJson(src, type);
        }
        return null;
    }

    public <Z> Z getTypeJson(String json, Class<Z> zClass) {
        if(!TextUtils.isEmpty(json) && gson != null) {
            return gson.fromJson(json, zClass);
        }
        return null;
    }

}
