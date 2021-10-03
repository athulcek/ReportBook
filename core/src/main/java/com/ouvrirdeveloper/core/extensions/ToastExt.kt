package com.ouvrirdeveloper.core.extensions

import android.content.Context
import android.widget.Toast
import com.ouvrirdeveloper.core.ui.BaseFragment
import com.ouvrirdeveloper.basearc.ToastLength
import com.ouvrirdeveloper.basearc.ui.base.BaseActivity

fun BaseActivity.showToast(message: String, @ToastLength duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun BaseFragment.showToast(message: String, @ToastLength duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(requireContext(), message, duration).show()
}

// Toash extensions
fun Context.showShotToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
