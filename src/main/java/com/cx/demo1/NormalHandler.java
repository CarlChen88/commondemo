package com.cx.demo1;

import org.apache.ibatis.type.MappedTypes;

@MappedTypes(value = Gender.class)
public class NormalHandler<E extends BaseEnum> extends NormalEnumHandler<E> {
    public NormalHandler(Class<E> enumType) {
        super(enumType);
    }
}
