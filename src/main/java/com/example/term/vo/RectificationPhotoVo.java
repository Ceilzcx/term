package com.example.term.vo;

import com.example.term.entity.PhotoEntity;
import com.example.term.entity.RectificationEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RectificationPhotoVo {
    RectificationEntity entity;
    List<PhotoEntity> photoEntityList;
}
