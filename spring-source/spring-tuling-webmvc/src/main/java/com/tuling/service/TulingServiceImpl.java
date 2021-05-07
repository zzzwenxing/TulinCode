package com.tuling.service;

import org.springframework.stereotype.Service;

/**
 * Created by smlz on 2019/7/31.
 */
@Service("tulingServiceImpl")
public class TulingServiceImpl  {

	public void sayHello() {
		System.out.println("TulingServiceImpl.....sayHello");
	}

	public TulingServiceImpl() {
		System.out.println("TulingServiceImpl");
	}

}
