package com.king.todo.ui.user;

import com.king.todo.inject.data.sql.UserEntity;

/**
 * @name money
 * @class name：com.king.todo.ui.user
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/4 1:00 PM
 * @change
 * @chang time
 * @class describe
 */
public class User {
    private static UserEntity userEntity;

    public User() {
    }
    public static String getToken(){
        return userEntity!=null?userEntity.getToken():"";
    }
}
