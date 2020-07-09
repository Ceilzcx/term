package com.example.term.controller;


import com.example.term.form.DangerForm;
import com.example.term.service.IDangerService;
import com.example.term.utils.LoginToken;
import com.example.term.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class DangerController {
    @Resource
    private IDangerService dangerService;

    @GetMapping("/dangers/{uid}")
    public Result findDangersByUid(@PathVariable int uid){
        return Result.success(dangerService.getDangersByUid(uid));
    }

    @GetMapping("/dangers/keynote")
    @LoginToken(type = 3)
    public Result findKeynoteDangers(){
        return Result.success(dangerService.getKeynoteDangers());
    }

    @GetMapping("/dangers/others")
    @LoginToken(type = 2)
    public Result findNotKeynoteDangers(){
        return Result.success(dangerService.getNotKeynoteDangers());
    }

    @GetMapping("/dangers/rectification")
    @LoginToken(type = 4)
    public Result findRectification() {
        return Result.success(dangerService.getRectDangers());
    }

    @GetMapping("/danger/{id}")
    public Result getInfo(@PathVariable int id){
        return Result.success(dangerService.getInfo(id));
    }

    @PostMapping("/danger")
    public Result createDanger(@RequestBody DangerForm dangerForm){
        return Result.success(dangerService.createDanger(dangerForm));
    }

}

