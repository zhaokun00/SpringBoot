package com.mongo;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/*
* @SpringBootApplication用来标注一个主程序类(作用到类上),说明这是一个SpringBoot的应用
*
* */
@SpringBootApplication
@Slf4j
public class MainApplication {

    //psvm:可以直接补全public static void main方法
    public static void main(String[] args) {

        //方法run:用来启动SpringBoot应用
        SpringApplication.run(MainApplication.class,args);

    }
}
