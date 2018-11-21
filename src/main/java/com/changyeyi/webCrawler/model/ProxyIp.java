package com.changyeyi.webCrawler.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author :  wzj
 * @date :Created in 2018/11/21
 */
@Data
public class ProxyIp implements Serializable {
    private static final long serialVersionUID = -2212806237188193559L;
    private String ip;
    private Integer port;
    private String type;
    private Double speed;
}
