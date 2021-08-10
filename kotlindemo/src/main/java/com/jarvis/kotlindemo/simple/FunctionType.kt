package com.jarvis.kotlindemo.simple

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/10
 */

class View {

    interface OnClickListener {
        fun onClick(view: View)
    }

    fun setOnClickListener(listener: OnClickListener) {

    }

    //函数类型参数定义
    fun setOnClickListener2(listener: (View) -> Unit) {

    }

}


fun main() {

    var view = View()

    view.setOnClickListener(object : View.OnClickListener {

        override fun onClick(view: View) {
            println("click")
        }
    })
    //函数类型参数调用
    view.setOnClickListener2(::onClick)
    view.setOnClickListener2 {
        println("Click")
    }
}

fun onClick(view: View) {

}