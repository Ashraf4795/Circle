package com.ango.circle.core.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class PermissionManager{

    companion object {
        fun shouldRequestPermission(context: Context, permission: String): Boolean {
            //check if permission granted or denied,
            //if denied, check if the app should display a UI to clarify the purpose of this Permission
            //if false then gracfully continue.
            return ContextCompat.checkSelfPermission(context, permission) == PERMISSION_DENIED
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun shouldShowPermissionRational(activity: Activity, permission: String) {
            activity.shouldShowRequestPermissionRationale(permission)
        }
    }


}