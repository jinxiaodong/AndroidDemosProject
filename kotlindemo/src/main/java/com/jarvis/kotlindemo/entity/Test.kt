package com.jarvis.kotlindemo.entity

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/10
 */


var user = User("AA", "BB", "CC")
val userCopy = user.copy()
fun main() {

    println(user)
    println(userCopy)
    println(userCopy == user)//equals
    println(userCopy === user)//地址值


    repeat(100) { it: Int ->
        println(it)
    }
    val arrayOf = arrayOf(1, 2, 3)
    for (i in 0..99) {

    }
    for (i in 0 until arrayOf.size) {

    }
    for (i in arrayOf.indices) {

    }
}