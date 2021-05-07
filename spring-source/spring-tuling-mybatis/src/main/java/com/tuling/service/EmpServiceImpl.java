package com.tuling.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuling.entity.Employee;
import com.tuling.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;


/**
 * Created by smlz on 2019/8/23.
 */
@Service
public class EmpServiceImpl  {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Transactional
	public void employeeList() {
		employeeMapper.list();
		employeeMapper.list();
	}

	public void list() {
		employeeMapper.list();
		employeeMapper.list();
	}

	public List<Employee> listByPage(Integer pageNum, Integer pageSize){
		PageHelper.startPage(pageNum,pageSize);
		List<Employee> employeeList=employeeMapper.list();
		PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
		return pageInfo.getList();
	}
}
