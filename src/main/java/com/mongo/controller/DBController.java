package com.mongo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/db")
@RestController
@Slf4j
public class DBController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/testDb1")
    public Map<String,Object> testDb1() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM user");

        return list.get(0);
    }
}
