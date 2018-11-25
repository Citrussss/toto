package com.king.todo.inject.data.sql;

import com.king.todo.inject.module.DataModule;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * @name money
 * @class name：com.king.todo.ui.user
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/4 12:41 PM
 * @change
 * @chang time
 * @class describe
 */
@Table(database = DataModule.class)
public class UserEntity extends ViewDbInflate {

    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private String name;
    @Column
    private String mobile;
    @Column
    private String token;
    /**
     * 密码字段不要保存
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
