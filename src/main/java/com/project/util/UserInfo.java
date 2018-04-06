package com.project.util;

import com.project.models.User;

public class UserInfo {

    private static String userId;
    private static User user;

    public static void setUserId(String userId) {
        UserInfo.userId = userId;
    }

    public static void setUser(User user) {
        UserInfo.user = user;
    }

    public static String getUserId() {

        return userId;
    }

    public static User getUser() {
        return user;
    }
}
