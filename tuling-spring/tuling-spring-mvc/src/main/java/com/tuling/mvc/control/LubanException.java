package com.tuling.mvc.control;/**
 * Created by Administrator on 2018/8/31.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/31
 **/
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "luban error")
public class LubanException extends Exception {
}
