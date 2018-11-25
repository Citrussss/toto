package com.king.todo.ui.user;


import android.databinding.ObservableField;

import com.binding.model.data.encrypt.SingleTransParams;
import com.binding.model.model.ViewInflate;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @name money
 * @class name：com.king.todo.ui.user
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/20 5:57 PM
 * @change
 * @chang time
 * @class describe
 */
public class UserEntity extends ViewInflate implements SingleTransParams {

    /**
     * id : 1
     * mobile : 17857025659
     * name : null
     * password : 123456
     */

    private Integer id;
    private String mobile;
    private String name;
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public RequestBody transParams() {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(mediaType, new Gson().toJson(this));
    }

    @Override
    public String encrypt(Object json) {
        return json.toString();
    }
}
