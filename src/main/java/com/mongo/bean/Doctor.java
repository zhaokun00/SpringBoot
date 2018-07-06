package com.mongo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "doctor")
public class Doctor {

    private String lastName; //当配置文件中没有配置该属性值时,对象默认为null

    private int age; //可以使用基本类型数据,也可以进行转化

    private int sex; //当配置文件没有配置该基本数据类型时,基本数据为0

    private List<Object> list;

    private Map<String,Object> map;

    private Dog dog;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", list=" + list +
                ", map=" + map +
                ", dog=" + dog +
                '}';
    }
}
