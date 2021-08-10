package com.jarvis.kotlindemo.simple

/**
 * @author jinxiaodong
 * @description： 为什么inline 关键字推荐在有函数类型参数的 函数中使用。
 * 编译java 字节码后可以发现，内联函数对于函数类型参数不会生产对应对象，而是直接赋值函数参数的对应代码到调用位置，
 * 减少了对象生成
 * @date 2021/8/10
 */


inline fun measureTime(action: () -> Unit): Unit {
    println(">>>>")
    val startTime = System.currentTimeMillis()
    action()
    val endTime = System.currentTimeMillis()
    println("<<<< 【${endTime - startTime}】")
}

fun main() {

    measureTime {
        println("Hello")
    }
}