package com.ouvrirdeveloper.core.utils

import android.os.StrictMode
import android.os.StrictMode.getThreadPolicy
import android.os.StrictMode.setThreadPolicy
import com.ouvrirdeveloper.core.BuildConfig


    fun permitDiskReads(func: () -> Any?) : Any? {
        if (BuildConfig.DEBUG) {
            val oldThreadPolicy = StrictMode.getThreadPolicy()
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder(oldThreadPolicy)
                    .permitDiskReads().build())
            val anyValue = func()
            StrictMode.setThreadPolicy(oldThreadPolicy)

            return anyValue
        } else {
            return func()
        }
    }
