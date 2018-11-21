package com.changyeyi.webCrawler.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * http 请求处理
 * @author :  wzj
 * @date :Created in 2018/11/21
 */
@Slf4j
@Component
public class RequestHandler {

    public String get(String url){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(url);

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;" +
                "q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Host", "www.xicidaili.com");
        httpGet.setHeader("Pragma", "no-cache");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()!=200){
                log.error("url:"+url+",code:"+response.getStatusLine().getStatusCode());
            }
            return EntityUtils.toString(response.getEntity(), "gbk");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String post(String url,String ip,Integer port,List<NameValuePair> formData){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(url);

        HttpHost proxy = new HttpHost(ip, port);
        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).
                setSocketTimeout(3000).build();
        httpPost.setConfig(config);
        httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpPost.setHeader("Connection", "keep-alive");
//        httpPost.setHeader("Content-Length", "23");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader("Cookie", "_ga=GA1.2.2085062207.1542700058; _gid=GA1.2.620205390.1542700058; user_trace_token=20181120154738-8d2e8ccb-ec98-11e8-ab18-525400f775ce; LGUID=20181120154738-8d2e90bf-ec98-11e8-ab18-525400f775ce; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID=ABAAABAAAGGABCB71C6F1972E455467E8489F5208C6451E; LGSID=20181121143051-fdc3bcfb-ed56-11e8-8a9a-5254005c3644; TG-TRACK-CODE=index_navigation; X_HTTP_TOKEN=2126b5f3299054fc807e3ff495682ff6; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216734fba6fea17-0c3c3a6c0a6263-5701732-2073600-16734fba6ff295%22%2C%22%24device_id%22%3A%2216734fba6fea17-0c3c3a6c0a6263-5701732-2073600-16734fba6ff295%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1542700058,1542781852,1542782233,1542782237; _gat=1; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1542783324; LGRID=20181121145523-6b438fd4-ed5a-11e8-b143-525400f775ce; SEARCH_ID=4c78ab36b7944cb79d9908b6adc182fa");
        httpPost.setHeader("Host", "www.lagou.com");
        httpPost.setHeader("Origin", "https://www.lagou.com");
        httpPost.setHeader("Referer", "https://www.lagou.com/jobs/list_java?city=%E5%85%A8%E5%9B%BD&cl=false&fromSearch=true&labelWords=&suginput=");
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36");
        httpPost.setHeader("X-Anit-Forge-Code", "a");
        httpPost.setHeader("X-Anit-Forge-Token", "None");
        httpPost.setHeader("X-Requested-With", "XMLHttpRequest");


        try {
            httpPost.setEntity(new UrlEncodedFormEntity(formData));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()!=200){
                log.error("url:"+url+",code:"+response.getStatusLine().getStatusCode());
            }
            return EntityUtils.toString(response.getEntity(), "gbk");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
