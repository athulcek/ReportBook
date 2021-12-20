package com.ouvrirdeveloper.core.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ouvrirdeveloper.basearc.ui.base.BaseActivity
import com.ouvrirdeveloper.core.R
import com.ouvrirdeveloper.core.utils.PermissionUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {

    val getActivity
        get() = if (requireActivity() is BaseActivity) {
            requireActivity() as BaseActivity
        } else null

    fun setTitle(
        title: String,
        startIcon: Drawable? = null,
        onStartIconClick: (() -> Unit)? = null,
        endIcon: Drawable? = null,
        onEndIconClick: (() -> Unit)? = null
    ) {
        getActivity?.setAppBar(
            title = title,
            startIcon = startIcon,
            endIcon = endIcon,
            onStartIconClick = onStartIconClick,
            onEndIconClick = onEndIconClick
        )
    }


    fun showAlert(
        message: String,
        cancellable: Boolean = true,
        onPositiveButtonClick: (() -> Unit)? = null,
        positiveButtonText: String = getString(R.string.ok),
        negativeButtonText: String? = null,
        onNegativeButtonClick: (() -> Unit)? = null,
        onDismiss: (() -> Unit)? = null
    ) {
        if (!isRemoving && isAdded) {
            getActivity?.let {
                it.showAlert(
                    message,
                    cancellable,
                    onPositiveButtonClick,
                    positiveButtonText,
                    negativeButtonText,
                    onNegativeButtonClick,
                    onDismiss
                )
            }
        }
    }

    fun launchPermissionSetting() {
        getActivity?.launchPermissionSetting()
    }

    fun shouldShowPermissionRationale(permission: String) =
        PermissionUtil.shouldShowRequestPermissionRationale(permission, requireActivity())

    fun hasPermission(permission: String) =
        PermissionUtil.hasPermission(requireContext(), permission)

    /**
     * Could handle back press.
     */
    open fun onBackPressed() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            lifecycleScope.launchWhenStarted {
                val callback =
                    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                        onBackPressed()
                    }
            }

        }
    }
}