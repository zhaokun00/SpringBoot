package com.mongo.controller;

import com.mongo.bean.Student;
import com.mongo.bean.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/ConfigController")
public class ConfigController {

    /*
    * 从Spring容器中获取该类型的数据,自动注入进来
    * */
    @Autowired
    private Teacher teacher;

    @Autowired
    private Student student;

    @RequestMapping("/helloTeacher")
    public String helloTeacher() {
        return teacher.toString();
    }

    /*
    * 直接从配置文件中读取数据
    * */
    @Value("${student.lastName}")
    String studentLastName;

    @RequestMapping("/helloConfig")
    public String helloConfig() {

        //在此处用注解是错误地写法,在编译时会报错
//        @Value("${student.lastName}")
//        String lastName;

        return studentLastName;
    }

    @RequestMapping("/helloStudent")
    //返回对象时,直接转换了json的格式
    public Student helloStudent() {

        return student;
    }

    @RequestMapping(value = "/helloStudentJson",method = RequestMethod.POST)
    /*
    * @RequestBody注解是将HTTP请求正文插入方法中,使用适合的HttpMessageConverter将请求体写到某个对象
    * 作用:该注解用于读取Request请求的body部分数据,使用系统默认配置的HttpMessageConver进行解析,然后把相应的数据绑定到对象上
    * 直接将发送的过来的json字符串转换为对象
    * */
    public Student helloStudentJson(@RequestBody Student s) {

        System.out.println("helloStudentJson");
        System.out.println(s.toString());

        return s;
    }
}
