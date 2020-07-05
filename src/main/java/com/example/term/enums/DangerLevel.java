package com.example.term.enums;

public enum DangerLevel {
    keynote(1, "重点"),
    more(2, "较大"),
    ordinary(3, "一般");

    public int key;
    public String value;

    DangerLevel(int key, String value){
        this.key = key;
        this.value = value;
    }

    public static String getValue(int key){
        DangerLevel[] levels = values();
        for (DangerLevel level: levels) {
            if (level.key == key)
                return level.value;
        }
        return null;
    }

    public static int getKey(String value){
        DangerLevel[] levels = values();
        for (DangerLevel level: levels) {
            if (level.value.equals(value))
                return level.key;
        }
        return -1;
    }
}
