package com.cx.java8;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * stream的reduce用法
 */
public class ReduceDemo {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        String str = Stream.of("A","B","C").reduce("chenxiang", String::concat);
        System.out.println(str);
        Integer sum = Stream.of(1,2,3,4).reduce(0,(a,b)->a+b);
        System.out.println(sum);

        List<BigDecimal> list = Lists.newArrayList();
        list.add(BigDecimal.valueOf(20.4));
        list.add(BigDecimal.valueOf(20.4));
        list.add(BigDecimal.valueOf(20.4));
        Optional<BigDecimal> reduce = list.stream().reduce(BigDecimal::multiply);
        System.out.println(reduce.get());
    }


    public static void test2() {
        String str = Stream.of("A","B","C").reduce(String::concat).orElse("");
        System.out.println(str);
        Integer sum = Stream.of(1,2,3,4).reduce((a,b)->a+b).orElse(0);
        System.out.println(sum);
    }

    public static void test3() {
        List<String> list = Stream.of(1,2,3,4).reduce(new ArrayList<>(),(a, b)->{
            a.add("element-"+b);
            System.out.println("a1:"+a);
            System.out.println("b1:"+b);
            return a;
        },(a,b)->{
            a.addAll(b);
            return a;
        });
        System.out.println(list);
    }

    /**
     * 先fork,在每个并行子线程中都执行:x+y
     * 线程一：10+1
     * 线程二：10+2
     * 线程三：10+3
     * ---------------
     * 再join,对每个子线程计算出的结果进行累乘:x * y
     * result *= 线程一的结果
     * result *= 线程二的结果
     * result *= 线程三的结果
     * 得到result = 1716
     * ————————————————
     */

    public static void test4() {
        //并行情况
        Integer result = Stream.of(1,2,3).parallel().reduce(10,(x,y)->x+y,(x,y)->x*y);
        System.out.println("result:"+result);
    }

    public static void test5() {
        //非并行情况下 第三个参数不起作用
        Integer result = Stream.of(1,2,3).reduce(10,(x,y)->x+y+2,(x,y)->x*y);
        System.out.println("result:"+result);
    }
}
