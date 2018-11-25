package com.king.todo.inject.interceptor;

import android.content.Context;

import com.king.todo.inject.qualifier.context.AppContext;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * project：cutv_ningbo
 * description：
 * create developer： admin
 * create time：10:01
 * modify developer：  admin
 * modify time：10:01
 * modify remark：
 *
 * @version 2.0
 */

public class UserInterceptor implements Interceptor {
    private Context context;

    @Inject
    public UserInterceptor(@AppContext Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (response.code() == 401) {
            return response.newBuilder().code(401).message("").build();
        }
        return response;
    }
}
