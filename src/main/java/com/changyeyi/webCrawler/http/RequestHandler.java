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

    public String post(String url,List<NameValuePair> formData,HttpHost host){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(url);
        if(host!=null){
            RequestConfig build = RequestConfig.custom().setProxy(host).setConnectTimeout(3000).setSocketTimeout(3000).build();
            httpPost.setConfig(build);
        }
        httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.setHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpPost.setHeader("Connection", "keep-alive");
//        httpPost.setHeader("Content-Length", "23");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader("Cookie", "JSESSIONID=ABAAABAAADEAAFI9612FBE72694F57591E0C4CF0E047D05; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1542798240; _ga=GA1.2.1633207753.1542798240; _gid=GA1.2.1093258419.1542798240; user_trace_token=20181121190358-24fb9399-ed7d-11e8-8abb-5254005c3644; LGSID=20181121190358-24fb9632-ed7d-11e8-8abb-5254005c3644; PRE_UTM=; PRE_HOST=www.baidu.com; PRE_SITE=https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3Db0wvx-5anAN2vM12Ft562D2_0EAIMJOoRi845wFUiO3%26wd%3D%26eqid%3Dcc481889000424f9000000045bf53b9a; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; LGUID=20181121190358-24fb97cc-ed7d-11e8-8abb-5254005c3644; index_location_city=%E5%85%A8%E5%9B%BD; TG-TRACK-CODE=index_navigation; _gat=1; LGRID=20181121190650-8bed3abb-ed7d-11e8-b2eb-52540 0f775ce; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1542798413; SEARCH_ID=2832f6179ae544bb8b1fe080c0968642");
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
                return null;
            }
            return EntityUtils.toString(response.getEntity(), "gbk");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
