@file:JvmName("KotlinUtils") //在java中可以通过 KotlinUtils. 使用

package com.jarvis.kotlindemo.Utils

import android.content.res.Resources

import android.widget.Toast

import android.util.TypedValue

import android.util.DisplayMetrics
import com.jarvis.kotlindemo.base.BaseApplication.Companion.currentApplication


/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/9
 */

private val displayMetrics: DisplayMetrics? = Resources.getSystem().displayMetrics



fun Float.dp2px(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
}


object Utils{
    @JvmOverloads
    fun toast(string: String?, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(currentApplication, string, duration).show()
    }
}
