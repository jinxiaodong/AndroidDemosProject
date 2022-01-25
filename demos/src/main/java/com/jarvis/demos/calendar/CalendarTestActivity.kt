package com.jarvis.demos.calendar

import android.Manifest
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.CalendarContract
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.jarvis.demos.R
import com.jarvis.demos.checkPermissionAllGranted
import java.security.Permission
import java.util.*
import kotlin.collections.ArrayList

class CalendarTestActivity : AppCompatActivity() {


    var uris = arrayListOf<Uri>(
        Uri.parse("content://com.android.calendar/calendars"),
        Uri.parse("content://com.android.calendar/events"),
        Uri.parse("content://com.android.calendar/reminders"),
    )
    private val permissions =
        arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_test)

        checkAndRegister()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 10) {
            var isAllGranted = true
            for (granted in grantResults) {
                if (granted != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false
                    break
                }
            }
            if (isAllGranted) {
                register()

            }
        }

    }

    private fun checkAndRegister() {

        if (!checkPermissionAllGranted(permissions)) {
            ActivityCompat.requestPermissions(this, permissions, 10)
        } else {
            register()
        }


    }

    private fun register() {
        for (uri in uris) {
            contentResolver.registerContentObserver(
                uri,
                false,
                object : ContentObserver(Handler()) {
                    override fun onChange(selfChange: Boolean, uri: Uri?) {
                        super.onChange(selfChange, uri)
                        Log.i("xd", uri.toString())
                    }
                })
        }

    }
}