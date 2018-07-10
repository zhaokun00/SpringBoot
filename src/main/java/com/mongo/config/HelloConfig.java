package com.mongo.config;

import com.mongo.service.HelloConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
* @Configration注解与@Bean注解
*  @Configuration标注在类上,相当于把该类作为spring的xml配置文件中的<beans>,作用为:配置spring容器(应用上下文)
*
*  示例:
*  //用@Configuration来标注一个类
*   package com.test.spring.support.configuration;

    @Configuration
    public class TestConfiguration {
        public TestConfiguration(){
            System.out.println("spring容器启动初始化。。。");
        }
    }

    相当于以下配置文件:
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context" xmlns:jdbc="http://www.springframework.org/schema/jdbc"
    xmlns:jee="http://www.springframework.org/schema/jee" xmlns:tx="http://www.springframework.org/schema/tx"
    xmlns:util="http://www.springframework.org/schema/util" xmlns:task="http://www.springframework.org/schema/task" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.0.xsd
        http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc-4.0.xsd
        http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-4.0.xsd
        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.0.xsd
        http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-4.0.xsd
        http://www.springframework.org/schema/task http://www.springframework.org/schema/task/spring-task-4.0.xsd" default-lazy-init="false">
    </beans>

    主方法测试:
    package com.test.spring.support.configuration;

    public class TestMain {
        public static void main(String[] args) {

            //@Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
            ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

            //如果加载spring-context.xml文件：
            //ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        }
    }

    运行结果:从运行结果上看spring容器已经启动了
    八月 11, 2016 12:04:11 下午 org.springframework.context.annotation.AnnotationConfigApplicationContext prepareRefresh
信息: Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@203e25d3: startup date [Thu Aug 11 12:04:11 CST 2016]; root of context hierarchy
spring容器启动初始化。。。

  @Bean标注在方法上(返回某个实例的方法),等价于spring的xml配置文件中的<bean>,作用为:注册bean对象
  bean类:
  package com.test.spring.support.configuration;

    public class TestBean {

        public void sayHello(){
            System.out.println("TestBean sayHello...");
        }

        public String toString(){
            return "username:"+this.username+",url:"+this.url+",password:"+this.password;
        }

        public void start(){
            System.out.println("TestBean 初始化。。。");
        }

        public void cleanUp(){
            System.out.println("TestBean 销毁。。。");
        }
    }

   配置类:
   package com.test.spring.support.configuration;

    @Configuration
    public class TestConfiguration {
        public TestConfiguration(){
            System.out.println("spring容器启动初始化。。。");
        }

        //@Bean注解注册bean,同时可以指定初始化和销毁方法
        //@Bean(name="testBean",initMethod="start",destroyMethod="cleanUp") //如果不标注name或value属性,name为方法名
        @Bean
        @Scope("prototype")
        public TestBean testBean() {
            return new TestBean();
        }
    }

    主方法测试:
    package com.test.spring.support.configuration;

    public class TestMain {
        public static void main(String[] args) {
            ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
            //获取bean
            TestBean tb = context.getBean("testBean");
            tb.sayHello();
        }
    }

    备注:
    1.@Bean注解在返回实例的方法上,如果未通过@Bean指定的bean的名称,则默认与标注的方法名相同
    2.@Bean注解默认作用域为单例singleton作用域,可通过@Scope("prototype"设置为多例模式)
    3.既然@Bean的作用是注册bean对象,那么完全可以使用@Component、@Controller、@Service、@Ripository等主机注册bean,当然需要配置@ComponentScan注解进行自动扫描

    示例:
    bean类:
    package com.test.spring.support.configuration;

    //添加注册bean的注解
    @Component
    public class TestBean {

        public void sayHello(){
            System.out.println("TestBean sayHello...");
        }

        public String toString(){
            return "username:"+this.username+",url:"+this.url+",password:"+this.password;
        }
    }

    配置类:
    @Configuration

    //添加自动扫描注解，basePackages为TestBean包路径
    @ComponentScan(basePackages = "com.test.spring.support.configuration")
    public class TestConfiguration {
        public TestConfiguration(){
            System.out.println("spring容器启动初始化。。。");
        }

    }

    主方法:
    public class TestMain {
        public static void main(String[] args) {
            ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);
            //获取bean
            TestBean tb = context.getBean("testBean");
            tb.sayHello();
        }
    }
* */
@Configuration
public class HelloConfig {

    @Bean
    public HelloConfigService helloConfigService() {

        return new HelloConfigService();
    }

}
