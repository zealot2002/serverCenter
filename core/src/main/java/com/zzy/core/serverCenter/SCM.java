package com.zzy.core.serverCenter;

import android.content.Context;

import com.zzy.core.utils.ClassUtils;
import com.zzy.core.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Server Center Management
 * @author zzy
 * @date 2018/8/13
 */

public class SCM {
    private static final String TAG = "SCM";
    private static final String SCACTION_ROOT_PAKCAGE = "com.zzy.processor.generated";

    private static final SCM ourInstance = new SCM();
    public static SCM getInstance() {
        return ourInstance;
    }

    private SCM() {
    }

    private HashMap<String, ScAction> actionMap = new HashMap<>();
    private volatile boolean isReady;
/******************************************************************************************************/

    /**
     * scm init
     * @param context
     */
    public void init(Context context){
        try {
            List<String> classFileNames = ClassUtils.getFileNameByPackageName(context, SCACTION_ROOT_PAKCAGE);
            for (String className : classFileNames) {
                String s = Class.forName(className).newInstance().toString();
                Map<String,String> map = StringUtils.mapStringToMap(s);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    Class clazz = Class.forName(entry.getValue());
                    registerAction(entry.getKey().trim(), (ScAction) clazz.newInstance());
                }
            }
            isReady = true;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void req(Context context,String action,ScCallback callback) throws Exception{
        req(context,action,null,callback);
    }
    /**
     * 请求服务
     *
     * @param action   请求的服务名称
     * @param param    携带参数，json格式的string
     * @param callback  标准返回
     * @throws Exception
     */
    public void req(Context context,String action,String param,ScCallback callback) throws Exception{
        if(!isReady){
            throw new RuntimeException("SCM is not ready! pls wait!");
        }
        if(!actionMap.containsKey(action)){
            throw new RuntimeException("SCM action not found! name:"+action);
        }
        ScAction scAction = actionMap.get(action);
        scAction.invoke(context,param,callback);
    }

    /**
     * 注册服务
     *
     * @param action
     * @param scAction
     * @throws Exception
     */
    private void registerAction(String action,ScAction scAction)throws Exception{
        if(scAction == null||action == null){
            throw new Exception("bad input param!");
        }
        if(!actionMap.containsKey(action)){
//            throw new Exception("SCM: the same name action has already exist! action:"+action);
            actionMap.put(action,scAction);
        }
    }
}
