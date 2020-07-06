package com.example.term.controller;

import com.example.term.entity.RectificationEntity;
import com.example.term.form.RectificationForm;
import com.example.term.service.IRectificationService;
import com.example.term.utils.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class RectificationController {
    @Resource
    private IRectificationService service;

    @GetMapping("/rectification/{id}")
    public Result getInfo(@PathVariable int id){
        return Result.success(service.getInfo(id));
    }

    @GetMapping("/rectification/did/{did}")
    public Result getInfoByDid(@PathVariable int did){
        RectificationEntity entity = service.getInfoByDid(did);
        if (entity == null)
            return Result.errorMsg("不存在这条整改措施");
        return Result.success(entity);
    }

    @PostMapping("/rectification")
    public Result create(@RequestBody RectificationForm form){
        return Result.success(service.createRectification(form));
    }

    @PostMapping("/rectification/document")
    public Result updateDocument(@RequestParam("document") MultipartFile files){

        return Result.success(null);
    }

}

