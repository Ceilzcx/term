package com.example.term.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DangerVo {
    Integer id;
    String riskSource;
    String status;
    String type;
    int timeLimit;
}