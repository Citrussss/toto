package com.union.bangbang.todokotlin.base.data.pojo

/**
 * @name toto
 * @anthor bangbang QQ:740090077
 * @time 2018/12/21 10:47 PM
 * 只有编译器可能不骗你。
 */

data class InfoEntity(
        val code: Int,
        val `data`: Data
)

data class Data(
        val token: Token,
        val user: User
)

data class Token(
        val id: Int,
        val token: String,
        val userEntity: UserEntity
)

data class UserEntity(
        val id: Int,
        val name: String
)

data class User(
        val id: Int,
        val name: String
)