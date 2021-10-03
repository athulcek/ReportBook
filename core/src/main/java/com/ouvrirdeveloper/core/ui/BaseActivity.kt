package com.ouvrirdeveloper.basearc.ui.base

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.ouvrirdeveloper.core.ui.BaseFragment
import com.ouvrirdeveloper.basearc.core.extension.showAlertDialog
import com.ouvrirdeveloper.basearc.helper.network.base.ConnectivityProvider
import com.ouvrirdeveloper.core.R
import com.ouvrirdeveloper.core.extensions.show
import com.ouvrirdeveloper.domain.usecases.UserUseCase
import org.koin.android.ext.android.inject

abstract class BaseActivity(
    @LayoutRes private val layoutRes: Int?,
    var showHomeAsUp: Boolean = true
) : AppCompatActivity(), ConnectivityProvider.ConnectivityStateListener {

    private var alertDialog: AlertDialog? = null
    private val provider: ConnectivityProvider by lazy { ConnectivityProvider.createProvider(this) }
    val userUseCase: UserUseCase by inject()
    var networkObserver = MutableLiveData<Boolean>()
    val isNetworkAvailable get() = networkObserver.value == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes?.let { setContentView(it) }
        provider.addListener(this)
    }

    fun setAppBar(title: String, startIcon: Drawable? = null,onStartIconClick: (() -> Unit)? = null, endIcon: Drawable? = null,onEndIconClick: (() -> Unit)? = null) {
        val toolbarTitle = findViewById<TextView>(R.id.toolbar_title)
        val toolbarStartIcon = findViewById<ImageView>(R.id.toolbarStartIcon)
        val toolbarEndIcon = findViewById<ImageView>(R.id.toolbarEndIcon)
        toolbarTitle?.apply {
            text = title
        }
        toolbarStartIcon?.apply {
            background = startIcon
            show()
            setOnClickListener {
                onStartIconClick?.invoke()
            }
        }
        toolbarEndIcon?.apply {
            background = endIcon
            show()
            setOnClickListener {
                onEndIconClick?.invoke()
            }
        }
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
        if (!isFinishing) {
            if (alertDialog?.isShowing == true) {
                dismissAlert()
            }
            alertDialog = showAlertDialog(
                message,
                cancellable,
                onPositiveButtonClick,
                positiveButtonText,
                negativeButtonText,
                onNegativeButtonClick,
                onDismiss
            )
            alertDialog?.show()
        }
    }

    fun dismissAlert() {
        alertDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
            alertDialog = null
        }
    }

    fun launchPermissionSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.fromParts("package", packageName, null)
        startActivity(intent)
    }

    override fun onBackPressed() {
        if (canGoBack()) {
            super.onBackPressed()
        }
    }

    fun canGoBack(): Boolean {
        val fragmentList = supportFragmentManager.fragments
        var handled = false
        for (fragment in fragmentList) {
            if (fragment is BaseFragment && fragment.isVisible) {
                handled = canParentGoBack(fragment)
                if (handled) {
                    handled = fragment.onBackPressed()
                }
                if (handled) {
                    return false
                }
            }
        }
        return true
    }

    private fun canParentGoBack(fragment: BaseFragment): Boolean {
        var handled = false
        for (child in fragment.childFragmentManager.fragments) {
            if (child is BaseFragment && child.isVisible && child.childFragmentManager.fragments.size > 0) {
                handled = canParentGoBack(child)
            } else {
                if (child is BaseFragment && child.isVisible) {
                    handled = child.onBackPressed()
                }
            }
            if (handled) {
                return false
            }
        }
        return true
    }

    override fun onStateChange(state: ConnectivityProvider.NetworkState) {
        when (state) {
            is ConnectivityProvider.NetworkState.ConnectedState.Connected,
            is ConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy -> networkObserver.value =
                true
            ConnectivityProvider.NetworkState.NotConnectedState -> networkObserver.value = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        provider.removeListener(this)
    }


}