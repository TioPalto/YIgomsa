package com.example.testapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {

    private SpUtil(){}

    private static  class Holder{
        private static  final SpUtil instance = new SpUtil();
    }

    public  static  SpUtil getInstance(){
        return  Holder.instance;
    }
    private Context context;
    public  void  init(Context appContext){
        this.context = appContext;
    }

    //保存String
    public void  save (String key,String msg){
        SharedPreferences sp = context.getSharedPreferences("tableName",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,msg);
        editor.apply();
    }

    public void  loginstatue (String key,boolean b){
        SharedPreferences sp = context.getSharedPreferences("table", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, b);
        editor.apply();
    }

    public boolean getloginstatue (String key){
        SharedPreferences sp = context.getSharedPreferences("table", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }


    public String getString(String key){
        SharedPreferences sp = context.getSharedPreferences("tableName",Context.MODE_PRIVATE);
        String msg = sp.getString(key,"");
        return msg;
    }

    public void save(String key,int msg){
        SharedPreferences sp = context.getSharedPreferences("tableName",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key,msg);
        editor.apply();

    }

    public int getint(String key){
        SharedPreferences sp = context.getSharedPreferences("tableName",Context.MODE_PRIVATE);
        return  sp.getInt(key,0);
    }

    //保存Float
    public void save(String key, float f) {
        SharedPreferences sp = context.getSharedPreferences("tableName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, f);
        editor.apply();
    }
    //读取Float
    public float getFloat(String key) {
        SharedPreferences sp = context.getSharedPreferences("tableName", Context.MODE_PRIVATE);
        return sp.getFloat(key, 0f);
    }

    //保存Long
    public void save(String key, long l) {
        SharedPreferences sp = context.getSharedPreferences("tableName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, l);
        editor.apply();
    }
    //读取Long
    public long getLong(String key) {
        SharedPreferences sp = context.getSharedPreferences("tableName", Context.MODE_PRIVATE);
        return sp.getLong(key, 0l);
    }

    //保存Boolean
    public void save(String key, boolean b) {
        SharedPreferences sp = context.getSharedPreferences("tableName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, b);
        editor.apply();
    }
    //读取Boolean
    public boolean getBoolean(String key) {
        SharedPreferences sp = context.getSharedPreferences("tableName", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

//    public void clear {
//
//    }



}
