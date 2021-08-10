package com.jarvis.kotlindemo.entity

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/9
 */
data class User(var username: String?, var password: String?, var code: String?) {

    //通过 @JvmField 注解可以让编译器只⽣成⼀个 public 的成员属性，不⽣成对
    //应的 setter/getter 函数
//    @JvmField


}