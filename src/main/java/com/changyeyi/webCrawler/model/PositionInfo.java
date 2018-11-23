package com.changyeyi.webCrawler.model;

import lombok.Data;

import java.util.Date;

/**
 * @author :  wzj
 * @date :Created in 2018/11/22
 */
@Data
public class PositionInfo {
    private String companyShortName;
    private String city;
    private String salary;
    private String workYear;
    private String education;
    private Date createTime;
}
