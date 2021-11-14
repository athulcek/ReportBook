package com.ouvrirdeveloper.basearc

import android.view.View
import android.widget.Toast
import androidx.annotation.IntDef


@IntDef(View.INVISIBLE, View.GONE, View.VISIBLE)
@Retention(AnnotationRetention.SOURCE)
annotation class ViewVisibility


@IntDef(Toast.LENGTH_LONG, Toast.LENGTH_SHORT)
@Retention(AnnotationRetention.SOURCE)
annotation class ToastLength