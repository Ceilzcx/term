package com.example.term.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import lombok.Data;

import java.io.Serializable;

@TableName("danger")
@Data
public class DangerEntity implements Serializable {

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    private Integer type;

    @TableField(value = "risk_source")
    private String riskSource;

    private Integer level;

    private String description;

    private String measure;

    @TableField(value = "time_limit")
    private Integer timeLimit;

    private Integer uid;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    private Integer pid1;

    private Integer pid2;

    private Integer pid3;
    @TableField(value = "danger_status")
    private Integer dangerStatus;

}
