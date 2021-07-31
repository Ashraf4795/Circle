package com.ango.circle.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.Image
import android.widget.Toast


fun showToast(context: Context, msg: String, lengthLong: Int = Toast.LENGTH_LONG) {
    Toast.makeText(context,msg,lengthLong).show()
}

fun Image.toBitmap(rotationDegrees: Int): Bitmap {
    val buffer = planes[0].buffer
    val bytes = ByteArray(buffer.remaining())
    buffer.get(bytes)
    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size, null)
    val matrix = Matrix()
    matrix.postRotate(rotationDegrees.toFloat())
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}