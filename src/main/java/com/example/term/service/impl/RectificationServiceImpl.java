package com.example.term.service.impl;

import com.example.term.entity.DangerEntity;
import com.example.term.entity.PhotoEntity;
import com.example.term.entity.RectificationEntity;
import com.example.term.enums.DangerStatus;
import com.example.term.form.RectificationForm;
import com.example.term.mapper.DangerMapper;
import com.example.term.mapper.PhotoMapper;
import com.example.term.mapper.RectificationMapper;
import com.example.term.service.IRectificationService;
import com.example.term.utils.UploadBean;
import com.example.term.vo.RectificationPhotoVo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    @Resource
    private DangerMapper dangerMapper;
    @Resource
    private UploadBean bean;

    @Override
    public RectificationEntity getInfo(int id) {
        return setStatusProperty(rectificationMapper.selectById(id));
    }

    @Override
    public RectificationEntity getInfoByDid(int did) {
        Map<String, Object> selectMap = new HashMap<>();
        selectMap.put("did", did);
        List<RectificationEntity> entities = rectificationMapper.selectByMap(selectMap);
        if (entities.size() == 0)
            return null;
        return setStatusProperty(entities.get(0));
    }

    @Override
    @Transactional
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
        entity.setStatus(DangerStatus.getKey(form.getStatus()));
        if (photoEntityList.size() > 0)
            entity.setPid1(photoEntityList.get(0).getId());
        if (photoEntityList.size() > 1)
            entity.setPid2(photoEntityList.get(1).getId());
        if (photoEntityList.size() > 2)
            entity.setPid3(photoEntityList.get(2).getId());
        rectificationMapper.insert(entity);
        DangerEntity dangerEntity = dangerMapper.selectById(form.getDid());
        dangerEntity.setDangerStatus(entity.getStatus());
        dangerMapper.updateById(dangerEntity);
        return new RectificationPhotoVo(setStatusProperty(entity), photoEntityList);
    }

    @Override
    public RectificationEntity updateDocument(int id, MultipartFile file) {
        RectificationEntity entity = rectificationMapper.selectById(id);
        if (entity == null)
            throw new RuntimeException("整改数据不存在");
        LocalDateTime now = LocalDateTime.now();
        String docPath = "rectification/doc-" + entity.getUid() + "-" + now.toInstant(ZoneOffset.of("+8")).toEpochMilli() + ".png";
        String finalDocPath = bean.getRootPath() + docPath;

        FileOutputStream fos = null;
        InputStream fis = null;
        try {
            File docFile = new File(finalDocPath);
            if (docFile.getParentFile() != null)
                docFile.getParentFile().mkdirs();
            fos = new FileOutputStream(docFile);
            fis = file.getInputStream();
            IOUtils.copy(fis, fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        entity.setDocument(bean.getUrlPath() + docPath);
        entity.setCreateDate(now);
        rectificationMapper.updateById(entity);
        return setStatusProperty(entity);
    }

    private RectificationEntity setStatusProperty(RectificationEntity entity){
        entity.setStatus(DangerStatus.getValue(entity.getStatus()));
        return entity;
    }

}
