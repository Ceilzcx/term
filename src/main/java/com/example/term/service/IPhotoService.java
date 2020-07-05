package com.example.term.service;

import com.example.term.entity.PhotoEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IPhotoService{

    public PhotoEntity getInfo(int id);

    // android发送图片二进制流,保存到tomcat的映射图片文件夹下
    public PhotoEntity uploadPhoto(int id, MultipartFile file);

}
