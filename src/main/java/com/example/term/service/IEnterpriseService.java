package com.example.term.service;

import com.example.term.entity.EnterpriseEntity;

public interface IEnterpriseService{
    // 用户存在eid外键,前端如何获得id
    EnterpriseEntity getInfo(int id);

}
