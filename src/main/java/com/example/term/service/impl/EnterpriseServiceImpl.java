package com.example.term.service.impl;


import com.example.term.entity.EnterpriseEntity;
import com.example.term.mapper.EnterpriseMapper;
import com.example.term.service.IEnterpriseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EnterpriseServiceImpl implements IEnterpriseService {
    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public EnterpriseEntity getInfo(int id) {
        return enterpriseMapper.selectById(id);
    }

}
