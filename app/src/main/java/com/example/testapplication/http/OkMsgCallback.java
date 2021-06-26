package com.example.testapplication.http;

public interface OkMsgCallback {

    void fail(String error);
    void success(int code, String body);

}
