package com.ouvrirdeveloper.core.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtil {


    fun useRunTimePermissions(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
    }

    fun arePermissionsGranted(permissions: Array<String>, activity: Activity): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) return false
        }
        return true
    }

    fun requestPermissionsCompat(permissions: Array<String>, requestCode: Int, activity: Activity) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    fun hasPermission(context: Context, permission: String) =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    fun shouldShowRequestPermissionRationale(permission: String, activity: Activity): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }

}