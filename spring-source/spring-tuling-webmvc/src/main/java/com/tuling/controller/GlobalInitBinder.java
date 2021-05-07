package com.tuling.controller;

/**
 * Created by smlz on 2019/8/12.
 */

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:全局的数据绑定器对象
* @author: smlz
* @createDate: 2019/8/12 14:34
* @version: 1.0
*/
@ControllerAdvice(annotations = {RestController.class})
public class GlobalInitBinder {

	@InitBinder
	public void globalInitWebBinder(WebDataBinder webDataBinder) {

		//控制入参哪个参数不赋值
		//webDataBinder.setDisallowedFields("age");

		//字段的请求前缀
		//webDataBinder.setFieldDefaultPrefix("angle.");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);

		webDataBinder.registerCustomEditor(Date.class,dateEditor);
	}
}
