package com.mongo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* 测试简单的SpringBoot的功能
* */
@Controller
public class HelloSpringBootController {

    /**
     * @RequestMapping作用:
     * RequestMapping是一个用来处理请求地址映射的注解,可用于类上或方法上.用在类上,表示类中的所有响应请求的方法都是以该地址作为父路径
     * RequestMapping注解有6个属性,把他们分成3类
     * value,method:
     * value:指定请求的实际地址,指定的地址可以是URI Template模式
     * method:指定请求的method,GET、POST、PUT、DELETE等
     * 示例:
     * 1.@RequestMapping(value="/new", method = RequestMethod.GET)
     * 2.@RequestMapping(value="/{day}", method = RequestMethod.GET)
     *
     * consumes,produces
     * consumes:指定处理请求的提交内容类型(Content-Type),例如application/json,text/html
     * produces:指定返回的内容类型,仅当request请求头中的(Accept)类型中包含该指定类型
     *
     * 示例:
     * 1.@RequestMapping(value = "/pets", method = RequestMethod.POST, consumes="application/json")
     * 方法仅处理request Content-Type为“application/json”类型的请求
     * 2.@RequestMapping(value = "/pets/{petId}", method = RequestMethod.GET, produces="application/json")
     * 方法仅处理request请求中Accept头中包含了"application/json"的请求，同时暗示了返回的内容类型为application/json;
     *
     * params,headers
     * params:指定request中必须包含某些参数值时才让该方法处理
     * headers:指定request中必须包含某些指定的head值,才能让该方法处理
     *
     * 示例:
     * 1.@RequestMapping(value = "/pets/{petId}", method = RequestMethod.GET, params="myParam=myValue")
     * 仅处理请求中包含了名为“myParam”，值为“myValue”的请求
     *
     * 2.@RequestMapping(value = "/pets", method = RequestMethod.GET, headers="Referer=http://www.ifeng.com/")
     * 仅处理request的header中包含了指定“Refer”请求头和对应值为“http://www.ifeng.com/”的请求
     */

    /* @ResponseBody作用:是将controller的方法返回的对象通过适当的转化器转换为指定的格式后,写入到response对象的body区,通常用来返回JSON数据或者xml数据
    *  在使用RequestMapping后,返回值通常解析为跳转路径,加上ResponseBody后,返回结果直接写入HTTP Response body中,不会被
    *  解析为跳转路径
    * */
    @RequestMapping(value = "/HelloSpringBootController")
    @ResponseBody
    public String helloSpringBoot() {

        System.out.println("访问helloSpringBoot方法了");
        System.out.println("测试debug");
        return "helloSpringBoot";
    }
}
