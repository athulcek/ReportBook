package com.ouvrirdeveloper.core.ui

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import com.ouvrirdeveloper.basearc.ui.base.BaseActivity
import com.ouvrirdeveloper.core.R
import com.ouvrirdeveloper.core.utils.PermissionUtil

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
            activity?.let {
                if (it is BaseActivity) {
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
     * @return true if back press was handled
     */
    open fun onBackPressed(): Boolean {
        return false
    }
}