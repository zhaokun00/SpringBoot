package com.mongo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
* 测试使用注解@value进行获取配置文件中的内容
* 注解value只能用于简单数据的注入,即基本类型的数据注入,如果使用@Value注解时可以不用使用@ConfigurationProperties注解
* */

/*
* Spring中提供@Component的三个衍生注解(功能目前来讲是一致的)
* @Controller : WEB层
* @Service    : 业务层
* @Repository : 持久层
*
* @Component(value="component") //注解组件由一个value属性值来标识这个组件的名称
* 属性注入的注解(使用注解注入的方式,可以不用提供set方法)
* @Value:用于普通类型
* @Autowired:自动装配,默认按类型进行装配,通常用作对象的属性注入
* @Qualifier:强制使用名称注入
* @Resource:相当于@Autowired和@Qualifier一起使用
*
* @Qualifier示例:
* 有如下接口:
* public interface EmployeeService {
*   public EmployeeDto getEmployeeById(Long id);
* }
*
* 有以下两个实现类:
* EmployeeServiceImpl和EmployeeServiceImpl1
*
* @Service("service") //组件的名称service
  public class EmployeeServiceImpl implements EmployeeService {
    public EmployeeDto getEmployeeById(Long id) {
        return new EmployeeDto();
    }
 }

 @Service("service1")  //组件的名称service
 public class EmployeeServiceImpl1 implements EmployeeService {
    public EmployeeDto getEmployeeById(Long id) {
        return new EmployeeDto();
    }
 }

 调用代码:
 @Controller
 @RequestMapping("/employee.do")
 public class EmployeeInfoControl {

    //根据类型自动注入,该处会报错,因为上面有两个接口实现了EmployeeService的接口,这个时候就要用到@Qualifier注解了,qualifier的意思是合格者,通过这个标识表明了哪一个实现类才是我们所需要的,需要注意的是@Qualifier的参数名称必须为我们之前定义的
    @Autowired //根据类型注入
//    @Qualifier("service") //根据名称进行注入
    EmployeeService employeeService;
 }
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
