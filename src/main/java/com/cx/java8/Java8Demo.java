package com.cx.java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Java8Demo {
    public static void main(String[] args) {
        test7();
    }

    public static void test1() {
        Predicate<String> predicate = (str)->str.equals("chenxiang");
        boolean flag = predicate.test("chenxiang");
        System.out.println(flag);
    }

    public static void test2() {
        Consumer<String> consumer = s -> {
            System.out.println("s:"+s);
        };
        consumer.accept("aaaa");
    }

    public static  void test3() {
        Supplier<Integer> supplier = ()->{
            return 1+3;
        };
        int num = supplier.get();
        System.out.println("num:"+num);
    }

    public static void test4() {
        Function<String, Integer> function = s -> {
            if ("1".equals(s)) {
                return 2;
            }
            return 0;
        };
        Integer num = function.apply("2");
        System.out.println(num);
    }

    public static void test5() {
        Integer[] nums = new Integer[]{2,3,4};
        Integer result = Arrays.stream(nums).reduce(1, (integer, integer2) -> integer*integer2);
        System.out.println(result);
     }

     public static void test6() {
        List<User> list = new ArrayList<>();
        list.add(new User("code","item"));
        list.add(new User("name","001"));
        list.add(new User("name","002"));
         list.add(new User("name","003"));
        /*Map<String,String> map = list.stream().collect(Collectors.toMap(User::getKey,User::getValue));
        System.out.println(map);*/
        Map<String,List<User>> listMap = list.stream().collect(Collectors.groupingBy(User::getKey));
        System.out.println(listMap);
        list.stream().collect(HashMap::new,(map,user)->map.put(user.getKey(),user.getValue()),Map::putAll);
         Map<String,String> map = new HashMap<>();
         //其中Collectors.toMap方法的第三个参数为键值重复处理策略，如果不传入第三个参数，当有相同的键时，会抛出一个IlleageStateException。
         Map<String,String> map1 = list.stream().collect(Collectors.toMap(user->user.getKey(),user->user.getValue(),(k1,k2)->k2));
         //Map<String,String> map2 = list.stream().collect(Collectors.toMap((user)->user.getKey(),(user)->user.getValue()));
         System.out.println(map1);
    }

    public static void test7() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        System.out.println(list.stream().anyMatch(a->a.startsWith("t")));
        System.out.println(list.stream().noneMatch(a->a.startsWith("o")));
        System.out.println(list.stream().allMatch(a->a.startsWith("t")));
    }

     static class User{
        private String key;
        private String value;

         @Override
         public String toString() {
             return "User{" +
                     "key='" + key + '\'' +
                     ", value='" + value + '\'' +
                     '}';
         }

         public User(String key, String value) {
             this.key = key;
             this.value = value;
         }

         public String getKey() {
             return key;
         }

         public void setKey(String key) {
             this.key = key;
         }

         public String getValue() {
             return value;
         }

         public void setValue(String value) {
             this.value = value;
         }
     }
}
