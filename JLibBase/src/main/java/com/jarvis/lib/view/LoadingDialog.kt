package com.jarvis.lib.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import com.jarvis.lib.R

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/2/10
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.LoadingDialog) {


    private var loadingDialog: LoadingDialog? = null

    init {

        setContentView(R.layout.layout_loading_view)
        val imageView: ImageView = findViewById(R.id.iv_image)
        val message: TextView = findViewById(R.id.tv_message)

        val rotateAnimation = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        rotateAnimation.duration = 2000
        rotateAnimation.repeatCount = 10
        rotateAnimation.fillAfter = true

        imageView.startAnimation(rotateAnimation)
    }


    fun showDialog(context: Context) {

        if (context is Activity) {
            if (context.isFinishing) {
                return
            }
        }

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(context)
        }
        loadingDialog?.show()
    }

    fun dismissDialog() {
        loadingDialog?.dismiss()
    }
}