package com.ly.login.manager;

public class TestManager {
    private static String userName;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        TestManager.userName = userName;
    }
}
