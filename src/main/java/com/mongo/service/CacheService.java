package com.mongo.service;

import com.mongo.bean.CacheStudent;
import com.mongo.controller.CacheController;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    public CacheStudent testCacheable() {

        CacheStudent student = new CacheStudent();
        student.setId(1);
        student.setName("zhaokun");

        return student;
    }
}
