package com.ouvrirdeveloper.core.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ouvrirdeveloper.basearc.ui.base.BaseActivity

//val firstName by getValue<String>("firstName") // String?
inline fun <reified T : Any> Activity.getValue(
    key: String, defaultvalue: T? = null
) = lazy {
    val value = intent?.extras?.get(key)
    if (value is T) value else defaultvalue
}

inline fun <reified T : Any> Activity.getValueNonNull(
    key: String, defaultvalue: T? = null
) = lazy {
    val value = intent?.extras?.get(key)
    requireNotNull((if (value is T) value else defaultvalue)) { key }
}

// Fragment related
inline fun <reified T : Any> Fragment.getValue(key: String, defaultvalue: T? = null) = lazy {
    val value = arguments?.get(key)
    if (value is T) value else defaultvalue
}

inline fun <reified T : Any> Fragment.getValueNonNull(key: String, defaultvalue: T? = null) =
    lazy {
        val value = arguments?.get(key)
        requireNotNull(if (value is T) value else defaultvalue) { key }
    }


fun AppCompatActivity.showKeyboard(view: View) {
    try {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.showSoftInput(
            view,
            InputMethodManager.SHOW_IMPLICIT
        )
    } catch (_: Exception) {
    }
}

fun AppCompatActivity.hideKeyboard() {
    this.currentFocus?.let { v ->
        v.clearFocus()
        try {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
                v.windowToken,
                0
            )
        } catch (_: Exception) {
        }
    }
}