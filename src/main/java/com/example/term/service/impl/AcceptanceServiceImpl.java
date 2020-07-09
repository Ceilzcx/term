package com.example.term.service.impl;

import com.example.term.entity.AcceptanceEntity;
import com.example.term.entity.DangerEntity;
import com.example.term.enums.DangerStatus;
import com.example.term.form.AcceptanceForm;
import com.example.term.mapper.AcceptanceMapper;
import com.example.term.mapper.DangerMapper;
import com.example.term.service.IAcceptanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AcceptanceServiceImpl implements IAcceptanceService {
    @Resource
    private AcceptanceMapper acceptanceMapper;
    @Resource
    private DangerMapper dangerMapper;

    @Override
    public AcceptanceEntity getInfo(int id) {
        return setStateProperty(acceptanceMapper.selectById(id));
    }

    @Override
    public AcceptanceEntity getInfoByRid(int rid) {
        Map<String, Object> selectMap = new HashMap<>();
        selectMap.put("rid", rid);
        List<AcceptanceEntity> entities = acceptanceMapper.selectByMap(selectMap);
        if (entities.size() > 0) {
            return setStateProperty(entities.get(0));
        }
        return null;
    }

    @Override
    @Transactional
    public AcceptanceEntity createAcceptance(AcceptanceForm form) {
        AcceptanceEntity entity = new AcceptanceEntity();
        BeanUtils.copyProperties(form, entity);
        entity.setAcceptStatus(DangerStatus.getKey(form.getAcceptStatus()));
        acceptanceMapper.insert(entity);
        DangerEntity danger = dangerMapper.getDangerByAid(entity.getId());
        danger.setDangerStatus(entity.getAcceptStatus());
        dangerMapper.updateById(danger);
        return setStateProperty(entity);
    }

    private AcceptanceEntity setStateProperty(AcceptanceEntity entity){
        entity.setAcceptStatus(DangerStatus.getValue(entity.getAcceptStatus()));
        return entity;
    }

}
