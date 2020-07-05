package com.example.term.enums;

public enum DangerStatus {
    create(0, "创建"),
    finish(1, "完成整控"),
    controlled(2, "已整控"),
    qualified(3, "合格"),
    unqualified(4, "不合格");

    public int key;
    public String value;

    DangerStatus(int key, String value){
        this.key = key;
        this.value = value;
    }

    public static String getValue(int key){
        DangerStatus[] statuses = values();
        for (DangerStatus status : statuses) {
            if (status.key == key)
                return status.value;
        }
        return null;
    }

    public static int getKey(String value){
        DangerStatus[] statuses = values();
        for (DangerStatus status : statuses) {
            if (status.value.equals(value))
                return status.key;
        }
        return -1;
    }

}
