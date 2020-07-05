package com.example.term.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import lombok.Data;

import java.io.Serializable;

@TableName("rectification")
@Data
public class RectificationEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String condition;

    private String measure;

    private String document;

    private Integer uid;
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    private Integer pid1;

    private Integer pid2;

    private Integer pid3;

    private Integer did;

}
