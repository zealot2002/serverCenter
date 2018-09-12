package com.zzy.servercenter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.zzy.core.serverCenter.SCM;
import com.zzy.core.serverCenter.ScCallback;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResume() {
        super.onResume();

        try {
            SCM.getInstance().req(this, "getUserInfo", new ScCallback() {
                @Override
                public void onCallback(boolean b, String data, String tag) {
                    Log.e(TAG,"返回:"+data);
                }
            });
            SCM.getInstance().req(this, "getProjectList", new ScCallback() {
                @Override
                public void onCallback(boolean b, String data, String tag) {
                    Log.e(TAG,"返回:"+data);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
