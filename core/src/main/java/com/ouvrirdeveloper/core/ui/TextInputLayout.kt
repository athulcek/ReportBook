package com.ouvrirdeveloper.core.ui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.view.ContextThemeWrapper
import com.google.android.material.textfield.TextInputLayout
import com.ouvrirdeveloper.core.R


class AppTextInputLayout : TextInputLayout {
    constructor(context: Context) : super(
        ContextThemeWrapper(
            context,
            R.style.TextInputLayoutAppearance
        )
    ) {
        setStyle()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(
        ContextThemeWrapper(
            context,
            R.style.TextInputLayoutAppearance
        ), attrs
    ) {
        setStyle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        ContextThemeWrapper(
            context,
            R.style.TextInputLayoutAppearance
        ),
        attrs,
        defStyle
    ) {
        setStyle()
    }

    private fun setStyle() {
        setErrorTextAppearance(R.style.ErrorText)

    }
}