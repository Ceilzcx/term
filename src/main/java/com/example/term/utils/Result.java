package com.example.term.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * JSON返回的对象封装
 */
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 2627220709786748205L;
    private Integer status;
    private String msg;
    private Object data;

    private Result(Integer status, String msg, Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private Result(Object data){
        this.status = 200;
        this.msg = "success";
        this.data = data;
    }

    public static Result build(Integer status, String msg, Object data){
        return new Result(status, msg, data);
    }

    public static Result errorMsg(String msg){
        return new Result(500, msg, null);
    }

    public static Result success(Object data){
        return new Result(data);
    }

    public static Result errorMap(Map<String, String> errorMap){
        return new Result(502, "参数格式错误", errorMap);
    }

}
