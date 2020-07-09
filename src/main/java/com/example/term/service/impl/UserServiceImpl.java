package com.example.term.service.impl;

import com.example.term.entity.UserEntity;
import com.example.term.form.LoginForm;
import com.example.term.mapper.UserMapper;
import com.example.term.service.IUserService;
import com.example.term.utils.LoginToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity isUserNameExist(String username) {
        Map<String, Object> selectMap = new HashMap<>();
        selectMap.put("username", username);
        List<UserEntity> userEntities = userMapper.selectByMap(selectMap);
        if (userEntities.size() == 0)
            return null;
        return userEntities.get(0);
    }

    @Override
    @LoginToken(type = 1)
    public UserEntity getInfo(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public UserEntity login(LoginForm loginForm) {
        Map<String, Object> selectMap = new HashMap<>();
        selectMap.put("username", loginForm.getUsername());
        selectMap.put("password", loginForm.getPassword());
        List<UserEntity> entities = userMapper.selectByMap(selectMap);

        if (entities.size() == 0)
            return null;
        return entities.get(0);
    }

}
