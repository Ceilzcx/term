package com.example.term.controller;


import com.example.term.service.IAcceptanceService;
import com.example.term.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
public class AcceptanceController {
    @Resource
    private IAcceptanceService acceptanceService;

    @GetMapping("/acceptance/{id}")
    public Result getInfo(@PathVariable int id){
        return Result.success(acceptanceService.getInfo(id));
    }

}

