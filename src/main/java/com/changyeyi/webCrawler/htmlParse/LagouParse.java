package com.changyeyi.webCrawler.htmlParse;

import com.changyeyi.webCrawler.util.JsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 拉勾网招聘信息解析
 *
 * @author :  wzj
 * @date :Created in 2018/11/21
 */
@Data
@Slf4j
@Component
public class LagouParse {
    private Map<String, Integer> cityMap;
    private Map<String, Map<String, Integer>> salaryMap;
    private String cityName;

    public boolean parse(String content) {
        //从JSON中获取指定数据
        String[] args = new String[]{"content", "positionResult", "result"};
        Object data = JsonUtils.recursion(content, args);
        List<Map<String, String>> list;
        if (data instanceof List) {
            list = (List<Map<String, String>>) data;
        } else {
            return false;
        }
        //进行数据分析统计
        analysisAndCount(list);
        //数据输出
//        printCityMap();
        return true;
    }

    /**
     * 数据分析与统计
     *
     * @param list 需要分析的数据
     */
    private void analysisAndCount(List<Map<String, String>> list) {
        for (Map<String, String> positionInfo : list) {
            String city = positionInfo.get("city");
            String salary = positionInfo.get("salary");
            if ((!StringUtils.isEmpty(cityName)) && cityName.contains(city)) {
                salaryGroup(city, salary);
            }
            cityMap.merge(city, 1, (a, b) -> a + b);
        }
    }

    /**
     * 按薪资分类
     *
     * @param city   招聘城市
     * @param salary 找平薪水
     */
    private void salaryGroup(String city, String salary) {
        Map<String, Integer> salaryCount = salaryMap.get(city);
        if(salaryCount==null){
            salaryCount=new HashMap<>();
            salaryCount.put(salary,1);
            salaryMap.put(city,salaryCount);
        }else {
            salaryCount.put(salary,salaryCount.get(salary)==null?1:salaryCount.get(salary)+1);
        }
    }

    /**
     * 输出数据
     */
    private void printCityMap() {
        Set<String> keySet = cityMap.keySet();
        for (String key : keySet) {
            System.out.print(key + ":" + cityMap.get(key) + "|");
        }
        System.out.println();
    }

}
