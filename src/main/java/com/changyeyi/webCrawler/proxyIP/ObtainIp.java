package com.changyeyi.webCrawler.proxyIP;

import com.changyeyi.webCrawler.htmlParse.ProxyIpParse;
import com.changyeyi.webCrawler.http.RequestHandler;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 代理IP获取
 * @author :  wzj
 * @date :Created in 2018/11/21
 */
@Component
public class ObtainIp {
    @Autowired
    private RequestHandler requestHandler;
    @Autowired
    private ProxyIpParse proxyIpParse;

    public Map<String,Integer> proxyIp(){
        String url="http://www.xicidaili.com/nn/1";
        String content = requestHandler.get(url);
        proxyIpParse.parse(Jsoup.parse(content));
        return proxyIpParse.getMap();
    }
}
