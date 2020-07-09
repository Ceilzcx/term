package com.example.term.controller;

import com.example.term.service.IEnterpriseService;
import com.example.term.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EnterpriseController {
    @Resource
    private IEnterpriseService service;

    @GetMapping("/enterprise/{id}")
    public Result getInfo(@PathVariable int id){
        return Result.success(service.getInfo(id));
    }

}

