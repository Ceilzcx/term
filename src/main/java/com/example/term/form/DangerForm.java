package com.example.term.form;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DangerForm {
    String type;
    String riskSource;
    String level;
    String description;
    String measure;
    int timeLimit;
    int uid;
    // TODO: 图片未处理,包括url; uid使用Danger的uid
    List<String> position;
}
