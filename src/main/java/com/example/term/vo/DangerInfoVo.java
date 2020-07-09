package com.example.term.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DangerInfoVo {
    Integer id;
    String type;
    String riskSource;
    String level;
    String description;
    String measure;
    Integer timeLimit;
    Integer uid;
    LocalDateTime createDate;
    Integer pid1;
    Integer pid2;
    Integer pid3;
    String dangerStatus;
}
