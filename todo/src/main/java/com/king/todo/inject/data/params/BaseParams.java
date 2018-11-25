package com.king.todo.inject.data.params;

import com.king.todo.ui.user.User;

/**
 * @name money
 * @class name：com.king.todo.inject.data.params
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/4 12:58 PM
 * @change
 * @chang time
 * @class describe
 */
interface BaseParams {
     String token = User.getToken();
}
