package com.example.term.enums;

public enum DangerType {
    mechanicalSafety(1, "机械安全"),
    fireworks(2, "烟花爆竹"),
    metallurgy(3, "冶金类"),
    hazardousChemicals(4, "危险化学品管理");

    int key;
    String value;

    DangerType(int key, String value){
        this.key = key;
        this.value = value;
    }

    public static String getValue(int key){
        DangerType[] levels = values();
        for (DangerType level : levels) {
            if (level.key == key)
                return level.value;
        }
        return null;
    }

    public static int getKey(String value){
        DangerType[] levels = values();
        for (DangerType level : levels) {
            if (level.value.equals(value))
                return level.key;
        }
        return -1;
    }

}
