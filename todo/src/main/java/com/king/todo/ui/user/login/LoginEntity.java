package com.king.todo.ui.user.login;

import com.king.todo.ui.user.TokenEntity;
import com.king.todo.ui.user.UserEntity;

/**
 * @name money
 * @anthor bangbang QQ:740090077
 * @time 2018/11/18 5:39 PM
 * 只有编译器可能不骗你。
 */
public class LoginEntity {

    /**
     * user : {"id":1,"mobile":17857025659,"name":null,"password":"123456"}
     * token : {"mobile":17857025659,"token":"MTc4NTcwMjU2NTl8MTU0MjUzNDQzNzE5Mg=="}
     */

    private UserEntity user;
    private TokenEntity token;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TokenEntity getToken() {
        return token;
    }

    public void setToken(TokenEntity token) {
        this.token = token;
    }
}
