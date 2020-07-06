package com.example.term.controller;

import com.example.term.entity.PhotoEntity;
import com.example.term.service.IPhotoService;
import com.example.term.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class PhotoController {
    @Resource
    private IPhotoService photoService;

    @GetMapping("/photo/{id}")
    public Result getInfo(@PathVariable int id){
        return Result.success(photoService.getInfo(id));
    }

    @PostMapping("photo/upload/{id}")
    public Result uploadPhoto(@PathVariable int id, @RequestParam("photo") MultipartFile file){
        PhotoEntity entity = photoService.uploadPhoto(id, file);
        return Result.success(entity);
    }

}

