package com.mongo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller

/*
* 在一个类上标记该注解,表示这个类中所有的方法在访问都会添加填写的路径
* */
@RequestMapping("/QuickController")

/*
* 在一个类上进行标记该注解,表示这个类中所有的方法返回的数据直接写给浏览器,如果是对象转为json数据
* */
//@ResponseBody

/*
* @RestController是注解@ResponseBody与注解@Controller的综合注解,因此可以使用这个一个注解替代其他两个注解
* */
@RestController
public class QuickController {

    @RequestMapping("/helloQuickController")
    public String helloQuickController() {

        System.out.println("helloQuickController");
        return "helloQuickController";
    }
}
