package com.tuling.controller;

import com.tuling.bean.Dept;
import com.tuling.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by smlz on 2019/3/22.
 */
@RestController
@RequestMapping("/dept")
public class DepartmentController {

    @Autowired
    private DeptMapper deptMapper;

    @RequestMapping("/findOne/{id}")
    public Dept findOne(@PathVariable("id") Integer id){
        return deptMapper.findOne(id);
    }

    @RequestMapping("/list")
    public List<Dept> list() {
        return deptMapper.list();
    }

    @RequestMapping("/save")
    public int save(Dept dept) {
        deptMapper.save(dept);
        return dept.getId();
    }
}
