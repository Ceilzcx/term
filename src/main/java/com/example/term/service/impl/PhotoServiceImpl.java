package com.example.term.service.impl;

import com.example.term.entity.PhotoEntity;
import com.example.term.mapper.PhotoMapper;
import com.example.term.service.IPhotoService;
import com.example.term.utils.UploadBean;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class PhotoServiceImpl implements IPhotoService {
    @Resource
    private PhotoMapper photoMapper;
    @Resource
    private UploadBean bean;

    @Override
    public PhotoEntity getInfo(int id) {
        return photoMapper.selectById(id);
    }

    @Override
    public PhotoEntity uploadPhoto(int id, MultipartFile file) {
        PhotoEntity entity = photoMapper.selectById(id);
        if (entity == null)
            throw new RuntimeException("图片不存在");
        LocalDateTime now = LocalDateTime.now();
        String photoPath = "user/photo-" + entity.getUid() + "-" + now.toInstant(ZoneOffset.of("+8")).toEpochMilli() + ".png";
        String finalPhotoPath = bean.getRootPath() + photoPath;

        FileOutputStream fos = null;
        InputStream fis = null;
        try {
            File photoFile = new File(finalPhotoPath);
            if (photoFile.getParentFile() != null)
                photoFile.getParentFile().mkdirs();
            fos = new FileOutputStream(photoFile);
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
        entity.setUrl(bean.getUrlPath() + photoPath);
        entity.setCreateDate(now);
        photoMapper.updateById(entity);
        return entity;

    }
}
