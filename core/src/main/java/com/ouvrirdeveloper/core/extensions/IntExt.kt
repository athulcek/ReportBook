package com.ouvrirdeveloper.basearc.core.extension

import android.content.Context
import androidx.core.content.ContextCompat


fun Int.asColor(context: Context) = ContextCompat.getColor(context, this)
fun Int.asDrawable(context: Context) = ContextCompat.getDrawable(context, this)

