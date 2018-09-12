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
@ScActionAnnotation("getProjectList")
public class ProjectListAction implements ScAction {
    private static final String TAG = "ProjectListAction";

    @Override
    public void invoke(Context context, String param, ScCallback callback) {
        Log.e(TAG,"ProjectListAction: invoke me! ");
        callback.onCallback(true,"I'm ProjectListAction!","");
    }
}
