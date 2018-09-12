package com.zzy.core.serverCenter;

import android.content.Context;

/**
 * @author zzy
 * @date 2018/8/13
 */

public interface ScAction {
    void invoke(Context context, String param, ScCallback callback);
}
