package com.changyeyi.webCrawler.http;

import com.changyeyi.webCrawler.ProxyIP.ObtainIp;
import com.changyeyi.webCrawler.htmlParse.LagouParse;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Map<String, Integer> map = obtainIp.proxyIp();
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        ArrayList<Map.Entry<String, Integer>> arrayList = new ArrayList<>(entrySet);
        int size=0;
        String url="https://www.lagou.com/jobs/positionAjax.json?needAddtionalResult=false";
        for (int i=0;i<500;i++){
            List<org.apache.http.NameValuePair> list=new ArrayList<>();
            list.add(new BasicNameValuePair("kd","java大数据"));
            if(i==0){
                list.add(new BasicNameValuePair("first","true"));
                list.add(new BasicNameValuePair("pn","1"));
            }else {
                list.add(new BasicNameValuePair("first","false"));
                list.add(new BasicNameValuePair("pn",i+1+""));
            }
            if(size==entrySet.size())size=0;
            String content ;
            do{
                content=requestHandler.post(url, arrayList.get(size).getKey(), arrayList.get(size).getValue(), list);
                if(content==null){
                    arrayList.remove(size);
                }
                size++;
            }while(content==null);

            lagouParse.parse(content);

        }
    }
}