package com.tuling.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * Created by smlz on 2019/6/17.
 */
@Repository
public class AccountInfoDao {

	/**
	 * 我们主要分析的式jdbcTempalte.update()
	 *              jdbcTemplate.query();
	 *              jdbcTemplate.queryForObject()
	 */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateAccountBlance(String accountId,double blance) {
        String sql = "update account_info set blance=blance-? where account_id=?";
        return jdbcTemplate.update(sql,blance,accountId);
    }

    public int saveAccountInfo(String accountId,double blance) {
        String sql = "insert into account_info(account_id,blance) values(?,?)";
        return jdbcTemplate.update(sql,accountId,blance);
    }

    public double qryBlanceByUserId(Integer userId) {
        String sql = "select blance from account_info where id= ? ";
        return jdbcTemplate.queryForObject(sql,new Object[]{userId}, Double.class);
    }
}
