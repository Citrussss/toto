package com.union.bangbang.todokotlin.base.data.pojo

import com.dbflow5.annotation.PrimaryKey
import com.dbflow5.annotation.Table
import com.dbflow5.reactivestreams.structure.BaseRXModel
import com.dbflow5.structure.BaseModel
import com.union.bangbang.todokotlin.base.data.model.AppDatabase

//@Table(database = AppDatabase::class)
data class UserEntity constructor(
        @PrimaryKey  var id: Int,
        var mobile: String,
        var password: String): BaseRXModel()