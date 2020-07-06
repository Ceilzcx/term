package com.example.term.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import lombok.Data;

import java.io.Serializable;

@TableName("photo")
@Data
public class PhotoEntity implements Serializable {
    private static final long serialVersionUID = -1304485467016277863L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String url;
//    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    private Integer uid;

    private String position;

}
