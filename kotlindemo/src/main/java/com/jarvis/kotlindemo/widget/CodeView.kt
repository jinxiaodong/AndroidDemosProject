package com.jarvis.kotlindemo.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.jarvis.kotlindemo.R
import com.jarvis.kotlindemo.Utils.dp2px
import java.util.*

/**
 * @author jinxiaodong
 * @description：
 * @date 2021/8/9
 */
class CodeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatTextView(context, attrs, defStyleAttr) {


    private val codeList = arrayOf(
        "kotlin",
        "android",
        "java",
        "http",
        "https",
        "okhttp",
        "retrofit",
        "tcp/ip"
    )
    private val paint = Paint().apply {
       isAntiAlias = true
       style = Paint.Style.STROKE
       color = resources.getColor(R.color.purple_500, null)
       strokeWidth = 6f.dp2px()
    }

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(resources.getColor(R.color.purple_200, null))
        setTextColor(Color.WHITE)
        updateCode()
    }


    fun updateCode() {
        val random = Random().nextInt(codeList.size)
        val code: String = codeList[random]
        text = code
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawLine(0f, height.toFloat(), width.toFloat(), 0f, paint)
        super.onDraw(canvas)

    }
}