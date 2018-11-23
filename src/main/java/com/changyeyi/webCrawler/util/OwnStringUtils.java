package com.changyeyi.webCrawler.util;

/**
 * @author :  wzj
 * @date :Created in 2018/11/23
 */
public class OwnStringUtils {

    public static String[] replaceAndSplit(String str,String split,String[] replaceArray ){
        for (String replace : replaceArray) {
            str=str.replaceAll(replace,"");
        }
        return str.split(split);
    }
}
