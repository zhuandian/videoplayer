package com.example.videoplayer;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * desc :
 * author：xiedong
 * date：2020/2/4
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "3b3a8173f7d02cc1222afb432eee0868");
    }
}
