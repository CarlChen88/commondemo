package com.cx.data;

public class ThreadLocalKeyUtil {

    private static ThreadLocal<String> threadLocalKey = new InheritableThreadLocal<>();

    public static void setDB(String DB) {
        threadLocalKey.set(DB);
    }
    public static String getDB() {
        return threadLocalKey.get();
    }
    public static void clearDB() {
        threadLocalKey.remove();
    }
}
