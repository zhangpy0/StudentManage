package com.zhangpy.studentmanage.controller;

import com.zhangpy.studentmanage.bean.User;
import com.zhangpy.studentmanage.service.UserService;
import com.zhangpy.studentmanage.util.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private UserService userService;

    @Operation(summary = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Map<String, String>> login(
            @Parameter(description = "用户名")
            @RequestParam(value = "username")
            String username,
            @Parameter(description = "密码")
            @RequestParam(value = "password")
            String password
    ) {
        logger.info("username:"+username+" password:"+password);
        User user = userService.getUserByNameAndPassword(username,password);
        if (user != null){
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            long id = user.getId();
            redisTemplate.opsForValue().set(String.valueOf(id), uuid, 3600, TimeUnit.SECONDS);
            Map<String, String> data = Map.of("id", String.valueOf(id), "token", uuid);
            return Result.ok(data,"登录成功");
        }else {
            return Result.fail(null,"登录失败");
        }
    }

    @Operation(summary = "登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout(
            @Parameter(description = "用户id")
            @RequestParam(value = "id")
            long id
    ) {
        redisTemplate.delete(String.valueOf(id));
        return Result.ok(null,"登出成功");
    }

    @Operation(summary = "检查登录状态")
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public Result checkLogin(
            @Parameter(description = "用户id")
            @RequestParam(value = "id")
            long id,
            @Parameter(description = "token")
            @RequestParam(value = "token")
            String token
    ) {
        String redisToken = redisTemplate.opsForValue().get(String.valueOf(id));
        if (token.equals(redisToken)){
            return Result.ok(null,"登录状态正常");
        }else {
            return Result.fail(403,"登录状态异常",null);
        }
    }

}
