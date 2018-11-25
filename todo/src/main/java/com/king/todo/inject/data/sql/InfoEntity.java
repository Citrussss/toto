package com.king.todo.inject.data.sql;

/**
 * @name money
 * @class name：com.king.todo.inject.data.sql
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/4 1:26 PM
 * @change
 * @chang time
 * @class describe
 */
public class InfoEntity <Entity>{
    private String info;
    private int code;
    private Entity entity;

    public String getInfo() {
        return info;
    }

    public InfoEntity() {
    }

    public InfoEntity(String info, int code) {
        this.info = info;
        this.code = code;
    }

    public InfoEntity(Entity entity) {
        this.entity = entity;
        if(entity!=null){
            info = "success";
            code = 1;
        }
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
