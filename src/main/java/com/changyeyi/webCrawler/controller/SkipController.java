package com.changyeyi.webCrawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author :  wzj
 * @date :Created in 2018/11/23
 */
@Controller
public class SkipController {
    @GetMapping("/crawler")
    public String crawler(){
        return "/crawler";
    }
}
