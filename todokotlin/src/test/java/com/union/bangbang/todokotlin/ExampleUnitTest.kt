package com.union.bangbang.todokotlin

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val a: Int = 1
    val b = 2
    @Test
    fun addition_isCorrect() {
        var a = "魔法少女"
        var b = "a is ${a.length} 售价 99。9 ${'$'}"
        print(b)
//        assertEquals(5, sum(2,2))
    }

    fun sum(a: Int, b: Int) = print(2)

    fun findMagic(name: String?): Int? = name?.length

    @Test
    fun test_findMagic() {
        val girl = "魔法少女"
        val boy: String? = null
        val lenth = boy?.length
        var aInt = listOf(1, 2, null, 4)
        for ((a, b) in aInt.withIndex()) println("$a,$b")
//        aInt = aInt.filterNotNull()
        girl?.let { print("girl is $aInt") }
        boy?.let { print("boy is $lenth") }
        lenth.let { lenth?.fuck(girl) }
    }

    infix fun Int.fuck(girl: String): Int {
        return this + girl.length
    }
//
//    @Test
//    fun globalScope(){
//        GlobalScope
//    }
}


