package com.tuling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by smlz on 2019/3/22.
 */
@RestController
public class TestTulingController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/testJdbc")
    public List<Map<String,Object>> tulingHello() {
        List<Map<String,Object>> employeeList = jdbcTemplate.queryForList("select * from employee");
        return employeeList;
    }
}
