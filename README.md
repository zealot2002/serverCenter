# serverCenter

轻量级的服务中心，统一的访问api，便于模块间解耦。

用法：
1，添加依赖



	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}



	dependencies {
	        implementation 'com.github.zealot2002:serverCenter:0.1.0'
	}
        
2，所在module的gradle文件，添加：
defaultConfig {
        ..........................
        ..........................
        
        //SCM add
        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath = true
                arguments = [moduleName: project.getName()]
            }
        }
    }

3，制作ScAction，如下：
@ScActionAnnotation("getUserInfo")
public class UserInfoAction implements ScAction {
    private static final String TAG = "UserInfoAction";

    @Override
    public void invoke(Context context, String param, ScCallback callback) {
        Log.e(TAG,"UserInfoAction: invoke me! ");
        callback.onCallback(true,"I'm UserInfoAction!","");
    }
}

4，使用者调用
try {
    SCM.getInstance().req(this, "getUserInfo", new ScCallback() {
        @Override
        public void onCallback(boolean b, String data, String tag) {
            Log.e(TAG,"返回:"+data);
        }
    });
} catch (Exception e) {
    e.printStackTrace();
}

5，初始化
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        //init scm
        SCM.getInstance().init(this);
    }
}

6，完。


