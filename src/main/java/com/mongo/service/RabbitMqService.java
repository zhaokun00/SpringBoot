package com.mongo.service;

import com.mongo.bean.RabbitMqStudent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableRabbit
public class RabbitMqService {

//    @RabbitListener(queues = {"queue_mongo","route_key_q"})
//    public void reveive(RabbitMqStudent m) {
//
//        log.info(m.toString());
//    }

    @RabbitListener(queues = "queue_direct")
    public void reveive(RabbitMqStudent m) {

        log.info(m.toString());
    }
}
