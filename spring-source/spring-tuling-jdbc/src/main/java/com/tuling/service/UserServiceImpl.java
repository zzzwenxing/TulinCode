package com.tuling.service;

import com.tuling.dao.AccountInfoDao;
import com.tuling.dao.UserInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by smlz on 2019/8/18.
 */
@Service
public class UserServiceImpl {

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private AccountInfoDao accountInfoDao;


	public void updateBlance(Integer userId) {
		accountInfoDao.updateAccountBlance("9988776644",1);
		accountInfoDao.updateAccountBlance("9988776655",1);
	}

	public void findUserName(Integer userId) {
		System.out.println(userInfoDao.findUserName(userId));
	}
}
