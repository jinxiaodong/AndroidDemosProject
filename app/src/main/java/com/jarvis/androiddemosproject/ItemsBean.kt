package com.jarvis.androiddemosproject

import android.graphics.drawable.Drawable
import com.blankj.utilcode.util.DeviceUtils
import com.jarvis.lib.ktx.dp2px

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/25
 */
data class ItemsBean(
    val name: String,
    val pic: Drawable? = null,
    val type: Int = 1,
    val flower: Int = 0,
    val marginStart: Int = 8.dp2px(),
    val marginEnd: Int = 8.dp2px()
)
