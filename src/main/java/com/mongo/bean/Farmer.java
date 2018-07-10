package com.mongo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/*
* 注解@PropertySource作用:加载指定的属性文件(用户自己编写的配置文件而非系统默认的application.properties文件)
* */
@PropertySource(value = {"classpath:farmer.properties"})
@Component
public class Farmer {

    @Value("${farmer.lastName}")
    private String lastName;

    @Value("${farmer.age}")
    private Integer age;

    @Value("${farmer.sex}")
    private Integer sex;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
