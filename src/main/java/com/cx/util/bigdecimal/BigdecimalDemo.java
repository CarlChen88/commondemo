package com.cx.util.bigdecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BigdecimalDemo {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        BigDecimal math1 = BigDecimal.valueOf(23.57);
        BigDecimal math2 = BigDecimal.valueOf(3.2332);
        BigDecimal result1 = math1.multiply(math2).setScale(1,RoundingMode.HALF_UP);
        System.out.println("result1:"+result1.toPlainString());
        BigDecimal result2 = math1.divide(math2,3, RoundingMode.HALF_UP);
        System.out.println("result2:"+result2.toPlainString());
        BigDecimal result3 = math1.add(math2);
        System.out.println("result3:"+result3.toPlainString());
        BigDecimal result4 = math1.subtract(math2);
        System.out.println("result4:"+result4.toPlainString());
        List<BigDecimal> list = new ArrayList<>();
        list.add(math1);
        list.add(math2);
        list.add(result1);
        BigDecimal result5 = list.stream().reduce(BigDecimal.ONE, BigDecimal::add);
        System.out.println("result5:"+result5.toPlainString());
        BigDecimal result6 = math1.setScale(1,6);
        System.out.println("result6:"+result6);
        int num = math1.compareTo(BigDecimal.valueOf(23.58));
        System.out.println("num:"+num);
        System.out.println(math1.multiply(math2).toPlainString());
    }
}
