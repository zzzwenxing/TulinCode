package com.tuling.controller;

import com.tuling.initbinder.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试@ModelAttribute的作用
 * Created by smlz on 2019/8/12.
 */
@RestController
public class TulingTestModelAttributeController {

	private static Map<Integer,User> userMap;

	static {
		userMap = new HashMap<>();
		userMap.put(1,new User(1,"zhangsan",17,new Date()));
		userMap.put(2,new User(2,"lisi",18,new Date()));
	}

	/**
	 * 方法实现说明:修改数据库操作，要求年龄不能改
	 * @author:smlz
	 * @param user 前端传过来的user对象(由于年龄不能改,那么前端不会传入年龄过来)
	 * @return:
	 * @exception:
	 * @date:2019/8/12 15:40
	 */
	@RequestMapping("/updateUser")
	public User updateUser(User user) {
		//模拟修改数据库
		userMap.put(user.getId(),user);
		//返回修改后的 那么可能会把数据库中的年龄更新为空
		return userMap.get(user.getId());
	}

	/**
	 * 运行流程:
	 * 1:在执行目标方法之前，springmvc会执行标注了@ModelAttribute注解的方法
	 * 2:在@modelAttribute标注的方法上 模拟去数据库中模拟获取数组，然存放到map中
	 * map的类型是key 是我们请求访问的目标controller方法入参类型的首字母小写.
	 * 3:springmvc 目标方法的入参中的User对象貌似是从我们的Map中获取出来的.
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public void getUser(@RequestParam("id")Integer id,Map<String,User> map) {
		//id不为空,表示修改操作
		if(id!=null) {
			//模拟从数据库获取一个对象
			User user = userMap.get(id);
			map.put("user",user);
		}
	}
}
