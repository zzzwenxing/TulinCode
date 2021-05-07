package com.tuling;

import com.tuling.multidatasource.core.ITulingRouting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TulingOpenMultidatasourceApplicationTests {


	@Autowired
	private DataSource dataSource;

	@Autowired
	private ITulingRouting tulingRouting;

	@Test
	public void contextLoads() {
		System.out.println(tulingRouting.calDataSourceKey("123456"));
	}



}
