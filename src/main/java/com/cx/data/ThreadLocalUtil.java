package com.cx.data;

public class ThreadLocalUtil {

    private static ThreadLocal<String> threadLocalDB = new ThreadLocal<>();

    public static void setDB(String DB) {
        threadLocalDB.set(DB);
    }
    public static String getDB() {
        return threadLocalDB.get();
    }
    public static void clearDB() {
        threadLocalDB.remove();
    }
}
