package com.example.term.service;

import com.example.term.entity.RectificationEntity;
import com.example.term.form.RectificationForm;
import com.example.term.vo.RectificationPhotoVo;
import org.springframework.web.multipart.MultipartFile;

public interface IRectificationService {

    RectificationEntity getInfo(int id);

    RectificationEntity getInfoByDid(int did);

    RectificationPhotoVo createRectification(RectificationForm form);

    RectificationEntity updateDocument(int id, MultipartFile file);

}
