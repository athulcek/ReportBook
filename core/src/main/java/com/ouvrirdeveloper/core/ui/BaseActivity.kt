package com.ouvrirdeveloper.basearc.ui.base

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.NavigationRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.lottie.LottieAnimationView
import com.ouvrirdeveloper.basearc.core.extension.asColor
import com.ouvrirdeveloper.basearc.core.extension.asString
import com.ouvrirdeveloper.basearc.core.extension.showAlertDialog
import com.ouvrirdeveloper.basearc.helper.network.base.ConnectivityProvider
import com.ouvrirdeveloper.core.R
import com.ouvrirdeveloper.core.extensions.hide
import com.ouvrirdeveloper.core.extensions.show
import com.ouvrirdeveloper.core.ui.BaseFragment
import com.ouvrirdeveloper.domain.usecases.UserUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
    val listOfLoadingLotties = listOf<Int>(
        R.raw.loading_square,
        R.raw.loading,
        R.raw.loading_one,
        R.raw.car_animation,
        R.raw.loading_animation_circle
    )

    private val progressLayout: View by lazy {
        var pLayout = findViewById<View>(R.id.cl_progress_loading)
        if (pLayout == null || pLayout.isInLayout.not()) {
            val parent: ViewGroup = (findViewById(android.R.id.content) as ViewGroup)
            val viewGroup = parent.getChildAt(0) as ViewGroup
            pLayout = LayoutInflater.from(this).inflate(R.layout.progress_layout, null, false)
            pLayout.elevation = 5f
            if (viewGroup is ConstraintLayout) {
                val set1 = ConstraintSet()
                //set1.clone(viewGroup)
                pLayout.setLayoutParams(
                    LinearLayoutCompat.LayoutParams(
                        0, 0
                    )
                )
                viewGroup.addView(pLayout)

                set1.connect(
                    pLayout.getId(),
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP,
                    0
                )
                set1.connect(
                    pLayout.getId(),
                    ConstraintSet.LEFT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.LEFT,
                    0
                )
                set1.connect(
                    pLayout.getId(),
                    ConstraintSet.RIGHT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT,
                    0
                )
                set1.connect(
                    pLayout.getId(),
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM,
                    0
                )
                set1.applyTo(viewGroup)
            }
            //viewGroup.addView(pLayout)
        }
        pLayout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes?.let { setContentView(it) }
        provider.addListener(this)
    }

    fun setAppBar(
        title: String,
        titleColorRes: Int = R.color.primaryTextColor,
        startIcon: Drawable? = null,
        onStartIconClick: (() -> Unit)? = null,
        endIcon: Drawable? = null,
        onEndIconClick: (() -> Unit)? = null,
        backgroundColorRes: Int = R.color.primaryColor
    ) {
        val toolbar = findViewById<TextView>(R.id.toolbar)
        val toolbarTitle = findViewById<TextView>(R.id.toolbar_title)
        val toolbarStartIcon = findViewById<ImageView>(R.id.toolbarStartIcon)
        val toolbarEndIcon = findViewById<ImageView>(R.id.toolbarEndIcon)
        toolbar?.apply {
            setBackgroundColor(backgroundColorRes.asColor(this@BaseActivity))
        }
        toolbarTitle?.apply {
            text = title
            setTextColor(titleColorRes.asColor(this@BaseActivity))
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

    fun showProgress(
        backgroundColorRes: Int? = null,
        message: String = R.string.loading_please_wait.asString(this),
        showRetry: Boolean = false,
        lottieFile: Int = -1,
        reTry: (() -> Unit)? = null
    ) = progressLayout.apply {
        backgroundColorRes?.let {
            this.findViewById<View>(R.id.viewBg)?.apply {
                this.setBackgroundColor(it)
                this.setAlpha(1f)
            }
        }
        this.findViewById<ProgressBar>(R.id.progress_bar_loading)?.apply {
            hide()
        }
        this.findViewById<LottieAnimationView>(R.id.Lottie_loading)?.apply {
            if (showRetry) {
                setAnimation(if (lottieFile == -1) R.raw.no_internet else lottieFile)
            } else {
                setAnimation(if (lottieFile == -1) listOfLoadingLotties.random() else lottieFile)
            }
            show()
        }
        this.findViewById<TextView>(R.id.text_view_loading)?.apply {
            text = message
        }
        this.findViewById<TextView>(R.id.tv_retry)?.apply {
            if (showRetry) {
                text = R.string.retry.asString(this@BaseActivity)
                setOnClickListener {
                    reTry?.invoke()
                }
                show()
                requestLayout()
            } else {
                hide()
            }
        }
        show()
    }

    fun showRetry(
        message: String,
        reTry: (() -> Unit)? = null,
        backgroundColorRes: Int? = null,
        lottieFile: Int = -1
    ) {
        showProgress(
            message = message,
            reTry = reTry,
            showRetry = true,
            backgroundColorRes = backgroundColorRes,
            lottieFile = lottieFile
        )
    }

    fun hideProgress() = CoroutineScope(Dispatchers.Main).launch {
        delay(2000)
        lifecycleScope.launchWhenStarted {
            progressLayout.hide()
        }
    }

    fun setNavGraph(
        @IdRes fragmentContainerId: Int,
        @NavigationRes graphResId: Int,
        @IdRes startDestinationId: Int?
    ): NavController {
        val navHostFragment = supportFragmentManager
            .findFragmentById(fragmentContainerId) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater

        val navGraph = graphInflater.inflate(graphResId)
        startDestinationId?.let { navGraph.startDestination = it }
        val navController = navHostFragment.navController
        navController.setGraph(navGraph, intent.extras)
        return navController
    }

}


