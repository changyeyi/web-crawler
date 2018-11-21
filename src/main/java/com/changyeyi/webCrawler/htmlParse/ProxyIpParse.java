package com.changyeyi.webCrawler.htmlParse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :  wzj
 * @date :Created in 2018/11/21
 */
@Component
public class ProxyIpParse {

    private Map<String,Integer> map=new HashMap<>();

    public void parse(Document document){
        String cssQuery="table#ip_list";
        Element tbody = document.select(cssQuery).first().child(0);
        Element child = tbody.child(1);
        while(child!=null){
            String speed=child.child(6).select("div[class=bar]").attr("title");
            if(child.child(5).ownText().equals("HTTPS")&&Double.parseDouble(speed.substring(0, speed.indexOf('秒')))<2){
                map.put(child.child(1).ownText(),Integer.parseInt(child.child(2).ownText()));
            }
            child=child.nextElementSibling();
        }
    }

    public Map<String,Integer> getMap(){
        return map;
    }
}
