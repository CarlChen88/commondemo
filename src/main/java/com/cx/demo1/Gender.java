package com.cx.demo1;

import java.util.Arrays;

public enum Gender implements BaseEnum<Gender, Integer> {
    MALE(1,"男"),FEMALE(2,"女"),UNKNOWN(3,"未知");
    private Integer code;
    private String info;

    Gender(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public static Gender getGender(Integer code){
        if(code == null){
            return null;
        }
        return Arrays.stream(Gender.values()).filter(i->i.getCode() == code).findAny().orElse(null);
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getInfo() {
        return this.info;
    }
}
