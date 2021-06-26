package com.example.testapplication.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpMgr {
    private static String TAG = "OkHttpMgr";

    private OkHttpMgr() {}

    private static final class Holder {
        private static final OkHttpMgr instance = new OkHttpMgr();
    }

    public static OkHttpMgr getInstance() {
        return Holder.instance;
    }

    private OkHttpClient client;
    private String baseUrl;

    public void init(String baseUrl) {
        if(!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        this.baseUrl = baseUrl;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        int TIME_OUT = 5;
        builder.connectTimeout(TIME_OUT,TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        client = builder.build();
    }

    public void postJson(String urlTag, Map<String, String> map, OkMsgCallback callback) {
        JSONObject jso = new JSONObject();
        if(map != null && map.size() > 0) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jso.put(entry.getKey(), entry.getValue());
                }
            } catch (JSONException e) {
                Log.e(TAG, "post-for-error:" + e.getMessage());
            }
        }
        String url = baseUrl + urlTag;
        MediaType type = MediaType.parse("application/json;charset=UTF-8");
        RequestBody body = RequestBody.create(type, jso.toString());
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.post(body).build();
        if(client == null) {
            return;
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.fail(e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response != null) {
                    try {
                        int code = response.code();
                        String body = response.body().string();
                        callback.success(code, body);
                    } catch (Exception e) {
                        Log.e(TAG, "onResponse-error:" + e.getMessage());
                    }
                }
            }
        });
    }

    public void shutdown() {
        if(client != null) {
            client.dispatcher().executorService().shutdown();
        }
    }

    //清理连接池
    public void cleanPool() {
        if(client != null) {
            client.connectionPool().evictAll();
        }
    }

    //关闭缓存
    public void closeCache() {
        if(client != null) {
            try {
                client.cache().close();
            } catch (IOException e) {
                Log.e(TAG, "closeCache-error:" + e.getMessage());
            }
        }
    }

}
