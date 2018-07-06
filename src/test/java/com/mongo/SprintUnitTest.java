package com.mongo;

import com.mongo.bean.Doctor;
import com.mongo.bean.Student;
import com.mongo.bean.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
* SpringBoot单元测试:
* 可以在测试期间很方便的类似编码一样进行自动注入等容器功能
* */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SprintUnitTest {

    @Autowired
    Student student;

    @Autowired
    Teacher teacher;

    @Autowired
    Doctor doctor;

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
}
