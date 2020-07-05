package com.example.term.service;

import com.example.term.entity.UserEntity;

public interface IUserService {

    UserEntity isUserNameExist(String username);

}
