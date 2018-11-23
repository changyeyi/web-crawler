package com.changyeyi.webCrawler.http;

import com.changyeyi.webCrawler.proxyIP.ObtainIp;
import com.changyeyi.webCrawler.htmlParse.LagouParse;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author :  wzj
 * @date :Created in 2018/11/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RequestHandlerTest {
    @Autowired
    private RequestHandler requestHandler;
    @Autowired
    private LagouParse lagouParse;
    @Autowired
    private ObtainIp obtainIp;
    @Test
    public void test() throws InterruptedException {
        String url="https://www.lagou.com/jobs/positionAjax.json?needAddtionalResult=false";
        for (int i=0;i<200;i++){
            System.out.println("-------------   "+i+"   ---------------");
            List<org.apache.http.NameValuePair> list=new ArrayList<>();
            list.add(new BasicNameValuePair("kd","java大数据"));
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
//                HttpHost httpHost = new HttpHost("59.44.43.198",80);

                content=requestHandler.post(url,list,null);
                parse = lagouParse.parse(content);
            }while(content==null||parse==false);

            Thread.sleep(new Random().nextInt(1000));
        }
    }
}