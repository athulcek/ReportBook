package com.ouvrirdeveloper.core.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun AppCompatActivity.callOnMainThread(mainCall: suspend () -> Unit) {

    this.lifecycleScope.launchWhenStarted {
        withContext(Dispatchers.Main) {
            mainCall()
        }
    }
}

fun Fragment.callOnMainThread(mainCall: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        withContext(Dispatchers.Main) {
            mainCall()
        }
    }
}