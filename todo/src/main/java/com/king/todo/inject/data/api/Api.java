package com.king.todo.inject.data.api;


import com.king.todo.ui.user.UserEntity;
import com.king.todo.ui.user.login.LoginEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by arvin on 2017/11/28.
 */

public interface Api {

    @POST("/user/login")
    Observable<ApiInfoEntity<LoginEntity>> login(@Body UserEntity userEntity);
    @POST("/user/register")
    Observable<ApiInfoEntity<UserEntity>> register(@Body UserEntity userEntity);
}
