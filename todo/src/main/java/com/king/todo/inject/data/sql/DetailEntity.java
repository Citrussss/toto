package com.king.todo.inject.data.sql;

import com.king.todo.inject.module.DataModule;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * @name money
 * @class name：com.king.todo.inject.data.sql
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/7 2:23 PM
 * @change
 * @chang time
 * @class describe
 */
@Table(database = DataModule.class)
public class DetailEntity extends ViewDbInflate {
    @PrimaryKey(autoincrement = true)
    protected int id;
    @Column
    protected int type;
    @Column
    protected int userId;
    @Column
    protected long createTime;
    @Column
    protected long lastEditTime;
    @Column
    protected String describe;
    @Column
    protected String json;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(long lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public long getCreateTime() {

        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
