package com.ouvrirdeveloper.core.extensions

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


internal fun AlertDialog.Builder.setPositiveButton(
    positiveButtonText: String,
    onPositiveButtonClick: (() -> Unit)?,
    onNegativeButtonClick: (() -> Unit)?
) {
    setPositiveButton(positiveButtonText) { _, which: Int ->
        run {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                onPositiveButtonClick?.invoke()
            } else {
                onNegativeButtonClick?.invoke()
            }
        }
    }
}

internal fun AlertDialog.Builder.setNegativeButton(
    negativeButtonText: String?,
    onNegativeButtonClick: (() -> Unit)?
) {
    negativeButtonText?.let {
        setNegativeButton(negativeButtonText) { _, which: Int ->
            run {
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    onNegativeButtonClick?.invoke()
                }
            }
        }
    }
}

internal fun AlertDialog.Builder.setOnDismiss(onDismiss: (() -> Unit)) {
    setOnDismissListener {
        onDismiss.invoke()
    }
}