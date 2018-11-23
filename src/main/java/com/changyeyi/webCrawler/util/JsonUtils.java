package com.changyeyi.webCrawler.util;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Json处理工具类
 * @author :  wzj
 * @date :Created in 2018/11/22
 */
@Slf4j
public class JsonUtils {
    private static final Gson gson=new Gson();
    /**
     * 递归获取JSON中指定参数名的数据
     * @param json  数据源
     * @param args  每次递归获取的目标参数名
     * @return      目标数据
     */
    public static Object recursion(String json,String[] args){
        Map origin = gson.fromJson(json, Map.class);
        if(origin==null){return null;}
        for (String arg : args) {
            Object value = origin.get(arg);
            if(!(value instanceof Map)){
                return value;
            }
            origin = (Map)value;
        }
        return origin;
    }

}
