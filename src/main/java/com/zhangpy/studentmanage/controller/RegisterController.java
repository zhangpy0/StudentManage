package com.zhangpy.studentmanage.controller;

import com.zhangpy.studentmanage.bean.User;
import com.zhangpy.studentmanage.service.UserService;
import com.zhangpy.studentmanage.util.Result;
import com.zhangpy.studentmanage.util.StringJudge;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    @Operation(summary = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(
            @Parameter(description = "用户名")
            @RequestParam(value = "username")
            String username,
            @Parameter(description = "密码")
            @RequestParam(value = "password")
            String password
    ) {
        logger.info("username:"+username+" password:"+password);
        if (!(StringJudge.isInRange(username) && StringJudge.isInRange(password))){
            return Result.fail(401,"用户名或密码不合法",null);
        }
        User user = userService.getUserByName(username);
        if (user != null){
            return Result.fail(402,"用户名已存在",null);
        }
        else {
            userService.addUser(username,password);
            return Result.ok(null,"注册成功");
        }
    }

}
