package com.tuling.compent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 测试tulingDao
 * Created by smlz on 2019/7/7.
 */
@Component
public class TulingDao {

	@Autowired
	private TulingDataSource tulingDataSource;

	public TulingDataSource getTulingDataSource() {
		return tulingDataSource;
	}

	public void setTulingDataSource(TulingDataSource tulingDataSource) {
		this.tulingDataSource = tulingDataSource;
	}

	public TulingDao(TulingDataSource tulingDataSource) {
		this.tulingDataSource = tulingDataSource;
		System.out.println("我是TulingDao的构造方法");
	}

	public TulingDao() {
		System.out.println("我是TulingDao的构造方法");
	}

}
