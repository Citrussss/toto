package com.king.todo.inject.data.sql;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.raizlabs.android.dbflow.rx2.language.RXSQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Where;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class DatabaseApi {
    Select select;

    public DatabaseApi(Select select) {
        this.select = select;
    }

    public Observable<List<JournalEntity>> getJournalEntity() {
        return RXSQLite.rx(select.from(JournalEntity.class))
                .queryList()
                .subscribeOn(Schedulers.newThread())
                .toObservable();
    }

    /**
     * 以下为登陆模块
     */
    public Observable<InfoEntity<UserEntity>> register(@NonNull UserEntity userEntity) {
        Where<UserEntity> where = select.from(UserEntity.class).where(UserEntity_Table.mobile.eq(userEntity.getMobile()));
        if (!TextUtils.isEmpty(userEntity.getMobile())
                && !TextUtils.isEmpty(userEntity.getPassword())
                && where.querySingle() == null) {
            try {
                userEntity.setToken(Arrays.toString((userEntity.getMobile() + userEntity.getPassword()).getBytes("utf-8")));
                userEntity.save();
                return RXSQLite.rx(where).querySingle().subscribeOn(Schedulers.newThread()).toObservable().map(InfoEntity::new);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return Observable.just(new InfoEntity<>("注册失败", 200));
    }

    public Observable<InfoEntity<UserEntity>> login(@NonNull UserEntity userEntity) {
        try {
            userEntity.setToken(Arrays.toString((userEntity.getMobile() + userEntity.getPassword()).getBytes("utf-8")));
            UserEntity entity = select.from(UserEntity.class).where(UserEntity_Table.mobile.eq(userEntity.getMobile())).querySingle();
            if(entity!=null&&entity.getToken().equals(userEntity.getToken())){
                return Observable.just(new InfoEntity<>(entity));
            }else return Observable.just(new InfoEntity<>("账号或者密码错误",200));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Observable.just(new InfoEntity<>("账号不存在",200));
    }

/*    *//**
     * 记录日志
     *//*
    public Observable<InfoEntity> saveDetail(DetailEntity entity){



    }*/
    /*public Observable<UserEntity> login(LoginParams loginParams){
        return RXSQLite.rx(select.from(UserEntity.class)
        .where(UserEntity)
        )

    }*/
}
