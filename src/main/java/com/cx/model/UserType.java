package com.cx.model;

public enum  UserType implements EnumDb{
    STUDENT(1,"学生"),TEACHER(2,"老师");
    private Integer code;
    private String name;

    UserType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserType getUserType(Integer code){
        if(code == null) {
            return null;
        }
        UserType[] userTypes = UserType.values();
        for(UserType userType : userTypes) {
            if (userType.getCode()==code){
                return userType;
            }
        }
        return null;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
