package com.example.term.service.impl;

import com.example.term.entity.PhotoEntity;
import com.example.term.service.IPhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoServiceImpl implements IPhotoService {

    @Override
    public PhotoEntity getInfo(int id) {
        return null;
    }

    @Override
    public PhotoEntity uploadPhoto(int id, MultipartFile file) {
        return null;
    }
}
