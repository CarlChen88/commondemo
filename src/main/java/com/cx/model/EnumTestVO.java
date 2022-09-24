package com.cx.model;

import lombok.Data;

@Data
public class EnumTestVO {
    private Long id;
    private String code;
    private String name;
    private UserType userType;
}
