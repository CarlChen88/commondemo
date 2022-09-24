package com.cx.model;

public class Test {
    public static void main(String[] args) {
       BaseResponse result = BaseResponse.builder().code("1").message("1111").build();
        System.out.println(result);
    }
}
