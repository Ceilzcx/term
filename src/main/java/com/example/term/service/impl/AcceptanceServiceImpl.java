package com.example.term.service.impl;

import com.example.term.entity.AcceptanceEntity;
import com.example.term.mapper.AcceptanceMapper;
import com.example.term.service.IAcceptanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AcceptanceServiceImpl implements IAcceptanceService {
    @Resource
    private AcceptanceMapper acceptanceMapper;

    @Override
    public AcceptanceEntity getInfo(int id) {
        return acceptanceMapper.selectById(id);
    }
}
