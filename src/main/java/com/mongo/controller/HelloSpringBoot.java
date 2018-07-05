package com.mongo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloSpringBoot {

    @RequestMapping(value = "/helloSpringBoot")
    @ResponseBody
    public String helloSpringBoot() {

        System.out.println("访问helloSpringBoot方法了");
        System.out.println("测试debug");
        return "helloSpringBoot";
    }
}
