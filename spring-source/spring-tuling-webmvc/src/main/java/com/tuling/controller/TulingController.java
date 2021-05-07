package com.tuling.controller;

import com.tuling.initbinder.User;
import com.tuling.service.TulingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/7/31.
 */
@RestController
public class TulingController {

	@Autowired
	private TulingServiceImpl tulingServiceImpl;

	@RequestMapping(value = {"/angle"})
	public String testTuling(HttpServletRequest httpServletRequest) {
		System.out.println("URL:"+httpServletRequest.getRequestURL());
		System.out.println("URI:"+httpServletRequest.getRequestURI());
		System.out.println("contextPath:"+httpServletRequest.getContextPath());
		System.out.println("serlvetPath:"+httpServletRequest.getServletPath());
		ServletContext servletContext = httpServletRequest.getServletContext();
		tulingServiceImpl.sayHello();
		return "smlz";
	}

	@RequestMapping(value = {"/tuling"})
	public String testAngle(HttpServletRequest httpServletRequest) {
		ServletContext servletContext = httpServletRequest.getServletContext();
		return "smlz";
	}

	@RequestMapping("/returnJson")
	public Object returnJson() {
		Map<String,String> retMap = new HashMap<>();
		retMap.put("name","张三");
		return retMap;
	}

	@RequestMapping("/testQuestPram")
	public String testRequestParam(@RequestParam("${name}") String name) {
		System.out.println("name="+name);
		return name;
	}

	public TulingController() {
		System.out.println("TulingController 执行构造方法");
	}


	@RequestMapping("/initbinder/user")
	public User getFormatData(User user) {
		System.out.println("user:"+user.toString());
		return user;
	}

	/**
	 * 作用于单个controller
	 * WebDataBinder 的作用
	 * @param webDataBinder
	 */
	@InitBinder
	public void initWebBinderDataFormatter(WebDataBinder webDataBinder) {
		//作用一:加入类型转化器
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);

		webDataBinder.registerCustomEditor(Date.class,dateEditor);
	}
}
