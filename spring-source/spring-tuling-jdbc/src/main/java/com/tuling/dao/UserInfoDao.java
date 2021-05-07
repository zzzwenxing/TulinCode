package com.tuling.dao;

import com.tuling.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by smlz on 2019/8/18.
 */
@Repository
public class UserInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserInfo findOne(Integer userId ) {
		String sql = "select * from user_info where id=?";
		return jdbcTemplate.queryForObject(sql,new Object[]{userId},((rs, rowNum) -> {
			UserInfo userInfo = new UserInfo();
			userInfo.setId(rs.getInt("id"));
			userInfo.setUserName(rs.getString("user_name"));
			userInfo.setPassword(rs.getString("password"));
			return userInfo;
		}));
	}

	public String findUserName(Integer userId) {
		String sql = "select user_name from user_info where id=?";
		return jdbcTemplate.queryForObject(sql,new Object[]{userId},String.class);
	}
}
