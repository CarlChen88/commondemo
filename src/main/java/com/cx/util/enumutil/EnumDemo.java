package com.cx.util.enumutil;

/**
 * 枚举类的七种使用方式
 */
public class EnumDemo {
    public static void main(String[] args) {
        test3();
    }

    /**
     * 1.用作常量
     */
    public static void test1() {
        System.out.println(ColorEnum.RED);
    }

    /**
     * 2.在switch中使用
     */
    public static void test2() {
        ColorEnum colorEnum = ColorEnum.RED;
        switch (colorEnum) {
            case RED: {
                System.out.println("RED");
                break;
            }
            case GREEN: {
                System.out.println("GREEN");
                break;
            }
            case YELLOW: {
                System.out.println("YELLOW");
                break;
            }
        }
    }

    /**
     * 3.枚举中增加方法
     */
    public static void test3() {
        ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.SUCCESS;
        System.out.println("code:" + errorCodeEnum.getCode() + " message:" + errorCodeEnum.getMessage());
    }

}

//1.常量使用
enum ColorEnum {
    RED, GREEN, YELLOW;
}

enum ErrorCodeEnum {
    SUCCESS(1000, "success"),
    PARAM_ERROR(1001, "parameter error"),
    SYS_ERROR(1003, "system error"),
    NAMESPACE_NOT_FOUND(2001, "namespace not found"),
    NODE_NOT_EXIST(3002, "node not exist"),
    NODE_ALREADY_EXIST(3003, "node already exist"),
    UNKNOWN_ERROR(9999, "unknown error");
    private int code;
    private String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ErrorCodeEnum getErrorCode(int code) {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (errorCodeEnum.getCode() == code) {
                return errorCodeEnum;
            }
        }
        return UNKNOWN_ERROR;
    }
}