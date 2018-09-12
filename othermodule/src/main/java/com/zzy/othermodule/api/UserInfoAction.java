package com.zzy.othermodule.api;

import android.content.Context;
import android.util.Log;

import com.zzy.annotations.ScActionAnnotation;
import com.zzy.core.serverCenter.ScAction;
import com.zzy.core.serverCenter.ScCallback;

/**
 * @author zzy
 * @date 2018/9/12
 */
@ScActionAnnotation("getUserInfo")
public class UserInfoAction implements ScAction {
    private static final String TAG = "UserInfoAction";

    @Override
    public void invoke(Context context, String param, ScCallback callback) {
        Log.e(TAG,"UserInfoAction: invoke me! ");
        callback.onCallback(true,"I'm UserInfoAction!","");
    }
}
