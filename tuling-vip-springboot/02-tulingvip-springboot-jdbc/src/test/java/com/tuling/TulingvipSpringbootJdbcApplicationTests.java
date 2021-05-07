package com.tuling;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TulingvipSpringbootJdbcApplicationTests {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testDbType() throws SQLException {
		System.out.println("自动装配数据源的类型:"+dataSource.getClass());
	}

	@Test
	public void testJdbcTemplate() {
		List<Map<String,Object>> employeeList = jdbcTemplate.queryForList("select * from employee");

		System.out.println(employeeList.size());
	}

}
