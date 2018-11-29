package com.zzy.othermodule.api;

import android.app.Application;
import android.util.Log;

import com.zzy.annotations.ScModuleInitAnnotation;
import com.zzy.core.serverCenter.ScModuleInit;

/**
 * @author zzy
 * @date 2018/9/12
 */
@ScModuleInitAnnotation("TestInit")
public class TestInit implements ScModuleInit {
    private static final String TAG = "TestInit";

    @Override
    public void invoke(Application application) {
        Log.e(TAG,"TestInit: invoke me! ");
    }
}
