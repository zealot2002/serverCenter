package com.zzy.helper.api;

import android.content.Context;
import android.util.Log;

import com.zzy.annotations.ScActionAnnotation;
import com.zzy.core.serverCenter.ScAction;
import com.zzy.core.serverCenter.ScCallback;


/**
 * @author zzy
 * @date 2018/9/12
 */
@ScActionAnnotation("getConfig")
public class ConfigAction implements ScAction {
    private static final String TAG = "ConfigAction";

    @Override
    public void invoke(Context context, String param, ScCallback callback) {
        Log.e(TAG,"ConfigAction: invoke me! ");
        callback.onCallback(true,"I'm ConfigAction!","");
    }
}
