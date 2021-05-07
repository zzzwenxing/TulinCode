package com.tuling.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * Created by smlz on 2019/6/17.
 */
@Repository
public class AccountInfoDao {

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

    public double qryBlanceByUserId(String accountId) {
        String sql = "select blance from account_info where account_id="+accountId;
        return jdbcTemplate.queryForObject(sql, Double.class);
    }
}
