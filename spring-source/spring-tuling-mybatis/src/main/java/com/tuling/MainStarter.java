package com.tuling;

import com.tuling.config.MyBatisConfig;
import com.tuling.entity.Dept;
import com.tuling.mapper.DeptMapper;
import com.tuling.mapper.EmployeeMapper;
import com.tuling.service.EmpServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * Created by smlz on 2019/8/20.
 */

public class MainStarter {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyBatisConfig.class);

		DeptMapper deptMapper = context.getBean(DeptMapper.class);

		deptMapper.findOne(1);

	}
}
