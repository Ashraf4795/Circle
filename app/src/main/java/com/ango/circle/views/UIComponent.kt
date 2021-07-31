package com.ango.circle.views

import android.content.Context
import com.ango.circle.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun showDialog(
    context: Context,
    onPositiveCallBack:() ->Unit,
    onNegativeCallBack:() ->Unit,
) {
    MaterialAlertDialogBuilder(context,
        R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered)
        .setMessage(context.resources.getString(R.string.permission_explain_message))
        .setNegativeButton(context.resources.getString(R.string.permission_explain_dialog_negative)) { dialog, which ->
            onPositiveCallBack.invoke()
        }
        .setPositiveButton(context.resources.getString(R.string.permission_explain_dialog_positive)) { dialog, which ->
            onNegativeCallBack.invoke()
        }
        .show()
}