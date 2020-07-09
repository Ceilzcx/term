package com.example.term.service;

import com.example.term.entity.UserEntity;
import com.example.term.form.LoginForm;

public interface IUserService {

    UserEntity isUserNameExist(String username);

    UserEntity getInfo(int id);

    UserEntity login(LoginForm loginForm);

}
