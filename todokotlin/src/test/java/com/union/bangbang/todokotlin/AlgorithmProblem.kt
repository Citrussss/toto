package com.union.bangbang.todokotlin

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.Predicate
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class AlgorithmProblem {
    var string = arrayOf("dasdas", "dasdasda")
    @Test
    fun math(): Unit {
        val map = string.filter { it.length == 5 }.sortedBy { it }.map { it.toUpperCase() }.toList()
        Observable.fromIterable(map).filter { it.length == 5 }.subscribe{t: String? ->  }

        var list:ArrayList<String> = ArrayList<String>()
        list.removeAt(1)
    }


}