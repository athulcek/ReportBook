package com.ouvrirdeveloper.reportbook.features.base

import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.ouvrirdeveloper.reportbook.features.home.HomeActivity
import com.ouvrirdeveloper.basearc.ui.base.BaseActivity
import com.ouvrirdeveloper.core.extensions.launchActivityAsRoot
import com.ouvrirdeveloper.core.extensions.showToast
import com.ouvrirdeveloper.domain.models.Resource
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.features.signin.SignInActivity
import com.ouvrirdeveloper.reportbook.features.splash.LandingPageActivity

/*
fun BaseActivity.showLoading(
    loadingMessage: String = getString(R.string.loading_please_wait),
    @ColorRes loadingTextColor: Int = R.color.orange,
    requireBackground: Boolean = true
) {

    getProgressBar()?.let {
        it.setBackgroundColor(
            ContextCompat.getColor(
                this,
                if (requireBackground) progress_background else android.R.color.transparent
            )
        )
        if (it.visibility != View.VISIBLE) {
            AnimationUtil.fadeInView(it)
        }
        getTextViewLoading()?.apply {
            text = loadingMessage
            setTextColor(ContextCompat.getColor(this@showLoading, loadingTextColor))
        }
        getProgressBar()?.visibility = View.VISIBLE
        getTextViewRetry()?.visibility = View.GONE
    }
    hideKeyboard()
}

fun BaseActivity.showLoading(@StringRes resource: Int, requireBackground: Boolean = true) {
    showLoading(getString(resource), requireBackground = requireBackground)
}*/
/*

fun BaseActivity.showRetry(
    @StringRes resource: Int,
    retryAction: () -> Unit,
    @ColorRes errorTextColor: Int = R.color.white,
    requireBackground: Boolean = true
) {

    getProgressBar()?.let {
        AnimationUtil.fadeInView(it)
        it.setBackgroundColor(
            ContextCompat.getColor(
                this,
                if (requireBackground) progress_background else android.R.color.transparent
            )
        )
        getTextViewLoading()?.apply {
            text = getString(resource)
            setTextColor(ContextCompat.getColor(this@showRetry, errorTextColor))
        }
        getProgressBar()?.visibility = View.GONE
        getTextViewRetry()?.apply {
            visibility = View.VISIBLE
            setOnClickListener { retryAction.invoke() }
        }
    }
    hideKeyboard()
}
*/

/*
fun BaseActivity.hideLoading() {

    getProgressBar()?.let {
        AnimationUtil.fadeOutView(it)
    }
}*/

fun BaseActivity.setDisplayHomeEnabled(showHomeAsUp: Boolean) {
    supportActionBar?.apply {
        if (showHomeAsUp) {
            setHomeAsUpIndicator(R.drawable.ic_back_arrow)
            getToolBarTitle()?.apply {
                setPadding(paddingLeft, paddingTop, getActionBarSize(), paddingBottom)
            }
        } else {
            getToolBarTitle()?.apply {
                setPadding(0, paddingTop, 0, paddingBottom)
            }
        }
        setDisplayHomeAsUpEnabled(showHomeAsUp)
    }
}

fun BaseActivity.handleApiError(error: Resource<Any?>, errorHandler: () -> Boolean) {
    //hideLoading()
    error?.let {
        if (!errorHandler.invoke()) {
            when (it.status) {
                Status.NETWORK_ERROR -> {
                    showToast(getString(R.string.no_internet_connection))
                }
                Status.HTTP_ERROR -> {
                    if (it.errorCode == 401 || it.errorCode == 403) {
                        userUseCase.onLogout()
                        launchActivityAsRoot<SignInActivity> {}
                    } else {
                        showToast(getString(R.string.something_went_wrong))
                    }
                }
                Status.GENERIC_ERROR -> {
                    showToast(getString(R.string.something_went_wrong))
                }
                else -> {
                }
            }
        }
    }
}

fun BaseActivity.setToolbarTitle(title: String) {
    getToolBarTitle()?.let { textview ->
        textview.text = title
    }

}

private fun BaseActivity.getActionBarSize(): Int {
    val tv = TypedValue()
    return if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
        TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    } else 0
}
fun BaseActivity.setToolbar() {
    getToolBar()?.let {
        setSupportActionBar(it)
        setDisplayHomeEnabled(showHomeAsUp)
        configureToolbar()
    }
}

private fun BaseActivity.configureToolbar() {
    getToolBarTitle()?.visibility = View.VISIBLE
    getToolBar()?.setBackgroundColor(
        ContextCompat.getColor(
            this,
            com.ouvrirdeveloper.core.R.color.primaryColor
        )
    )
    when (this) {
        is HomeActivity, is LandingPageActivity -> {
            getToolBarTitle()?.visibility = View.GONE
            getToolBarStartIcon()?.visibility = View.VISIBLE
            getToolBarStartIcon()?.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_back_arrow
                )
            )
            setDisplayHomeEnabled(false)
        }
        is SignInActivity -> {
            getToolBarTitle()?.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            getToolBar()?.background =
                ContextCompat.getDrawable(
                    this, android.R.color.transparent
                )


        }
    }
}

fun BaseActivity.getToolBarTitle() = findViewById<TextView>(R.id.toolbar_title)
fun BaseActivity.getToolBarStartIcon() = findViewById<ImageView>(R.id.toolbarStartIcon)
fun BaseActivity.getToolBarEndIcon() = findViewById<ImageView>(R.id.toolbarEndIcon)
fun BaseActivity.getToolBar() = findViewById<Toolbar>(R.id.toolbar)
fun BaseActivity.getProgressBar() = findViewById<View>(R.id.progressIndicator)
fun BaseActivity.getTextViewLoading() = findViewById<TextView>(R.id.text_view_loading)
fun BaseActivity.getTextViewRetry() = findViewById<TextView>(R.id.tv_retry)

