package com.ango.circle.core.utils

import android.app.Activity
import android.content.pm.PackageManager.PERMISSION_DENIED
import androidx.core.content.ContextCompat
import com.ango.circle.core.state.PermissionState

class PermissionManager {


    companion object {
        fun requestPermission(
            context: Activity,
            permission: String
        ): PermissionState {
            val isPermissionDenied =
                ContextCompat.checkSelfPermission(context, permission) == PERMISSION_DENIED
            if (isPermissionDenied) {
                if (shouldShowPermissionRational(context, permission)) {
                    return PermissionState.EXPLAIN
                } else {
                    return PermissionState.DENIED
                }
            } else {
                return PermissionState.GRANTED
            }
        }

        private fun shouldShowPermissionRational(activity: Activity, permission: String): Boolean {
            return activity.shouldShowRequestPermissionRationale(permission)
        }
    }


}