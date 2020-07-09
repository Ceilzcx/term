package com.example.term.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.term.entity.DangerEntity;
import com.example.term.entity.PhotoEntity;
import com.example.term.enums.DangerLevel;
import com.example.term.enums.DangerStatus;
import com.example.term.enums.DangerType;
import com.example.term.form.DangerForm;
import com.example.term.mapper.DangerMapper;
import com.example.term.mapper.PhotoMapper;
import com.example.term.service.IDangerService;
import com.example.term.vo.DangerPhotoVo;
import com.example.term.vo.DangerVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DangerServiceImpl implements IDangerService {
    @Resource
    private DangerMapper dangerMapper;
    @Resource
    private PhotoMapper photoMapper;

    @Override
    @Transactional
    public DangerPhotoVo createDanger(DangerForm dangerForm) {
        List<String> positions = dangerForm.getPosition();
        List<PhotoEntity> photoEntityList = new ArrayList<>();
        if (positions.size() > 0){
            for (String position : positions) {
                PhotoEntity entity = new PhotoEntity();
                entity.setPosition(position);
                entity.setUid(dangerForm.getUid());
                photoMapper.insert(entity);
                photoEntityList.add(entity);
            }
        }
        DangerEntity entity = new DangerEntity();
        BeanUtils.copyProperties(dangerForm, entity);
        entity.setDangerStatus(DangerStatus.create.key);
        entity.setLevel(DangerLevel.getKey(dangerForm.getLevel()));
        entity.setType(DangerType.getKey(dangerForm.getType()));
        if (photoEntityList.size() > 0)
            entity.setPid1(photoEntityList.get(0).getId());
        if (photoEntityList.size() > 1)
            entity.setPid2(photoEntityList.get(1).getId());
        if (photoEntityList.size() > 2)
            entity.setPid3(photoEntityList.get(2).getId());
        dangerMapper.insert(entity);
        return new DangerPhotoVo(entity, photoEntityList);
    }

    @Override
    public List<DangerVo> getAllDanger(int eid) {
        List<DangerEntity> entities = dangerMapper.getDangerByEid(eid);
        List<DangerVo> result = new ArrayList<>();
        for (DangerEntity entity : entities) {
            result.add(changeToVo(entity));
        }
        return result;
    }

    @Override
    public List<DangerVo> getDangersByUid(int uid) {
        //  Wrappers.<DangerEntity>lambdaQuery().ne(DangerEntity::getType, DangerStatus.qualified.key);
        List<DangerEntity> entities = dangerMapper.selectList(
                new QueryWrapper<DangerEntity>()
                        .lambda()
                        .eq(DangerEntity::getUid, uid));
        List<DangerVo> result = new ArrayList<>();
        for (DangerEntity entity : entities) {
            result.add(changeToVo(entity));
        }
        return result;
    }

    @Override
    public List<DangerVo> getKeynoteDangers() {
        List<DangerEntity> entities = dangerMapper.selectList(
                new QueryWrapper<DangerEntity>()
                        .lambda()
                        .eq(DangerEntity::getDangerStatus, DangerStatus.create.key)
                        .eq(DangerEntity::getLevel, DangerLevel.keynote.key));
        List<DangerVo> result = new ArrayList<>();
        for (DangerEntity entity : entities) {
            result.add(changeToVo(entity));
        }
        return result;
    }

    @Override
    public List<DangerVo> getNotKeynoteDangers() {
        List<DangerEntity> entities = dangerMapper.selectList(
                new QueryWrapper<DangerEntity>()
                        .lambda()
                        .eq(DangerEntity::getDangerStatus, DangerStatus.create.key)
                        .ne(DangerEntity::getLevel, DangerLevel.keynote.key));
        List<DangerVo> result = new ArrayList<>();
        for (DangerEntity entity : entities) {
            result.add(changeToVo(entity));
        }
        return result;
    }

    @Override
    public List<DangerVo> getRectDangers() {
        Map<String, Object> selectMap = new HashMap<>();
        selectMap.put("danger_status", DangerStatus.finish.key);
        List<DangerEntity> entities = dangerMapper.selectByMap(selectMap);
        List<DangerVo> result = new ArrayList<>();
        for (DangerEntity entity : entities) {
            result.add(changeToVo(entity));
        }

        selectMap.remove("danger_status");
        selectMap.put("danger_status", DangerStatus.controlled.key);
        entities = dangerMapper.selectByMap(selectMap);
        for (DangerEntity entity : entities) {
            result.add(changeToVo(entity));
        }

        return result;
    }

    @Override
    public DangerEntity getInfo(int did) {
        return dangerMapper.selectById(did);
    }

    @Override
    public DangerEntity updateStatus(int did, String status) {
        return null;
    }

    private DangerVo changeToVo(DangerEntity entity){
        DangerVo dangerVo = new DangerVo();
        dangerVo.setId(entity.getId());
        dangerVo.setRiskSource(entity.getRiskSource());
        dangerVo.setStatus(DangerStatus.getValue(entity.getDangerStatus()));
        dangerVo.setTimeLevel(entity.getLevel());
        dangerVo.setType(DangerType.getValue(entity.getType()));
        return dangerVo;
    }

}
