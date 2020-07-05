package com.example.term.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.term.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;

public class TokenUtil {

    public static String generateToken(UserEntity entity){
        return JWT.create().withAudience(entity.getId().toString()).sign(Algorithm.HMAC256(entity.getPassword()));
    }

    public static int analysisToken(HttpServletRequest request){
        String token  = request.getHeader("token");
        String id = JWT.decode(token).getAudience().get(0);
        return Integer.parseInt(id);
    }

}
