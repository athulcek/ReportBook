package com.ouvrirdeveloper.core.extensions

import android.content.Context
import android.widget.Toast
import com.ouvrirdeveloper.basearc.ToastLength
import com.ouvrirdeveloper.basearc.ui.base.BaseActivity
import com.ouvrirdeveloper.core.ui.BaseFragment
import com.ouvrirdeveloper.core.utils.callOnMainThread

fun BaseActivity.showToast(message: String, @ToastLength duration: Int = Toast.LENGTH_LONG) {
    callOnMainThread { Toast.makeText(this, message, duration).show() }
}

fun BaseFragment.showToast(message: String, @ToastLength duration: Int = Toast.LENGTH_LONG) {
     callOnMainThread { Toast.makeText(requireContext(), message, duration).show() }
}
