package com.king.todo.inject.data.params;

/**
 * @name money
 * @class name：com.king.todo.inject.data.params
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/4 1:01 PM
 * @change
 * @chang time
 * @class describe
 */
public class LoginParams implements BaseParams {
    private String mobile;
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
