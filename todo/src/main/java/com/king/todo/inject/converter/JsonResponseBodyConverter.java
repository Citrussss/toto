package com.king.todo.inject.converter;


import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;
    private static final JsonDeepUtil jsonDeepUtil = new JsonDeepUtil();

    JsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        value.close();
        return jsonDeepUtil.jsonParse(json, type);
    }


}
