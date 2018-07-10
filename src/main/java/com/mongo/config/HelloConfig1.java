package com.mongo.config;

import com.mongo.service.HelloConfigService1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig1 {

    @Bean(value = "helloConfigService1")
    public HelloConfigService1 helloConfigService() {
        return new HelloConfigService1();
    }
}
