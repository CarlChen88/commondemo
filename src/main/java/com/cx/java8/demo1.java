package com.cx.java8;

import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class demo1 {
    private static List<Teacher> teacherList = new ArrayList<>();
    private static List<Student> studentList = new ArrayList<>();
    static {
        teacherList.add(Teacher.builder().code("t001").name("小明老师").age(25).sex("male").build());
        teacherList.add(Teacher.builder().code("t002").name("小红老师").age(28).sex("female").build());
        teacherList.add(Teacher.builder().code("t003").name("小花老师").age(30).sex("female").build());
        studentList.add(Student.builder().code("s001").name("学生01").age(15).sex("female").build());
        studentList.add(Student.builder().code("s002").name("学生02").age(16).sex("female").build());
        studentList.add(Student.builder().code("s003").name("学生03").age(15).sex("female").build());
        studentList.add(Student.builder().code("s004").name("学生04").age(15).sex("female").build());
    }


    public static void main(String[] args) {
        test2();
    }

    /**
     * 将一个对象的集合转换成另一个对象的集合
     */
    public static void test1() {
        List<Student> list = teacherList.stream().map(teacher -> Student.builder().code(teacher.getCode()).build()).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void test2() {
        List<String> list1 = new ArrayList<>();
        list1.add("lili");
        list1.add("james1");
        list1.add("carl111");
        list1.add("lisa1111");
        List<String> list2 = new ArrayList<>();
        list2.add("lili");
        list2.add("john");
        list2.add("luyisi");
        // 交集
        List intersect = list1.stream().filter(list2::contains).collect(Collectors.toList());
        System.out.println(intersect);
        // 差集 list1-list2
        List reduce1 = list1.stream().filter(item->!list2.contains(item)).collect(Collectors.toList());
        System.out.println(reduce1);
        // 差集 list2-list1
        List reduce2 = list2.stream().filter(item->!list1.contains(item)).collect(Collectors.toList());
        System.out.println(reduce2);
        // 并集
        List<String> listAll1 = list1.parallelStream().collect(Collectors.toList());
        List<String> listAll2 = list2.parallelStream().collect(Collectors.toList());
        listAll1.addAll(listAll2);
        System.out.println(listAll1);
        // 去重并集
        System.out.println(listAll1.stream().distinct().collect(Collectors.toList()));
        // 从list中过滤出一个元素
        String str = list1.stream().filter(item->item.startsWith("f")).findAny().orElseGet(()->{
            return "aaaa";
        });
        System.out.println("str:"+str);
        List list = list1.stream().sorted(Comparator.comparing(item->item.length())).collect(Collectors.toList());
        System.out.println(list);
    }








    @Data
    @Builder
    @AllArgsConstructor
    @ToString
    @NoArgsConstructor
    static class Teacher{
        private String code;
        private String name;
        private String sex;
        @Builder.Default
        private Integer age =0 ;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @ToString
    @NoArgsConstructor
    static class Student{
        private String code;
        private String name;
        private String sex;
        @Builder.Default
        private Integer age =0 ;
    }
}
