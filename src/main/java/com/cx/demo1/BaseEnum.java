package com.cx.demo1;

public interface BaseEnum<E extends Enum<?>, T> {
    T getCode(); //存入数据库的值
    String getInfo(); //字面意义
}
