package com.ouvrirdeveloper.core.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.ouvrirdeveloper.core.extensions.gone
import com.ouvrirdeveloper.core.extensions.show


object ViewBindingUtil {

    @BindingAdapter(value = ["setVisibility"], requireAll = true)
    @JvmStatic
    fun View.setVisibility(value: Any?) {
        val needtoShow = when (value) {
            is String -> value.isEmpty().not() && value.equals("N/A").not()&& value.equals("0").not()
            is Int -> (value == 0).not()
            is Long -> (value == 0L).not()
            null -> false
            else -> true
        }
        if (needtoShow) {
            show()
        } else {
            gone()
        }
    }
}