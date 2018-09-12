package com.zzy.servercenter;

import android.app.Application;
import android.content.Context;

import com.zzy.core.serverCenter.SCM;

/**
 * @author zzy
 * @date 2018/9/12
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SCM.getInstance().init(this);
    }
}
