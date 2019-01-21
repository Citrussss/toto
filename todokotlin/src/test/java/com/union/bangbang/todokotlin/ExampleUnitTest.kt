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

    @Test
    fun decodeAtIndex():String {
        val S: String
        val K: Int
        S = "ajx37nyx97niysdrzice4petvcvmcgqn282zicpbx6okybw93vhk782unctdbgmcj"
        //                "82unctdbgmcjmbqn25rorktmu5ig2qn2y4xagtru2nehmsp";
        K = 976159153
        //        for (int i = 1; i <= 64; i++) {
        return ff(S.toCharArray(), 0, 0, K.toLong(), 0).toString()
        //        }
        //	   System.out.print(fun(S.toCharArray(),0,0,K,0)+
        //               fun(S.toCharArray(),0,0,K,0));
    }

    private fun ff(chars: CharArray, start: Int, lenth: Long, target: Long, cycle: Int): Char {
        //	    System.out.println("target"+target);
        for (i in start until chars.size) {
            val c = chars[i]
            if (isShuzi(c)) {
                val newLenth = (lenth + i - start) * (c.toInt() - 48)
                return if (newLenth < target) {
                    //                    System.out.println(newLenth);
                    ff(chars, i + 1, newLenth, target, cycle + 1)
                } else if (target % (lenth + i - start) != 0L)
                    ff(chars, 0, 0, target % (lenth + i - start), 0)
                else
                    returnChar(chars, i)
            } else if (i + lenth - start + 1 == target) return returnChar(chars, i)
        }
        return '1'
    }

    private fun isShuzi(c: Char): Boolean {
        return c <= '9' && c >= '0'
    }

    private fun returnChar(chars: CharArray, position: Int): Char {
        return if (isShuzi(chars[position])) returnChar(chars, position - 1) else chars[position]
    }
//
//    @Test
//    fun globalScope(){
//        GlobalScope
//    }
}


