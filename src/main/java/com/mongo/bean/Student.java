package com.mongo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
* 测试使用注解@value进行获取配置文件中的内容
* 注解value只能用于简单数据的注入,即基本类型的数据注入,如果使用@Value注解时可以不用使用@ConfigurationProperties注解
* */
@Component
public class Student {

    @Value("${student.lastName}") //不写value的情况
    private String lastName;

    @Value(value="${student.age}") //写value的情况
    private Integer age;

    @Value(value="${student.boss}")
    private Boolean boss;

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

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    @Override
    public String toString() {
        return "Student{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                '}';
    }
}
