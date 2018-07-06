package com.mongo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
/*
* 测试使用注解ConfigurationProperties进行属性的注入
* 作用:告诉springboot本类中的所有属性要和配置文件中相关的配置进行绑定
* 参数prefix = "teacher":是要与配置文件中哪个下面的所有属性进行一一映射
* 注意事项:在使用@ConfigurationProperties注解时要写属性的get和set方法
* */
@ConfigurationProperties(prefix = "teacher")
public class Teacher {

//    当
    private String lastName;

    private Integer age;

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
        return "Teacher{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                '}';
    }
}
