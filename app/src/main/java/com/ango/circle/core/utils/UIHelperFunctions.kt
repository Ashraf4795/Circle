package com.ango.circle.core.utils

import android.content.Context
import android.widget.Toast


fun showToast(context: Context, msg: String, lengthLong: Int) {
    Toast.makeText(context,msg,lengthLong).show()
}