package com.king.todo.inject.data.api;

import com.binding.model.data.encrypt.SingleTransParams;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author user
 * @version $Rev$
 * @des 2018/7/9
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ApiParams implements SingleTransParams<RequestBody> {
    @Override
    public RequestBody transParams() {
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        return RequestBody.create(mediaType, new Gson().toJson(this));
//        FormUtfBody.Builder builder = new FormUtfBody.Builder();
//        List<Field> fields = ReflectUtil.getAllFields(getClass(), new ArrayList<>());
//        for (Field field : fields) {
//            Object o = ReflectUtil.beanGetValue(field, this);
//            if (o == null) continue;
//            if(BaseUtil.isEncoded(field)) builder.addEncoded(BaseUtil.findQuery(field), encrypt(o));
//            else builder.add_friends(BaseUtil.findQuery(field), encrypt(o));
//        }
//        return builder.build();
    }

    @Override
    public String encrypt(Object json) {
        return json.toString();
    }
}
