package com.ouvrirdeveloper.basearc.core.extension

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.ouvrirdeveloper.core.extensions.setNegativeButton
import com.ouvrirdeveloper.core.extensions.setOnDismiss
import com.ouvrirdeveloper.core.extensions.setPositiveButton
import com.ouvrirdeveloper.core.R

// Show alert dialog
fun Context.showAlertDialog(
    message: String,
    cancellable: Boolean = true,
    onPositiveButtonClick: (() -> Unit)? = null,
    positiveButtonText: String = getString(R.string.ok),
    negativeButtonText: String? = null,
    onNegativeButtonClick: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
): AlertDialog {
    val builder = AlertDialog.Builder(this).apply {
        setMessage(message)
        setTitle(getString(R.string.app_name))
        setPositiveButton(
            positiveButtonText,
            onPositiveButtonClick,
            onNegativeButtonClick
        )
        setNegativeButton(negativeButtonText, onNegativeButtonClick)
        onDismiss?.let { setOnDismiss(it) }
        setCancelable(cancellable)
    }
    val alertDialog = builder.create()
    alertDialog.show()
    return alertDialog
}

// Snackbar Extensions
fun View.showShotSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.showLongSnackbar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun View.snackBarWithAction(
    message: String, actionlable: String,
    block: () -> Unit
) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .setAction(actionlable) {
            block()
        }

}