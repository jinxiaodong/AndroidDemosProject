package com.jarvis.kotlindemo.entity;

import com.jarvis.kotlindemo.Utils.Utils;
import com.jarvis.kotlindemo.base.BaseApplication;
import com.jarvis.kotlindemo.simple.View;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/9
 */
public class A {


    public static void main(String[] args) {

        User user = new User("user1", "BB", "CC");

//        String username = user.getUsername();


//        user.username

        BaseApplication.Companion.currentApplication();
        BaseApplication.currentApplication();

        Utils.INSTANCE.toast("");
        Utils.INSTANCE.toast("");

        //java中如何调用Kotlin中声明的函数类型
        //Kotlin 的函数类型就是一个接口
        View view = new View();
        view.setOnClickListener2(new Function1<View, Unit>() {
            @Override
            public Unit invoke(View view) {
                return null;
            }
        });
    }

}
