package com.tuling.dynamicproxy.anno;

import org.springframework.stereotype.Component;

/**
 * Created by smlz on 2020/3/31.
 */
@Component

public class UserInfoServiceImpl implements IUserInfoService {

	private boolean userFlag =true;

	public boolean isUserFlag() {
		return userFlag;
	}

	public void setUserFlag(boolean userFlag) {
		this.userFlag = userFlag;
	}

	@AngleProxy
	@Override
	public void queryUserInfo(Integer userId) {
		System.out.println("queryUserInfo   userId....."+userId);
	}
}
