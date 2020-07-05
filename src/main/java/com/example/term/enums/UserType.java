package com.example.term.enums;

public enum UserType {
    // 需要token认证,但不需要type认证
    Common(1, "普通用户"),
    SafetyController(2, "安管员"),
    SafetyManagement(3, "安管负责人");

    public int key;
    public String value;

    UserType(int key, String value){
        this.key = key;
        this.value = value;
    }

    public static String getValue(int key){
        UserType[] types = values();
        for (UserType type : types) {
            if (type.key == key)
                return type.value;
        }
        return null;
    }

    public static int getKey(String value){
        UserType[] types = values();
        for (UserType type : types) {
            if (type.value.equals(value))
                return type.key;
        }
        return -1;
    }
}
