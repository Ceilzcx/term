package com.example.term.controller;

import com.example.term.entity.UserEntity;
import com.example.term.enums.UserType;
import com.example.term.form.LoginForm;
import com.example.term.service.IUserService;
import com.example.term.utils.LoginToken;
import com.example.term.utils.Result;
import com.example.term.utils.TokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping("/user/login")
    public Result login(@RequestBody LoginForm loginForm){
        UserEntity entity = userService.isUserNameExist(loginForm.getUsername());
        if (entity == null)
            return Result.errorMsg("账号不存在");
        if (!entity.getPassword().equals(loginForm.getPassword()))
            return Result.errorMsg("密码输入错误");
        return Result.success(TokenUtil.generateToken(entity));
    }

    @GetMapping("/user")
    @LoginToken(type = 1)
    public Result getInfo(HttpServletRequest request){
        return Result.success(
                userService.getInfo(
                        TokenUtil.analysisToken(request)));
    }

}

