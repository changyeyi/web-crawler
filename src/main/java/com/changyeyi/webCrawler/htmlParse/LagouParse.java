package com.changyeyi.webCrawler.htmlParse;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 拉勾网招聘信息解析
 * @author :  wzj
 * @date :Created in 2018/11/21
 */
@Component
public class LagouParse {
    private Map<String,Integer> cityMap=new HashMap<>();
    @Autowired
    private Gson gson;

    public void parse(String content){
        Map map =null;
        try{
            map = gson.fromJson(content, Map.class);
        }catch (Exception e){
            System.out.println(content);
        }
        List<Map<String,String>> list= (List<Map<String,String>>)objToMap(objToMap(map.get("content")).get("positionResult")).get("result");
        for (Map<String, String> positionInfo : list) {
            String city = positionInfo.get("city");
            cityMap.merge(city, 1, (a, b) -> a + b);
        }
        printCityMap();
    }

    /**
     * 输出数据前十的数据
     */
    private void printCityMap() {
        Set<Map.Entry<String, Integer>> entries = cityMap.entrySet();
        List<Integer> count = entries.stream().map(Map.Entry::getValue).sorted((o1, o2) -> o2 - o1).collect(Collectors.toList());
        Integer limit = count.get(count.size() / 3)>20?count.get(count.size() / 3):20;
        for (Map.Entry<String, Integer> entry : entries) {
            if(entry.getValue()>limit){

                System.out.print(entry.getKey()+":"+entry.getValue()+"|");
            }
        }
        System.out.println();
    }

    private Map objToMap(Object obj){
        if(obj instanceof Map){
            return (Map)obj;
        }else {
            throw new RuntimeException("类型转换失败");
        }
    }
}
