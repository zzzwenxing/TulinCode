package com.tuling.service;/**
 * Created by Administrator on 2018/8/28.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/28
 **/
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addAccount(String name, int initMoney) {
        String accountid = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        jdbcTemplate.update("insert INTO account (accountName,user,money) VALUES (?,?,?)", accountid, name, initMoney);
    }

    @Override
    @Transactional
    public List<Account> queryAccount(String name) {
        List<Account> list = jdbcTemplate.queryForList("SELECT * from account where user=?", Account.class, name);
        Arrays.toString(list.toArray());
        return list;
    }

    @Override
    @Transactional
    public int updateAccount(String name, int money) {
        return jdbcTemplate.update("SELECT * from account set money=money+? where user=?", money, name);
    }

}
