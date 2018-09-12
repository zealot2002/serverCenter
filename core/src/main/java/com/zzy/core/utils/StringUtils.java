package com.zzy.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zzy
 * @date 2018/9/12
 */

public class StringUtils {
    public static Map<String,String> mapStringToMap(String str){
        str=str.substring(1, str.length()-1);
        String[] strs=str.split(",");
        Map<String,String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key=string.split("=")[0];
            String value=string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}
