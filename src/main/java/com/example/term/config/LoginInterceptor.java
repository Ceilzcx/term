package com.example.term.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.term.entity.UserEntity;
import com.example.term.mapper.UserMapper;
import com.example.term.utils.LoginToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private UserMapper userMapper;

    /**
     * 可以选择自定义的异常，赞数使用RuntimeException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod))
            return true;
        Method method = ((HandlerMethod)handler).getMethod();
        if (method.isAnnotationPresent(LoginToken.class)){
            LoginToken loginToken = method.getDeclaredAnnotation(LoginToken.class);
            if (loginToken.required()){
                String token = request.getHeader("token");
                if (StringUtils.isBlank(token))
                    throw new RuntimeException("token不存在");
                String uid = JWT.decode(token).getAudience().get(0);
                JWTVerifier verifier = null;
                UserEntity entity = userMapper.selectById(Integer.parseInt(uid));
                if (entity == null)
                    throw new RuntimeException("用户不存在");
                verifier = JWT.require(Algorithm.HMAC256(entity.getPassword())).build();
                //通过密码验证token是否正确
                if (verifier == null)
                    throw new RuntimeException("loginToken出错");
                try {
                    verifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("解析错误");
                }
            }
        }
        return true;
    }
}
