package com.changyeyi.webCrawler.controller;

import com.changyeyi.webCrawler.constant.UrlConst;
import com.changyeyi.webCrawler.htmlParse.LagouParse;
import com.changyeyi.webCrawler.http.RequestHandler;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author :  wzj
 * @date :Created in 2018/11/23
 */
@RestController
@RequestMapping("/lagou")
public class LagouCrawlerController {
    @Autowired
    private RequestHandler requestHandler;
    @Autowired
    private LagouParse lagouParse;
    @GetMapping("city")
    public List<Map> getByKd(@RequestParam("kd")String kd,@RequestParam("pn")Integer pn,@RequestParam(value = "city",required = false)String city) throws InterruptedException {
        List<Map> result=new ArrayList<>();
        Map<String,Integer> cityMap=new HashMap<>();
        lagouParse.setCityMap(cityMap);
        result.add(cityMap);
        Map<String,Map<String,Integer>> salaryMap=new HashMap<>();
        lagouParse.setSalaryMap(salaryMap);
        result.add(salaryMap);
        if(!StringUtils.isEmpty(city)){
            lagouParse.setCityName(city);
        }
        for (int i=0;i<pn;i++){
            System.out.println("-------------   "+i+"   ---------------");
            List<NameValuePair> list=new ArrayList<>();
            list.add(new BasicNameValuePair("kd",kd));
            if(i==0){
                list.add(new BasicNameValuePair("first","true"));
                list.add(new BasicNameValuePair("pn","1"));
            }else {
                list.add(new BasicNameValuePair("first","false"));
                list.add(new BasicNameValuePair("pn",i+1+""));
            }
            String content ;
            boolean parse;
            do{
                content=requestHandler.post(UrlConst.lagou,list,null);
                parse = lagouParse.parse(content);
            }while(content==null|| !parse);

            Thread.sleep(new Random().nextInt(500));
        }
        return result;
    }
}
