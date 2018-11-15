package com.mongo;

import com.mongo.bean.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.dao.Dao;
import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

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

    /*
    * 测试redis中的StringRedisTemplate类
    * */
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Test
    public void testStringRedisTemplate() {

        log.info("测试StringRedisTemplate");
//        stringRedisTemplate.opsForValue().append("age","20");

        stringRedisTemplate.opsForValue().set("age","20");
    }

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void testRedisTemplate() {
        log.info("测试RedisTemplate");

        CacheTeacher t = new CacheTeacher();

        t.setId(10);
        t.setName("zhaokun");

        redisTemplate.opsForValue().set("teacher40",t);

    }

    @Test
    public void testRedisTemplate1() {
        log.info("测试RedisTemplate");

//        CacheTeacher t = new CacheTeacher();
//
//        t.setId(1);
//        t.setName("zhaokun");
//
//        techerRedisTemplate.opsForValue().set("teacher11",t);

//        CacheTeacher t = (CacheTeacher)techerRedisTemplate.opsForValue().get("techer:\"6techer\"");

//        CacheTeacher t = (CacheTeacher)techerRedisTemplate.opsForValue().get("aaa");

//        String s = (String)redisTemplate.opsForValue().get("aaa");

//        log.info(s);
    }

    @Autowired
    public DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testDataSource() {
        log.info(dataSource.getClass().toString());

//      dataSource.getConnection();

    }

//    @Autowired
//    Dao dao;
//
//    @Test
//    public void testDao() {
//
//        DbStudent student = dao.fetch(DbStudent.class,2);
//
//        log.info(student.toString());
//    }
//
//    @Test
//    public void testJson() {
//
//        DbStudent student = new DbStudent();
//
//        student.setId(100);
//        student.setName("zhaokun");
//
//        String s = Json.toJson(student);
//
//        log.info(Json.toJson(student));
//
//        DbStudent ss = Json.fromJson(DbStudent.class,s);
//
//        log.info(ss.toString());
//    }
//
//    @Test
//    public void testHttp() {
//
//        Header header = Header.create();
//        header.asJsonContentType();
//
//        Response response = Http.post3("http://api.m.mtime.cn/PageSubArea/TrailerList.api",null,header,10000);
//
//        log.info(response.getContent());
//
//
//    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSendRabbitMq() {

        rabbitTemplate.convertAndSend("exchange.direct","route_key_mongo","zhaokun");
    }

    @Test
    public void testSendRabbitMqObject() {

        RabbitMqStudent m = new RabbitMqStudent();

        m.setId(200);
        m.setName("zhaokun");

        rabbitTemplate.convertAndSend("exchange.direct","route_key_mongo",m);
    }

    @Test
    public void testReceiveMqObject() {

        RabbitMqStudent m = (RabbitMqStudent)rabbitTemplate.receiveAndConvert("queue_mongo");

        log.info(m.toString());
    }

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void testAmqpAdmin() {

        Exchange exchange = new DirectExchange("exchange_direct_p");

        amqpAdmin.declareExchange(exchange);

        Queue queue = new Queue("queue_direct");

        amqpAdmin.declareQueue(queue);

        Binding binding = new Binding("queue_direct", Binding.DestinationType.QUEUE,"exchange_direct_p","route_key_q",null);
        amqpAdmin.declareBinding(binding);

        RabbitMqStudent m = new RabbitMqStudent();

        m.setId(2000);
        m.setName("zhaokun11");

        rabbitTemplate.convertAndSend("exchange_direct_p","route_key_q",m);
    }
}
