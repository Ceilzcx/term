package com.example.term.form;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RectificationForm {
    String type;
    String measure;
    Integer uid;
    Integer did;
    List<String> position;
}
