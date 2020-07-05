package com.example.term.form;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AcceptanceForm {
    String acceptOption;
    Integer uid;
    int acceptStatus;
    int rid;
}
