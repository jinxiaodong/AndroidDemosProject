package com.jarvis.lib.ktx

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.core.content.ContextCompat
import com.jarvis.lib.core.ApplicationHolder

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/25
 */

private val displayMetrics: DisplayMetrics? = Resources.getSystem().displayMetrics

fun Int.dp2px(): Int {
    return this.toFloat().dp2px().toInt()
}

fun Float.dp2px(): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
}

fun getDrawable(id: Int): Drawable? {
    return ContextCompat.getDrawable(ApplicationHolder.get(), id)
}
