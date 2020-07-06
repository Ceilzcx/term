package com.example.term.controller;

import com.example.term.service.IPhotoService;
import com.example.term.utils.Result;
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

    @PostMapping("photo/upload")
    public Result uploadPhoto(@RequestParam int id, @RequestParam("photo") MultipartFile file){

        return Result.success(null);
    }

}

