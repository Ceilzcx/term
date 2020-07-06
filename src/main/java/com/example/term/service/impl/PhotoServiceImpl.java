package com.example.term.service.impl;

import com.example.term.entity.PhotoEntity;
import com.example.term.mapper.PhotoMapper;
import com.example.term.service.IPhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
public class PhotoServiceImpl implements IPhotoService {
    @Resource
    private PhotoMapper photoMapper;

    @Override
    public PhotoEntity getInfo(int id) {
        return photoMapper.selectById(id);
    }

    @Override
    public PhotoEntity uploadPhoto(int id, MultipartFile file) {
        return null;
    }
}
