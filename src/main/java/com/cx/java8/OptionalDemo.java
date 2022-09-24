package com.cx.java8;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Integer integer = Optional.of(21).filter(a -> a == 21).orElse(0);
        System.out.println(integer);
        Optional.of(3).orElseThrow(()->new RuntimeException());
    }
}
