package com.jarvis.demos

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * @author jinxiaodong
 * @description：
 * @date 2022/1/25
 */


fun Activity.checkPermissionAllGranted(permissions: Array<String>): Boolean {

    for (permission in permissions) {
        if (ContextCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
    }
    return true
}