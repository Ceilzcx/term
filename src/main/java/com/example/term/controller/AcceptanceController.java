package com.example.term.controller;


import com.example.term.entity.AcceptanceEntity;
import com.example.term.form.AcceptanceForm;
import com.example.term.service.IAcceptanceService;
import com.example.term.utils.LoginToken;
import com.example.term.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class AcceptanceController {
    @Resource
    private IAcceptanceService acceptanceService;

    @GetMapping("/acceptance/{id}")
    public Result getInfo(@PathVariable int id){
        return Result.success(acceptanceService.getInfo(id));
    }

    @GetMapping("/acceptance/rid/{rid}")
    public Result getInfoByDid(@PathVariable int rid){
        AcceptanceEntity entity = acceptanceService.getInfoByRid(rid);
        if (entity == null)
            return Result.errorMsg("不存在验证信息");
        return Result.success(entity);
    }

    @PostMapping("/acceptance")
    public Result create(@RequestBody AcceptanceForm form){
        return Result.success(acceptanceService.createAcceptance(form));
    }

}

