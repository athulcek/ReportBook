package com.ouvrirdeveloper.core.utils

import android.view.View
import androidx.databinding.BindingAdapter
import com.ouvrirdeveloper.core.extensions.gone
import com.ouvrirdeveloper.core.extensions.show
import com.ouvrirdeveloper.core.ui.custom.OUTextView


object ViewBindingUtil {

    @BindingAdapter(value = ["setVisibility"], requireAll = true)
    @JvmStatic
    fun View.setVisibility(value: Any?) {
        val needtoShow = when (value) {
            is String -> value.isEmpty().not() && value.equals("N/A").not() && value.equals("0")
                .not()
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

    @BindingAdapter(value = ["ou_text", "ou_title"], requireAll = false)
    @JvmStatic
    fun OUTextView.setTextandTitle(ou_text: String?, ou_title: String?) {
        if (ou_title.isNullOrEmpty().not()) {
            getOutextView().text = ou_title
        }else if(ou_text.isNullOrEmpty().not()){
            getOutextView().text = ou_text
        }
    }
}