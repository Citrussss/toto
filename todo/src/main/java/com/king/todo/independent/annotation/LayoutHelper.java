package com.king.todo.independent.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @name money
 * @class name：com.king.todo.independent.annotation
 * @class describe
 * @anthor bangbang QQ:740090077
 * @time 2018/10/26 9:45 PM
 * @change
 * @chang time
 * @class describe
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LayoutHelper {
    @LayoutRes int[] value();
}
