package com.ouvrirdeveloper.core.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class BaseDialogFragment : DialogFragment() {

    companion object {
        fun create() =
            BaseDialogFragment()
    }


    override fun onCreateDialog(
        savedInstanceState: Bundle?
    ): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Title")
            .setMessage("Supporting text")
            .create()
    }
}