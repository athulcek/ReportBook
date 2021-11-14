package com.ouvrirdeveloper.core.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ouvrirdeveloper.core.R

open class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var behavior: BottomSheetBehavior<View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                ?.let {
                    behavior = BottomSheetBehavior.from(it)
                    behavior?.peekHeight = 0
                    behavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                }
        }
        return bottomSheetDialog
    }

    fun hideKeyboard(view: View) {
        view.clearFocus()
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun collapse() {
        behavior?.let {
            if (it.state != BottomSheetBehavior.STATE_COLLAPSED) {
                it.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    fun expand() {
        behavior?.let {
            if (it.state != BottomSheetBehavior.STATE_EXPANDED) {
                it.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }
}