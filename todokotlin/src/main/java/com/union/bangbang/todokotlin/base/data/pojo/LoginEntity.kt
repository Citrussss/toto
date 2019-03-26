package com.union.bangbang.todokotlin.base.data.pojo

data class LoginEntity(
        val id :Int,
        val token: String,
        val userEntity: UserEntity
)