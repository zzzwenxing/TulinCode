package com.tuling.controller;

import com.tuling.bean.Car;
import com.tuling.bean.DataNode;
import com.tuling.bean.User;
import com.tuling.bean.UserCondition;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smlz on 2019/3/24.
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户服务",description = "用户的基本操作")
public class UserController {

    @ApiOperation(value = "用户列表服务",notes = "查詢所有用戶的列表信息")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> list() {
        List<User> userList = new ArrayList<>();
        for(String key: DataNode.users.keySet()) {
            userList.add(DataNode.users.get(key));
        }
        return userList;
    }

    @ApiOperation(value ="根据用户ID查询用户信息",notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name="userId",value = "用户ID",required = true,dataType ="Integer",paramType = "path")
    @RequestMapping(value = "/findOneById/{userId}",method = RequestMethod.GET)
    public User findOneById(@PathVariable("userId") Integer userId) {
        for(String key: DataNode.users.keySet()) {
            User user = DataNode.users.get(key);
            if(user.getUserId() == userId) {
                return user;
            }
        }

        return null;
    }

    @ApiOperation(value = "根据用户名获取用户信息")
    @RequestMapping(value = "/findOneUserName/{userName}",method = RequestMethod.GET)
    @ApiImplicitParam(name = "userName",value = "用户名",required = true,dataType = "String",paramType = "path")
    public User findOneByName( @PathVariable("userName") String userName) {
        for(String key: DataNode.users.keySet()) {
            User user = DataNode.users.get(key);
            if(user.getUserName().equals(userName)) {
                return user;
            }
        }

        return null;
    }

    @ApiOperation(value = "根据用户名获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "用户名称", required = true, dataType = "String", paramType = "query")
    }
    )
    @RequestMapping(value = "/findOneByIdAndName",method = RequestMethod.GET)
    public User findOneByIdAndName(@RequestParam String userName, @RequestParam Integer id) {
        for(String key: DataNode.users.keySet()) {
            User user = DataNode.users.get(key);
            if(user.getUserName().equals(userName) && id==user.getUserId()) {
                return user;
            }
        }

        return null;
    }

    @ApiOperation(value = "根据查询条件获取用户信息")
    @RequestMapping(value = "/findOneByCondition",method = RequestMethod.GET)
    public User findOneByCondition(UserCondition userCondition) {
        for(String key: DataNode.users.keySet()) {
            User user = DataNode.users.get(key);
            if(user.getUserName().equals(userCondition.getUserName()) &&
                    user.getUserId()==userCondition.getUserId()) {
                Car car = new Car();
                car.setName("奥迪");
                user.setCar(car);
                return user;
            }
        }
        return null;
    }
}