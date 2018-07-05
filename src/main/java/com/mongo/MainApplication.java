package com.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* @SpringBootApplication用来标注一个主程序类,说明这是一个SpringBoot的应用
* */
@SpringBootApplication
public class MainApplication {

    //psvm:可以直接补全public static void main方法
    public static void main(String[] args) {

        //方法run:用来启动SpringBoot应用
        SpringApplication.run(MainApplication.class,args);
    }
}
