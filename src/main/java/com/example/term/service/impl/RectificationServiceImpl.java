package com.example.term.service.impl;

import com.example.term.entity.PhotoEntity;
import com.example.term.entity.RectificationEntity;
import com.example.term.enums.DangerStatus;
import com.example.term.form.RectificationForm;
import com.example.term.mapper.PhotoMapper;
import com.example.term.mapper.RectificationMapper;
import com.example.term.service.IRectificationService;
import com.example.term.vo.RectificationPhotoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RectificationServiceImpl implements IRectificationService {
    @Resource
    private RectificationMapper rectificationMapper;
    @Resource
    private PhotoMapper photoMapper;

    @Override
    public RectificationEntity getInfo(int id) {
        return rectificationMapper.selectById(id);
    }

    @Override
    public RectificationEntity getInfoByDid(int did) {
        Map<String, Object> selectMap = new HashMap<>();
        selectMap.put("did", did);
        List<RectificationEntity> entities = rectificationMapper.selectByMap(selectMap);
        if (entities.size() == 0)
            return null;
        return entities.get(0);
    }

    @Override
    public RectificationPhotoVo createRectification(RectificationForm form) {
        List<String> positions = form.getPosition();
        List<PhotoEntity> photoEntityList = new ArrayList<>();
        if (positions.size() > 0){
            for (String position : positions) {
                PhotoEntity entity = new PhotoEntity();
                entity.setPosition(position);
                entity.setUid(form.getUid());
                photoMapper.insert(entity);
                photoEntityList.add(entity);
            }
        }
        RectificationEntity entity = new RectificationEntity();
        BeanUtils.copyProperties(form, entity);
        entity.setType(DangerStatus.getKey(form.getType()));
        if (photoEntityList.size() > 0)
            entity.setPid1(photoEntityList.get(0).getId());
        if (photoEntityList.size() > 1)
            entity.setPid2(photoEntityList.get(1).getId());
        if (photoEntityList.size() > 2)
            entity.setPid3(photoEntityList.get(2).getId());
        rectificationMapper.insert(entity);
        return new RectificationPhotoVo(entity, photoEntityList);
    }

    @Override
    public RectificationEntity updateDocument(int id, MultipartFile file) {
        return null;
    }

}
