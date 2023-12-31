package com.carbon.sys.controller;

import com.carbon.common.vo.Result;
import com.carbon.sys.entity.User;
import com.carbon.sys.entity.Warning;
import com.carbon.sys.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2023-08-10
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/all")
    public Result<List<User>> getAllData(){
        List<User> list = userService.list();
        return Result.success(list,"查询成功");
    }

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
       Map<String,Object>  data = userService.login(user);
        if(data !=null){
            return  Result.success(data);
        }
        return  Result.fail(20002,"用户名或密码错误");
    }

    @GetMapping("/info")
    public Result<Map<String,Object>> getUserInfo(@RequestParam("token")String token){
        // 根据token获取用户信息，redis
        Map<String,Object> data = userService.getUserInfo(token);
        if(data !=null){
            return  Result.success(data);
        }
        return  Result.fail(20003,"登录失效");
    }

    @PostMapping("logout")
    public Result<?> logout(@RequestHeader("X-Token")String token){
        userService.logout(token);
        return  Result.success();
    }
}
