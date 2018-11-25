package com.king.todo.inject.data.api;

/**
 * @name money
 * @anthor bangbang QQ:740090077
 * @time 2018/11/4 3:54 PM
 * 只有编译器可能不骗你。
 */
public class ApiInfoEntity<Entity extends Object> {

    /**
     * code : 10
     * data : null
     * msg : For input string: "他"
     */

    private int code;
    private Entity data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Entity getData() {
        return data;
    }

    public void setData(Entity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
