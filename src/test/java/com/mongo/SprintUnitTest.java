package com.mongo;

import com.mongo.bean.Doctor;
import com.mongo.bean.Farmer;
import com.mongo.bean.Student;
import com.mongo.bean.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/*
* SpringBoot单元测试:
* 可以在测试期间很方便的类似编码一样进行自动注入等容器功能
* */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SprintUnitTest {

    @Autowired
    Student student;

    @Autowired
    Teacher teacher;

    @Autowired
    Doctor doctor;

    @Autowired
    ApplicationContext context;

    @Autowired
    Farmer farmer;

    @Test
    public void testStudent() {
        System.out.println(student);
    }

    @Test
    public void testTeacher() {
        System.out.println(teacher);
    }

    @Test
    public void testDoctor() {
        System.out.println(doctor);
    }

    @Test
    public void testConfig() {
        Boolean result = context.containsBean("helloConfigService2");
        System.out.println("result = " + result);
    }

    @Test
    public void testFarmer() {

        System.out.println(farmer);
    }

    @Test
    public void testPrintf() {

        Logger logger = LoggerFactory.getLogger(getClass());

        logger.info("infor信息1");
    }

    @Test
    public void testSlf() {
        log.info("infor信息1");
    }
}
